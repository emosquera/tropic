/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.enums.ScrumRole;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.User;
import java.util.Collection;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless
public class SprintUserFacade extends AbstractFacade<SprintUser> implements SprintUserFacadeLocal {

    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SprintUserFacade() {
        super(SprintUser.class);
    }

    @Override
    public Collection<SprintUser> findBySprint(Sprint sprint) {
        return em.createNamedQuery("findAllSprintUserBySprint").setParameter("sprint", sprint).getResultList();
    }

    @Override
    public SprintUser findBySprintAndUserAndRole(Sprint sprint, User user, ScrumRole role) {
        try {
        return (SprintUser) em.createNamedQuery("findAllSprintUserBySprintAndUserAndRole").setParameter("sprint", sprint).setParameter("user", user).setParameter("role", role).getSingleResult();
        } catch (NoResultException e) {
            throw (EJBException) new EJBException(e).initCause(e);
        }
    }
    
}
