/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.TaskProgress;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Edgar Mosquera
 */
@Local
public interface TaskProgressFacadeLocal {

    void create(TaskProgress taskProgress);

    void edit(TaskProgress taskProgress) throws OldVersionException;

    void remove(TaskProgress taskProgress);

    TaskProgress find(Object id);

    Collection<TaskProgress> findAll();

    Collection<TaskProgress> findRange(int[] range);

    int count();
    
    Collection<TaskProgress> findAllProgressByTask(Task task);
    
    Collection<TaskProgress> findAllProgressInProgressBySprintUser(SprintUser sprintUser);
}
