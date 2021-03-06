/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Project;
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
public class HistoryFacade extends AbstractFacade<History> implements HistoryFacadeLocal {

    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoryFacade() {
        super(History.class);
    }

    @Override
    public Collection<History> findAllByUser(User user) {
        return em.createNamedQuery("findAllHistoriesByUser").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<History> findAllByCollaborator(User user) {
        return em.createNamedQuery("findAllHistoriesByCollaborator").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<History> findByProject(Project project) {
        return em.createNamedQuery("findByProject").setParameter("project", project).getResultList();
    }    

    @Override
    public Collection<History> findBySprint(Sprint sprint) {
        return em.createNamedQuery("findBySprint").setParameter("sprint", sprint).getResultList();
}
    
}
