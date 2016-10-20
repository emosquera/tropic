/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.dto.UserEstimateDTO;
import com.tropicscrum.base.locator.ServiceVerifier;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface UserEstimateFacadeRemote extends ServiceVerifier {
    public final String JNDI_REMOTE_NAME = "ejb/userEstimateFacadeRemote";
    
    UserEstimateDTO create(UserEstimateDTO userEstimateDTO);

    UserEstimateDTO edit(UserEstimateDTO userEstimateDTO);

    void remove(UserEstimateDTO userEstimateDTO);

    UserEstimateDTO find(Object id);

    List<UserEstimateDTO> findAll();

    List<UserEstimateDTO> findRange(int[] range);

    int count();
}
