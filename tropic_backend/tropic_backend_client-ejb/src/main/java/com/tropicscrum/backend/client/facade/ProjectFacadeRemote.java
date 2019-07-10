/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */

@Remote
public interface ProjectFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb:tropic_backend-ear-1.0-SNAPSHOT/tropic_backend_persistence-ejb-1.0-SNAPSHOT/projectFacadeRemote!com.tropicscrum.backend.client.facade.ProjectFacadeRemote";
    //public final String JNDI_REMOTE_NAME = "ejb/projectFacadeRemote";
    
    Project create(Project project);

    Project edit(Project project) throws UpdateException;

    void remove(Project project);

    Project find(Object id);

    Collection<Project> findAll();

    Collection<Project> findRange(int[] range);

    int count();
    
    Collection<Project> findAllMine(User you);
    
    Collection<Project> findAllMyCollabs(User you);
}
