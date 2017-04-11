/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
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
public class MilestoneFacade extends AbstractFacade<Milestone> implements MilestoneFacadeLocal {

    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MilestoneFacade() {
        super(Milestone.class);
    }

    @Override
    public Collection<Milestone> findAllByUser(User user) {
        return em.createNamedQuery("findAllMilestonesByUser").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<Milestone> findAllByCollaborator(User user) {
        return em.createNamedQuery("findAllMielstoneByCollaborator").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<Milestone> findByHistory(History history) {
        return em.createNamedQuery("findByHistory").setParameter("history", history).getResultList();
    }

    @Override
    public Collection<Milestone> findByHistoryAndSprint(History history, Sprint sprint) {
        return em.createNamedQuery("findByHistoryAndSprint").setParameter("history", history).setParameter("sprint", sprint).getResultList();
    }

    @Override
    public Collection<Milestone> findBySprint(Sprint sprint) {
        return em.createNamedQuery("findMilestonesBySprint").setParameter("sprint", sprint).getResultList();
    }
    
}
