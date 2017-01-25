/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.ProjectFacadeRemote;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.ProjectFacadeLocal;
import java.util.Collection;
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
    public Project edit(Project project) throws UpdateException {
        try {
            projectFacadeLocal.edit(project);
            project.getCollaborators().size();
            return project;
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar el Proyecto. Este ha sido modificado en otra sesion. Se muestra el Proyecto actualizado");
        }
        
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
    public Collection<Project> findAll() {
        return projectFacadeLocal.findAll();
    }

    @Override
    public Collection<Project> findRange(int[] range) {
        return projectFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return projectFacadeLocal.count();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Collection<Project> findAllMine(User you) {
        Collection<Project> myProjects = projectFacadeLocal.findAllByUser(you);
        for (Project p : myProjects) {
            p.getCollaborators().size();
        }
        return myProjects;
    }

    @Override
    public Collection<Project> findAllMyCollabs(User you) {
        Collection<Project> collabProjects = projectFacadeLocal.findAllByCollaborator(you);
        for (Project p : collabProjects) {
            p.getCollaborators().size();
        }
        return collabProjects;
    }
}
