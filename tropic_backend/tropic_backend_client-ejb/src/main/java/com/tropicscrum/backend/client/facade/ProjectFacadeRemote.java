/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface ProjectFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/projectFacadeRemote";
    
    Project create(Project project);

    Project edit(Project project) throws UpdateException;

    void remove(Project project);

    Project find(Object id);

    List<Project> findAll();

    List<Project> findRange(int[] range);

    int count();
    
    List<Project> findAllMine(User you);
    
    List<Project> findAllMyCollabs(User you);
}
