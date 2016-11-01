/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.model.UserEstimate;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface UserEstimateFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/userEstimateFacadeRemote";
    
    UserEstimate create(UserEstimate userEstimate);

    UserEstimate edit(UserEstimate userEstimate);

    void remove(UserEstimate userEstimate);

    UserEstimate find(Object id);

    List<UserEstimate> findAll();

    List<UserEstimate> findRange(int[] range);

    int count();
}
