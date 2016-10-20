/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.delegate;

import com.tropicscrum.backend.client.dto.UserEstimateDTO;
import com.tropicscrum.backend.client.facade.UserEstimateFacadeRemote;
import com.tropicscrum.base.locator.AbstractDelegate;
import java.util.List;

/**
 *
 * @author syslife02
 */
public class UserEstimateDelegate extends AbstractDelegate<UserEstimateFacadeRemote> implements UserEstimateFacadeRemote {

    @Override
    public UserEstimateDTO create(UserEstimateDTO userEstimateDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).create(userEstimateDTO);
    }

    @Override
    public UserEstimateDTO edit(UserEstimateDTO userEstimateDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).edit(userEstimateDTO);
    }

    @Override
    public void remove(UserEstimateDTO userEstimateDTO) {
        this.getDelegado(JNDI_REMOTE_NAME).remove(userEstimateDTO);
    }

    @Override
    public UserEstimateDTO find(Object id) {
        return this.getDelegado(JNDI_REMOTE_NAME).find(id);
    }

    @Override
    public List<UserEstimateDTO> findAll() {
        return this.getDelegado(JNDI_REMOTE_NAME).findAll();
    }

    @Override
    public List<UserEstimateDTO> findRange(int[] range) {
        return this.getDelegado(JNDI_REMOTE_NAME).findRange(range);
    }

    @Override
    public int count() {
        return this.getDelegado(JNDI_REMOTE_NAME).count();
    }

    @Override
    public boolean isAlive() {
        return this.getDelegado(JNDI_REMOTE_NAME).isAlive();
    }
    
}
