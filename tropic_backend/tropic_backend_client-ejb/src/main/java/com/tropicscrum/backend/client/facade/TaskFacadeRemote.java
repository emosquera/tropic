/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.dto.TaskDTO;
import com.tropicscrum.base.locator.ServiceVerifier;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface TaskFacadeRemote extends ServiceVerifier {
    public final String JNDI_REMOTE_NAME = "ejb/taskFacadeRemote";
    
    TaskDTO create(TaskDTO taskDTO);

    TaskDTO edit(TaskDTO taskDTO);

    void remove(TaskDTO taskDTO);

    TaskDTO find(Object id);

    List<TaskDTO> findAll();

    List<TaskDTO> findRange(int[] range);

    int count();
}
