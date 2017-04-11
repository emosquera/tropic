/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.TaskProgress;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */

@Remote
public interface TaskProgressFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/taskProgressFacadeRemote";
    
    TaskProgress create(TaskProgress taskProgress);

    TaskProgress edit(TaskProgress taskProgress) throws UpdateException;

    void remove(TaskProgress taskProgress);

    TaskProgress find(Object id);

    Collection<TaskProgress> findAll();

    Collection<TaskProgress> findRange(int[] range);

    int count();
    
    Collection<TaskProgress> findTaskActivity(Task task);
    
    void closeTaskProgress(TaskProgress taskProgress) throws UpdateException;
    
    void stopTaskProgres(TaskProgress taskProgress) throws UpdateException;
}
