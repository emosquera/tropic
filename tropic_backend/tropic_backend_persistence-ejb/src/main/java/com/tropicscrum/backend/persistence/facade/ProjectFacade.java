/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author syslife02
 */
@Stateless
public class ProjectFacade extends AbstractFacade<Project> implements ProjectFacadeLocal {

    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectFacade() {
        super(Project.class);
    }

    @Override
    public Collection<Project> findAllByUser(User user) {
        return em.createNamedQuery("findAllProjectsByUser").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<Project> findAllByCollaborator(User user) {
        return em.createNamedQuery("findAllProjectsByCollaborator").setParameter("user", user).getResultList();
    }
    
}
