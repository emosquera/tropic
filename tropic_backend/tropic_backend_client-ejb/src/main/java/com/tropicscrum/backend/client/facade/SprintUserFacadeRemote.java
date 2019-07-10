/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.User;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */

@Remote
public interface SprintUserFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb:tropic_backend-ear-1.0-SNAPSHOT/tropic_backend_persistence-ejb-1.0-SNAPSHOT/sprintUserFacadeRemote!com.tropicscrum.backend.client.facade.SprintUserFacadeRemote";
    //public final String JNDI_REMOTE_NAME = "ejb/sprintUserFacadeRemote";
    
    SprintUser create(SprintUser sprintUser);

    SprintUser edit(SprintUser sprintUser) throws UpdateException;

    void remove(SprintUser sprintUser);

    SprintUser find(Object id);

    Collection<SprintUser> findAll();

    Collection<SprintUser> findRange(int[] range);

    int count();
    
    Collection<SprintUser> findSprintTeam(Sprint sprint);
    
    Boolean isSprintUserScrumMaster(Sprint sprint, User user);
}
