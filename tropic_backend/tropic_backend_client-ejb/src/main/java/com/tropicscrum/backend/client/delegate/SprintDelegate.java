/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.delegate;

import com.tropicscrum.backend.client.dto.SprintDTO;
import com.tropicscrum.backend.client.facade.SprintFacadeRemote;
import com.tropicscrum.base.locator.AbstractDelegate;
import java.util.List;

/**
 *
 * @author syslife02
 */
public class SprintDelegate extends AbstractDelegate<SprintFacadeRemote> implements SprintFacadeRemote {

    @Override
    public SprintDTO create(SprintDTO sprintDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).create(sprintDTO);
    }

    @Override
    public SprintDTO edit(SprintDTO sprintDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).edit(sprintDTO);
    }

    @Override
    public void remove(SprintDTO sprintDTO) {
        this.getDelegado(JNDI_REMOTE_NAME).remove(sprintDTO);
    }

    @Override
    public SprintDTO find(Object id) {
        return this.getDelegado(JNDI_REMOTE_NAME).find(id);
    }

    @Override
    public List<SprintDTO> findAll() {
        return this.getDelegado(JNDI_REMOTE_NAME).findAll();
    }

    @Override
    public List<SprintDTO> findRange(int[] range) {
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
