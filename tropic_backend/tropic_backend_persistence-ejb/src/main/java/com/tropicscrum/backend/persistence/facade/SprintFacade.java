/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.User;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless
public class SprintFacade extends AbstractFacade<Sprint> implements SprintFacadeLocal {
    
    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SprintFacade() {
        super(Sprint.class);
    }

    @Override
    public Collection<Sprint> findAllByUser(User user) {
        return em.createNamedQuery("findAllSprintsByUser").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<Sprint> findAllByCollaborator(User user) {
        return em.createNamedQuery("findAllSprintsByCollaborator").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<Sprint> findAllSprintsByUserCanCreateTask(User user) {
        return em.createNamedQuery("findAllSprintsByUserCanCreateTask").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<Sprint> findAllSprintsBySprintUser(User user) {        
        return em.createNamedQuery("findAllSprintsBySprintUser").setParameter("user", user).getResultList();
    }
}
