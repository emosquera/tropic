/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.SprintVelocity;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */
@Remote
public interface SprintVelocityFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb:tropic_backend-ear-1.0-SNAPSHOT/tropic_backend_persistence-ejb-1.0-SNAPSHOT/sprintVelocityFacadeRemote!com.tropicscrum.backend.client.facade.SprintVelocityFacadeRemote";
    //public final String JNDI_REMOTE_NAME = "ejb/sprintVelocityFacadeRemote";
    
    SprintVelocity create(SprintVelocity sprintVelocity);

    SprintVelocity edit(SprintVelocity sprintVelocity) throws UpdateException;

    void remove(SprintVelocity sprintVelocity);

    SprintVelocity find(Object id);

    Collection<SprintVelocity> findAll();

    Collection<SprintVelocity> findRange(int[] range);

    int count();
}
