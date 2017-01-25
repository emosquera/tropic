/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface ProjectFacadeLocal {

    void create(Project project);

    void edit(Project project) throws OldVersionException;

    void remove(Project project);

    Project find(Object id);

    Collection<Project> findAll();

    Collection<Project> findRange(int[] range);

    int count();
    
    Collection<Project> findAllByUser(User user);
    
    Collection<Project> findAllByCollaborator(User user);
    
}
