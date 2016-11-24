/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author syslife02
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
    public List<History> findAllByUser(User user) {
        return em.createNamedQuery("findAllHistoriesByUser").setParameter("user", user).getResultList();
    }

    @Override
    public List<History> findAllByCollaborator(User user) {
        return em.createNamedQuery("findAllHistoriesByCollaborator").setParameter("user", user).getResultList();
    }

    @Override
    public List<History> findByProject(Project project) {
        return em.createNamedQuery("findByProject").setParameter("project", project).getResultList();
    }
    
}
