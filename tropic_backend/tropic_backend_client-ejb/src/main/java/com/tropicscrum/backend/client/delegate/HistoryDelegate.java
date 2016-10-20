/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.delegate;

import com.tropicscrum.backend.client.dto.HistoryDTO;
import com.tropicscrum.backend.client.facade.HistoryFacadeRemote;
import com.tropicscrum.base.locator.AbstractDelegate;
import java.util.List;

/**
 *
 * @author syslife02
 */
public class HistoryDelegate extends AbstractDelegate<HistoryFacadeRemote> implements HistoryFacadeRemote {

    @Override
    public HistoryDTO create(HistoryDTO historyDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).create(historyDTO);
    }

    @Override
    public HistoryDTO edit(HistoryDTO historyDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).edit(historyDTO);
    }

    @Override
    public void remove(HistoryDTO historyDTO) {
        this.getDelegado(JNDI_REMOTE_NAME).remove(historyDTO);
    }

    @Override
    public HistoryDTO find(Object id) {
        return this.getDelegado(JNDI_REMOTE_NAME).find(id);
    }

    @Override
    public List<HistoryDTO> findAll() {
        return this.getDelegado(JNDI_REMOTE_NAME).findAll();
    }

    @Override
    public List<HistoryDTO> findRange(int[] range) {
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
