/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.delegate;

import com.tropicscrum.backend.client.dto.TaskDTO;
import com.tropicscrum.backend.client.facade.TaskFacadeRemote;
import com.tropicscrum.base.locator.AbstractDelegate;
import java.util.List;

/**
 *
 * @author syslife02
 */
public class TaskDelegate extends AbstractDelegate<TaskFacadeRemote> implements TaskFacadeRemote {

    @Override
    public TaskDTO create(TaskDTO taskDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).create(taskDTO);
    }

    @Override
    public TaskDTO edit(TaskDTO taskDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).edit(taskDTO);
    }

    @Override
    public void remove(TaskDTO taskDTO) {
        this.getDelegado(JNDI_REMOTE_NAME).remove(taskDTO);
    }

    @Override
    public TaskDTO find(Object id) {
        return this.getDelegado(JNDI_REMOTE_NAME).find(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return this.getDelegado(JNDI_REMOTE_NAME).findAll();
    }

    @Override
    public List<TaskDTO> findRange(int[] range) {
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
