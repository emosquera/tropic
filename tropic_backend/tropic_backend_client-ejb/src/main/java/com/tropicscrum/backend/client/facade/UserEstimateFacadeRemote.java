/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.model.UserEstimate;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */

@Remote
public interface UserEstimateFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb:tropic_backend-ear-1.0-SNAPSHOT/tropic_backend_persistence-ejb-1.0-SNAPSHOT/userEstimateFacadeRemote!com.tropicscrum.backend.client.facade.UserEstimateFacadeRemote";
    //public final String JNDI_REMOTE_NAME = "ejb/userEstimateFacadeRemote";
    
    UserEstimate create(UserEstimate userEstimate);

    UserEstimate edit(UserEstimate userEstimate);

    void remove(UserEstimate userEstimate);

    UserEstimate find(Object id);

    Collection<UserEstimate> findAll();

    Collection<UserEstimate> findRange(int[] range);

    int count();
}
