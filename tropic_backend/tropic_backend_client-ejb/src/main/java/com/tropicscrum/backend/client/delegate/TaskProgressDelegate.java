/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.delegate;

import com.tropicscrum.backend.client.dto.TaskProgressDTO;
import com.tropicscrum.backend.client.facade.TaskProgressFacadeRemote;
import com.tropicscrum.base.locator.AbstractDelegate;
import java.util.List;

/**
 *
 * @author syslife02
 */
public class TaskProgressDelegate extends AbstractDelegate<TaskProgressFacadeRemote> implements TaskProgressFacadeRemote {

    @Override
    public TaskProgressDTO create(TaskProgressDTO taskProgressDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).create(taskProgressDTO);
    }

    @Override
    public TaskProgressDTO edit(TaskProgressDTO taskProgressDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).edit(taskProgressDTO);
    }

    @Override
    public void remove(TaskProgressDTO taskProgressDTO) {
        this.getDelegado(JNDI_REMOTE_NAME).remove(taskProgressDTO);
    }

    @Override
    public TaskProgressDTO find(Object id) {
        return this.getDelegado(JNDI_REMOTE_NAME).find(id);
    }

    @Override
    public List<TaskProgressDTO> findAll() {
        return this.getDelegado(JNDI_REMOTE_NAME).findAll();
    }

    @Override
    public List<TaskProgressDTO> findRange(int[] range) {
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
