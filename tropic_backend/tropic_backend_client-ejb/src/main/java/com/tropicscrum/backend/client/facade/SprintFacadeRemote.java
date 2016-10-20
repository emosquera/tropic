/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.dto.SprintDTO;
import com.tropicscrum.base.locator.ServiceVerifier;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface SprintFacadeRemote extends ServiceVerifier {
    public final String JNDI_REMOTE_NAME = "ejb/sprintFacadeRemote";
    
    SprintDTO create(SprintDTO sprintDTO);

    SprintDTO edit(SprintDTO sprintDTO);

    void remove(SprintDTO sprintDTO);

    SprintDTO find(Object id);

    List<SprintDTO> findAll();

    List<SprintDTO> findRange(int[] range);

    int count();
}
