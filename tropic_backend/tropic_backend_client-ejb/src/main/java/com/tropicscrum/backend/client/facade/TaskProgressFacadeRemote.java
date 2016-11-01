/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.model.TaskProgress;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface TaskProgressFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/taskProgressFacadeRemote";
    
    TaskProgress create(TaskProgress taskProgress);

    TaskProgress edit(TaskProgress taskProgress);

    void remove(TaskProgress taskProgress);

    TaskProgress find(Object id);

    List<TaskProgress> findAll();

    List<TaskProgress> findRange(int[] range);

    int count();
}
