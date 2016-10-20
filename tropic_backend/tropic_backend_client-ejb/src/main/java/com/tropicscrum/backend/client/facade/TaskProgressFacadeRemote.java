/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.dto.TaskProgressDTO;
import com.tropicscrum.base.locator.ServiceVerifier;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface TaskProgressFacadeRemote extends ServiceVerifier {
    public final String JNDI_REMOTE_NAME = "ejb/taskProgressFacadeRemote";
    
    TaskProgressDTO create(TaskProgressDTO taskProgressDTO);

    TaskProgressDTO edit(TaskProgressDTO taskProgressDTO);

    void remove(TaskProgressDTO taskProgressDTO);

    TaskProgressDTO find(Object id);

    List<TaskProgressDTO> findAll();

    List<TaskProgressDTO> findRange(int[] range);

    int count();
}
