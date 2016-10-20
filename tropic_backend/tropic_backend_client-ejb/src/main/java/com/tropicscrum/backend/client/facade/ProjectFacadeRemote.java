/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.dto.ProjectDTO;
import com.tropicscrum.base.locator.ServiceVerifier;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface ProjectFacadeRemote extends ServiceVerifier {
    public final String JNDI_REMOTE_NAME = "ejb/projectFacadeRemote";
    
    ProjectDTO create(ProjectDTO projectDTO);

    ProjectDTO edit(ProjectDTO projectDTO);

    void remove(ProjectDTO projectDTO);

    ProjectDTO find(Object id);

    List<ProjectDTO> findAll();

    List<ProjectDTO> findRange(int[] range);

    int count();
}
