/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.delegate;

import com.tropicscrum.backend.client.dto.SprintUserDTO;
import com.tropicscrum.backend.client.facade.SprintUserFacadeRemote;
import com.tropicscrum.base.locator.AbstractDelegate;
import java.util.List;

/**
 *
 * @author syslife02
 */
public class SprintUserDelegate extends AbstractDelegate<SprintUserFacadeRemote> implements SprintUserFacadeRemote {

    @Override
    public SprintUserDTO create(SprintUserDTO sprintUserDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).create(sprintUserDTO);
    }

    @Override
    public SprintUserDTO edit(SprintUserDTO sprintUserDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).edit(sprintUserDTO);
    }

    @Override
    public void remove(SprintUserDTO sprintUserDTO) {
        this.getDelegado(JNDI_REMOTE_NAME).remove(sprintUserDTO);
    }

    @Override
    public SprintUserDTO find(Object id) {
        return this.getDelegado(JNDI_REMOTE_NAME).find(id);
    }

    @Override
    public List<SprintUserDTO> findAll() {
        return this.getDelegado(JNDI_REMOTE_NAME).findAll();
    }

    @Override
    public List<SprintUserDTO> findRange(int[] range) {
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
