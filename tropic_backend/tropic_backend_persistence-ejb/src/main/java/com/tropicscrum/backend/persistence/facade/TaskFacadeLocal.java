/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Edgar Mosquera
 */
@Local
public interface TaskFacadeLocal {

    void create(Task task);

    void edit(Task task) throws OldVersionException;

    void remove(Task task);

    Task find(Object id);

    Collection<Task> findAll();

    Collection<Task> findRange(int[] range);

    int count();
    
    Collection<Task> findAllTasksByUser(User user);
    
    Collection<Task> findAllTasksByCollaborator(User user);
    
    Collection<Task> findAllTasksBySprint(Sprint sprint);
    
    Collection<Task> findAllTasksByMilestonAndSprint(Milestone milestone, Sprint sprint);
}
