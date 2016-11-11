/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.facade.ProjectFacadeRemote;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.facade.ProjectFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */
@Stateless(name = "projectFacadeRemote", mappedName = ProjectFacadeRemote.JNDI_REMOTE_NAME)
@Remote(ProjectFacadeRemote.class)
public class ProjectBussinesFacade implements ProjectFacadeRemote {

    @EJB
    ProjectFacadeLocal projectFacadeLocal;
    
    @Override
    public Project create(Project project) {
        projectFacadeLocal.create(project);
        project.getCollaborators().size();
        return project;
    }

    @Override
    public Project edit(Project project) {
        projectFacadeLocal.edit(project);
        project.getCollaborators().size();
        return project;
    }

    @Override
    public void remove(Project project) {
        projectFacadeLocal.remove(project);
    }

    @Override
    public Project find(Object id) {
        Project p = projectFacadeLocal.find(id);
        p.getCollaborators().size();
        return p;
    }

    @Override
    public List<Project> findAll() {
        return projectFacadeLocal.findAll();
    }

    @Override
    public List<Project> findRange(int[] range) {
        return projectFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return projectFacadeLocal.count();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Project> findAllMine(User you) {
        List<Project> myProjects = projectFacadeLocal.findAllByUser(you);
        for (Project p : myProjects) {
            p.getCollaborators().size();
        }
        return myProjects;
    }
}
