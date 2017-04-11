/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.SprintUserFacadeRemote;
import com.tropicscrum.backend.client.facade.TaskFacadeRemote;
import com.tropicscrum.backend.client.facade.TaskProgressFacadeRemote;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.TaskProgress;
import com.tropicscrum.frontend.controllers.view.BoardViewBean;
import com.tropicscrum.frontend.push.model.BoardMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.DragDropEvent;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "boardRequestBean")
@RequestScoped
public class BoardRequestBean implements Serializable {

    private final Comparator<Task> comparatorTask = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.getCode().compareTo(t2.getCode());
        }
    };

    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
    private final static String CHANNEL = "/board/";

    @Inject
    BoardViewBean boardViewBean;

    @EJB(lookup = TaskProgressFacadeRemote.JNDI_REMOTE_NAME)
    TaskProgressFacadeRemote taskProgressFacadeRemote;

    @EJB(lookup = TaskFacadeRemote.JNDI_REMOTE_NAME)
    TaskFacadeRemote taskFacadeRemote;
    
    @EJB(lookup = SprintUserFacadeRemote.JNDI_REMOTE_NAME)
    SprintUserFacadeRemote sprintUserFacadeRemote;

    /**
     * Creates a new instance of BoardRequestBean
     */
    public BoardRequestBean() {
    }

    public void startTaskActivity(DragDropEvent ddEvent) {
        try {
            Task task = (Task) ddEvent.getData();
            taskFacadeRemote.startTaskProgress(task, boardViewBean.getSprintUser());
            startTaskOnBoard(task.getId(), boardViewBean.getSprintUser());     
            publishNotification("start", task.getId().toString(), boardViewBean.getSprintUser());
        } catch (UpdateException ex) {
            Logger.getLogger(BoardRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void startTaskOnBoard(Long taskId, SprintUser sprintUser) {
        for (Iterator<TaskProgress> it = boardViewBean.getTaskProgresss().iterator(); it.hasNext();) {
            TaskProgress tp = it.next();
            if (tp.getSprintUser().equals(sprintUser)) {
                tp.setTask(taskFacadeRemote.find(tp.getTask().getId()));
                boardViewBean.getToDoTasks().add(tp.getTask());
                boardViewBean.getDoingTasks().remove(tp.getTask());
                it.remove();
            }
        }

        Task task = taskFacadeRemote.find(taskId);
        boardViewBean.getDoingTasks().add(task);
        boardViewBean.getToDoTasks().remove(task);
        task.setTaskProgresss(taskProgressFacadeRemote.findTaskActivity(task));
        if (task.getTaskProgresss().iterator().hasNext()) {
            boardViewBean.getTaskProgresss().add(task.getTaskProgresss().iterator().next());
        }
    }

    private void publishNotification(String action, String taskProgressId, SprintUser sprintUser) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            BoardMessage boardMessage = new BoardMessage(action, taskProgressId);
            boardMessage.setSprintUserId(sprintUser.getId().toString());
            String jsonMessage = mapper.writeValueAsString(boardMessage);
            for (SprintUser su : boardViewBean.getSprint().getSprintUsers()) {
                if (!su.getUser().equals(boardViewBean.getUser())) {
                    eventBus.publish(CHANNEL + boardViewBean.getSprint().getId().toString() + "/" + su.getUser().getId().toString(), jsonMessage);
                }
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(BoardRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeTask(DragDropEvent ddEvent) {
        try {
            TaskProgress taskProgress = (TaskProgress) ddEvent.getData();
            taskProgressFacadeRemote.closeTaskProgress(taskProgress);
            closeTaskOnBoard(taskProgress.getId());
            publishNotification("close", taskProgress.getId().toString(), boardViewBean.getSprintUser());
        } catch (UpdateException ex) {
            Logger.getLogger(BoardRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeTaskOnBoard(Long taskProgressId) {
        TaskProgress taskProgress = taskProgressFacadeRemote.find(taskProgressId);
        boardViewBean.getTaskProgresss().remove(taskProgress);
        boardViewBean.getDoingTasks().remove(taskProgress.getTask());
        boardViewBean.getDoneTasks().add(taskProgress.getTask());
        boardViewBean.getFinishedProgresss().add(taskProgress);
    }

    public void stopTask(DragDropEvent ddEvent) {
        try {
            TaskProgress taskProgress = (TaskProgress) ddEvent.getData();
            taskProgressFacadeRemote.stopTaskProgres(taskProgress);
            stopTaskOnBoard(taskProgress.getId());
            publishNotification("stop", taskProgress.getId().toString(), boardViewBean.getSprintUser());
        } catch (UpdateException ex) {
            Logger.getLogger(BoardRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void stopTaskOnBoard(Long taskProgressId) {
        TaskProgress taskProgress = taskProgressFacadeRemote.find(taskProgressId);
        boardViewBean.getToDoTasks().add(taskProgress.getTask());
        boardViewBean.getDoneTasks().remove(taskProgress.getTask());
        boardViewBean.getFinishedProgresss().remove(taskProgress);
    }

    public void receiveBoardMessage() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            String jsonMessage = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("boardMessage");
            BoardMessage boardMessage = mapper.readValue(jsonMessage, BoardMessage.class);
            switch (boardMessage.getAction()) {
                case "close":
                    closeTaskOnBoard(Long.parseLong(boardMessage.getTaskProgressId()));
                    break;

                case "start":
                    SprintUser sprintUser = sprintUserFacadeRemote.find(Long.parseLong(boardMessage.getSprintUserId()));
                    startTaskOnBoard(Long.parseLong(boardMessage.getTaskProgressId()), sprintUser);
                    break;

                case "stop":
                    stopTaskOnBoard(Long.parseLong(boardMessage.getTaskProgressId()));
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(BoardRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
