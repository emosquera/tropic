/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.delegate;

import com.tropicscrum.backend.client.dto.ProjectDTO;
import com.tropicscrum.backend.client.facade.ProjectFacadeRemote;
import com.tropicscrum.base.locator.AbstractDelegate;
import java.util.List;

/**
 *
 * @author syslife02
 */
public class ProjectDelegate extends AbstractDelegate<ProjectFacadeRemote> implements ProjectFacadeRemote {

    @Override
    public ProjectDTO create(ProjectDTO projectDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).create(projectDTO);
    }

    @Override
    public ProjectDTO edit(ProjectDTO projectDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).edit(projectDTO);
    }

    @Override
    public void remove(ProjectDTO projectDTO) {
        this.getDelegado(JNDI_REMOTE_NAME).remove(projectDTO);
    }

    @Override
    public ProjectDTO find(Object id) {
        return this.getDelegado(JNDI_REMOTE_NAME).find(id);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return this.getDelegado(JNDI_REMOTE_NAME).findAll();
    }

    @Override
    public List<ProjectDTO> findRange(int[] range) {
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
