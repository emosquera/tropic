/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.SprintUserFacadeRemote;
import com.tropicscrum.backend.client.facade.TaskFacadeRemote;
import com.tropicscrum.backend.client.facade.TaskProgressFacadeRemote;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.TaskProgress;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import com.tropicscrum.frontend.controllers.view.BoardViewBean;
import com.tropicscrum.frontend.push.model.BoardMessage;
import com.tropicscrum.frontend.push.resource.BoardMessageResource;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "boardRequestBean")
@RequestScoped
public class BoardRequestBean implements Serializable {
    
    private TaskProgress taskProgressClicked;

    private final Comparator<Task> comparatorTask = (Task t1, Task t2) -> t1.getCode().compareTo(t2.getCode());

    private Collection<Long> users;

    @Inject
    BoardViewBean boardViewBean;
    
    @Inject
    BoardMessageResource boardMessageResource;

    TaskProgressFacadeRemote taskProgressFacadeRemote = new ServiceLocatorDelegate<TaskProgressFacadeRemote>().getService(TaskProgressFacadeRemote.JNDI_REMOTE_NAME);

    TaskFacadeRemote taskFacadeRemote = new ServiceLocatorDelegate<TaskFacadeRemote>().getService(TaskFacadeRemote.JNDI_REMOTE_NAME);

    SprintUserFacadeRemote sprintUserFacadeRemote = new ServiceLocatorDelegate<SprintUserFacadeRemote>().getService(SprintUserFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    ExternalContext extContext;

    public TaskProgress getTaskProgressClicked() {
        if (taskProgressClicked == null) {
            taskProgressClicked = new TaskProgress();
        }
        return taskProgressClicked;
    }

    public void setTaskProgressClicked(TaskProgress taskProgressClicked) {
        this.taskProgressClicked = taskProgressClicked;
    }
    
    public Collection<Long> getUsers() {
        users = new ArrayList<>();
        List<SprintUser> otherUsers = boardViewBean.getSprint().getSprintUsers().stream().filter((su) -> (!su.getUser().equals(boardViewBean.getUser()))).collect(Collectors.toList());
        otherUsers.forEach(su->users.add(su.getId()));
        return users;
    }

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

    private void publishNotification(String action, String taskId, String taskProgressId, SprintUser sprintUser) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            BoardMessage boardMessage = new BoardMessage(action, taskProgressId);
            boardMessage.setSprintUserId(sprintUser.getId().toString());
            boardMessage.setTaskId(taskId);
            String jsonMessage = mapper.writeValueAsString(boardMessage);
                        
            boardMessageResource.getPush().send(jsonMessage, this.getUsers());
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(BoardRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void publishNotification(String action, String taskProgressId, SprintUser sprintUser) {
        publishNotification(action, "", taskProgressId, sprintUser);
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
        taskProgress.getTask().setTaskProgresss(taskProgressFacadeRemote.findTaskActivity(taskProgress.getTask()));
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
            String jsonMessage = extContext.getRequestParameterMap().get("boardMessage");
            BoardMessage boardMessage = mapper.readValue(jsonMessage.replace("\"{","{").replace("\\",""), BoardMessage.class);
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
                    
                case "pause":
                    pauseTaskOnBoard(Long.parseLong(boardMessage.getTaskProgressId()));
                    break;
                    
                case "play":
                    playTaskOnBoard(Long.parseLong(boardMessage.getTaskId()), Long.parseLong(boardMessage.getTaskProgressId()));
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(BoardRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playTask() {
        TaskProgress taskProgress = new TaskProgress();
        taskProgress.setDateExecution(Calendar.getInstance());
        taskProgress.setStartEstatus(GeneralStatus.IN_PROGRESS);
        taskProgress.setTask(getTaskProgressClicked().getTask());
        taskProgress.setSprintUser(boardViewBean.getSprintUser());
        taskProgress = taskProgressFacadeRemote.create(taskProgress);
        boardViewBean.getTaskProgresss().remove(getTaskProgressClicked());
        boardViewBean.getTaskProgresss().add(taskProgress);
        publishNotification("play", getTaskProgressClicked().getId().toString(), taskProgress.getId().toString(), boardViewBean.getSprintUser());
    }
    
    private void playTaskOnBoard(Long taskProgressToRemoveId, Long taskProgressToAddId) {
        TaskProgress taskProgressToRemove = taskProgressFacadeRemote.find(taskProgressToRemoveId);
        boardViewBean.getTaskProgresss().remove(taskProgressToRemove);
        TaskProgress taskProgressToAdd = taskProgressFacadeRemote.find(taskProgressToAddId);
        boardViewBean.getTaskProgresss().add(taskProgressToAdd);
    }

    public void pauseTask() {
        try {
            getTaskProgressClicked().setFinalDate(Calendar.getInstance());
            double timeElapsed = getTaskProgressClicked().getFinalDate().getTimeInMillis() - getTaskProgressClicked().getDateExecution().getTimeInMillis();
            getTaskProgressClicked().setTimeInProgress(timeElapsed);
            getTaskProgressClicked().setFinalStatus(GeneralStatus.IN_PROGRESS);
            taskProgressFacadeRemote.edit(getTaskProgressClicked());
            boardViewBean.getTaskProgresss().remove(getTaskProgressClicked());
            boardViewBean.getTaskProgresss().add(taskProgressFacadeRemote.find(getTaskProgressClicked().getId()));
            publishNotification("pause", getTaskProgressClicked().getId().toString(), boardViewBean.getSprintUser());
        } catch (UpdateException ex) {
            Logger.getLogger(BoardRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void pauseTaskOnBoard(Long taskProgressId) {
        TaskProgress taskProgress = taskProgressFacadeRemote.find(taskProgressId);        
        boardViewBean.getTaskProgresss().remove(taskProgress);
        boardViewBean.getTaskProgresss().add(taskProgress);
    }
}
