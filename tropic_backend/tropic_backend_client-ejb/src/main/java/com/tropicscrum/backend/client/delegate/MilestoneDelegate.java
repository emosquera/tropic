/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.delegate;

import com.tropicscrum.backend.client.dto.MilestoneDTO;
import com.tropicscrum.backend.client.facade.MilestoneFacadeRemote;
import com.tropicscrum.base.locator.AbstractDelegate;
import java.util.List;

/**
 *
 * @author syslife02
 */
public class MilestoneDelegate extends AbstractDelegate<MilestoneFacadeRemote> implements MilestoneFacadeRemote {

    @Override
    public MilestoneDTO create(MilestoneDTO milestoneDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).create(milestoneDTO);
    }

    @Override
    public MilestoneDTO edit(MilestoneDTO milestoneDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).edit(milestoneDTO);
    }

    @Override
    public void remove(MilestoneDTO milestoneDTO) {
        this.getDelegado(JNDI_REMOTE_NAME).remove(milestoneDTO);
    }

    @Override
    public MilestoneDTO find(Object id) {
        return this.getDelegado(JNDI_REMOTE_NAME).find(id);
    }

    @Override
    public List<MilestoneDTO> findAll() {
        return this.getDelegado(JNDI_REMOTE_NAME).findAll();
    }

    @Override
    public List<MilestoneDTO> findRange(int[] range) {
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
