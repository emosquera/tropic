/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface ProjectFacadeLocal {

    void create(Project project);

    void edit(Project project);

    void remove(Project project);

    Project find(Object id);

    List<Project> findAll();

    List<Project> findRange(int[] range);

    int count();
    
    List<Project> findAllByUser(User user);
    
}
