/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.model.Task;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface TaskFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/taskFacadeRemote";
    
    Task create(Task task);

    Task edit(Task task);

    void remove(Task task);

    Task find(Object id);

    Collection<Task> findAll();

    Collection<Task> findRange(int[] range);

    int count();
}
