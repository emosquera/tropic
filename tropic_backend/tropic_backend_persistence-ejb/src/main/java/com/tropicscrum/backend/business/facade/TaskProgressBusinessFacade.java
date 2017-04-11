/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.TaskProgressFacadeRemote;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.TaskProgress;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
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
@Stateless(name = "taskProgressFacadeRemote", mappedName = TaskProgressFacadeRemote.JNDI_REMOTE_NAME)
@Remote(TaskProgressFacadeRemote.class)
public class TaskProgressBusinessFacade implements TaskProgressFacadeRemote {

    @EJB
    TaskProgressFacadeLocal taskProgressFacadeLocal;

    @EJB
    TaskFacadeLocal taskFacadeLocal;

    @Override
    public TaskProgress create(TaskProgress taskProgress) {
        taskProgressFacadeLocal.create(taskProgress);
        return taskProgress;
    }

    @Override
    public TaskProgress edit(TaskProgress taskProgress) throws UpdateException {
        try {
            taskProgressFacadeLocal.edit(taskProgress);
            return taskProgress;
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar la Tarea. Esta ha sido modificada en otra sesion. Se muestra la Tarea actualizada");
        }
    }

    @Override
    public void remove(TaskProgress taskProgress) {
        taskProgressFacadeLocal.remove(taskProgress);
    }

    @Override
    public TaskProgress find(Object id) {
        return taskProgressFacadeLocal.find(id);
    }

    @Override
    public Collection<TaskProgress> findAll() {
        return taskProgressFacadeLocal.findAll();
    }

    @Override
    public Collection<TaskProgress> findRange(int[] range) {
        return taskProgressFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return taskProgressFacadeLocal.count();
    }

    @Override
    public Collection<TaskProgress> findTaskActivity(Task task) {
        return taskProgressFacadeLocal.findAllProgressByTask(task);
    }

    @Override
    public void closeTaskProgress(TaskProgress taskProgress) throws UpdateException {
        taskProgress.setFinalDate(Calendar.getInstance());
        taskProgress.setFinalStatus(GeneralStatus.FINISHED);
        taskProgress.getTask().setStatus(GeneralStatus.FINISHED);
        try {
            taskProgressFacadeLocal.edit(taskProgress);
            taskFacadeLocal.edit(taskProgress.getTask());
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar el Progreso. Este ha sido modificada en otra sesion.");
        }
    }

    @Override
    public void stopTaskProgres(TaskProgress taskProgress) throws UpdateException {
        taskProgress.setFinalStatus(GeneralStatus.PENDING);
        taskProgress.getTask().setStatus(GeneralStatus.PENDING);
        try {
            taskProgressFacadeLocal.edit(taskProgress);
            taskFacadeLocal.edit(taskProgress.getTask());
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar el Progreso. Este ha sido modificada en otra sesion.");
        }
    }

}
