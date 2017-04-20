/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.TaskFacadeRemote;
import com.tropicscrum.backend.client.model.Artifact;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.TaskProgress;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.ArtifactFacadeLocal;
import com.tropicscrum.backend.persistence.facade.HistoryFacadeLocal;
import com.tropicscrum.backend.persistence.facade.MilestoneFacadeLocal;
import com.tropicscrum.backend.persistence.facade.TaskFacadeLocal;
import com.tropicscrum.backend.persistence.facade.TaskProgressFacadeLocal;
import java.util.Calendar;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless(name = "taskFacadeRemote", mappedName = TaskFacadeRemote.JNDI_REMOTE_NAME)
@Remote(TaskFacadeRemote.class)
public class TaskBusinessFacade implements TaskFacadeRemote {

    @EJB
    TaskFacadeLocal taskFacadeLocal;
    
    @EJB
    TaskProgressFacadeLocal taskProgressFacadeLocal;

    @EJB
    HistoryFacadeLocal historyFacadeLocal;
    
    @EJB
    MilestoneFacadeLocal milestoneFacadeLocal;
    
    @EJB
    ArtifactFacadeLocal artifactFacadeLocal;
    
    @Override
    public Task create(Task task) {
        taskFacadeLocal.create(task);
        return task;
    }

    @Override
    public Task edit(Task task) throws UpdateException {
        try {
            taskFacadeLocal.edit(task);
            return task;
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar la Tarea. Esta ha sido modificada en otra sesion. Se muestra la Tarea actualizada");
        }
    }

    @Override
    public void remove(Task task) {
        taskFacadeLocal.remove(task);
    }

    @Override
    public Task find(Object id) {
        return taskFacadeLocal.find(id);
    }

    @Override
    public Collection<Task> findAll() {
        return taskFacadeLocal.findAll();
    }

    @Override
    public Collection<Task> findRange(int[] range) {
        return taskFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return taskFacadeLocal.count();
    }

    @Override
    public Collection<Task> findMyTasks(User you) {        
        return deepTask(taskFacadeLocal.findAllTasksByUser(you));
    }

    @Override
    public Collection<Task> findMyCollabs(User you) {
        return deepTask(taskFacadeLocal.findAllTasksByCollaborator(you));
    }

    @Override
    public Collection<Task> findSprintTasks(Sprint sprint) {
        return taskFacadeLocal.findAllTasksBySprint(sprint);
    }

    @Override
    public Collection<Task> findArtifactTasks(Artifact artifact) {
        Collection<Task> myTasks = taskFacadeLocal.findAllTasksByArtifact(artifact);
        for (Task task : myTasks) {
            task.getUserEstimates().size();
        }
        return myTasks;
    }

    @Override
    public Collection<Task> findSprintTasksWithProgress(Sprint sprint) {
        Collection<Task> myTasks = taskFacadeLocal.findAllTasksBySprint(sprint);
        for (Task task : myTasks) {
            task.getTaskProgresss().size();
        }
        return myTasks;
    }

    @Override
    public void startTaskProgress(Task task, SprintUser sprintUser) throws UpdateException {
        Collection<TaskProgress> myInProgress = taskProgressFacadeLocal.findAllProgressInProgressBySprintUser(sprintUser);
        for (TaskProgress tp : myInProgress) {
            if (tp.getFinalDate() == null) {
                tp.setFinalDate(Calendar.getInstance());
                double timeElapsed = tp.getFinalDate().getTimeInMillis() - tp.getDateExecution().getTimeInMillis();
                tp.setTimeInProgress(timeElapsed);
                tp.setFinalStatus(GeneralStatus.PENDING);
                try {
                    taskProgressFacadeLocal.edit(tp);
                } catch (OldVersionException ex) {
                    throw new UpdateException("No se puede actualizar el Progreso. Este ha sido modificado en otra sesion.");
                }
            }            
            tp.getTask().setStatus(GeneralStatus.PENDING);
            try {                
                taskFacadeLocal.edit(tp.getTask());
            } catch (OldVersionException ex) {
                throw new UpdateException("No se puede actualizar la Tarea del Progreso. Esta ha sido modificada en otra sesion.");
            }
        }
        task.setStatus(GeneralStatus.IN_PROGRESS);
        TaskProgress taskProgress = new TaskProgress();
        taskProgress.setTask(task);
        taskProgress.setDateExecution(Calendar.getInstance());
        taskProgress.setStartEstatus(GeneralStatus.IN_PROGRESS);
        taskProgress.setSprintUser(sprintUser);
        task.setTaskProgresss(taskProgressFacadeLocal.findAllProgressByTask(task));
        task.getTaskProgresss().add(taskProgress);
        try {
            taskFacadeLocal.edit(task);
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar la Tarea. Esta ha sido modificada en otra sesion. Se muestra la Tarea actualizada");
        }
    }
    
    private Collection<Task> deepTask(Collection<Task> tasks) {
        for (Task myTask : tasks) {
            myTask.getArtifact().getMilestone().getSprint().getProject().setHistories(historyFacadeLocal.findBySprint(myTask.getArtifact().getMilestone().getSprint()));
            for (History h : myTask.getArtifact().getMilestone().getSprint().getProject().getHistories()) {
                h.setMilestones(milestoneFacadeLocal.findByHistory(h));
                for (Milestone milestone : h.getMilestones()) {
                    milestone.setArtifacts(artifactFacadeLocal.findAllArtifactsByMilestone(milestone));
                }
            }
        }
        return tasks;
    }
}
