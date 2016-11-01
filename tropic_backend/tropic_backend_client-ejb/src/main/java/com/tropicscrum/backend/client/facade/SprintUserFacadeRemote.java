/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.model.SprintUser;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface SprintUserFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/sprintUserFacadeRemote";
    
    SprintUser create(SprintUser sprintUser);

    SprintUser edit(SprintUser sprintUser);

    void remove(SprintUser sprintUser);

    SprintUser find(Object id);

    List<SprintUser> findAll();

    List<SprintUser> findRange(int[] range);

    int count();
}
