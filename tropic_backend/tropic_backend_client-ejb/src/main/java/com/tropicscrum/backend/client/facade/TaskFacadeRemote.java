/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.Artifact;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.User;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */

@Remote
public interface TaskFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb:tropic_backend-ear-1.0-SNAPSHOT/tropic_backend_persistence-ejb-1.0-SNAPSHOT/taskFacadeRemote!com.tropicscrum.backend.client.facade.TaskFacadeRemote";

    //public final String JNDI_REMOTE_NAME = "ejb/taskFacadeRemote";
    
    Task create(Task task);

    Task edit(Task task) throws UpdateException;

    void remove(Task task);

    Task find(Object id);

    Collection<Task> findAll();

    Collection<Task> findRange(int[] range);

    int count();
    
    Collection<Task> findMyTasks(User you);
    
    Collection<Task> findMyCollabs(User you);
    
    Collection<Task> findSprintTasks(Sprint sprint);
    
    Collection<Task> findArtifactTasks(Artifact artifact);
    
    Collection<Task> findSprintTasksWithProgress(Sprint sprint);
    
    void startTaskProgress(Task task, SprintUser sprintUser) throws UpdateException;    
}
