/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.delegate;

import com.tropicscrum.backend.client.dto.UserScheduleDTO;
import com.tropicscrum.backend.client.facade.UserScheduleFacadeRemote;
import com.tropicscrum.base.locator.AbstractDelegate;
import java.util.List;

/**
 *
 * @author syslife02
 */
public class UserScheduleDelegate extends AbstractDelegate<UserScheduleFacadeRemote> implements UserScheduleFacadeRemote{

    @Override
    public UserScheduleDTO create(UserScheduleDTO userScheduleDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).create(userScheduleDTO);
    }

    @Override
    public UserScheduleDTO edit(UserScheduleDTO userScheduleDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).edit(userScheduleDTO);
    }

    @Override
    public void remove(UserScheduleDTO userScheduleDTO) {
        this.getDelegado(JNDI_REMOTE_NAME).remove(userScheduleDTO);
    }

    @Override
    public UserScheduleDTO find(Object id) {
        return this.getDelegado(JNDI_REMOTE_NAME).find(id);
    }

    @Override
    public List<UserScheduleDTO> findAll() {
        return this.getDelegado(JNDI_REMOTE_NAME).findAll();
    }

    @Override
    public List<UserScheduleDTO> findRange(int[] range) {
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
