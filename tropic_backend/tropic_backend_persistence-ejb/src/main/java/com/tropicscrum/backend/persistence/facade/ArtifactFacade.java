/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Artifact;
import com.tropicscrum.backend.client.model.Milestone;
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
public class ArtifactFacade extends AbstractFacade<Artifact> implements ArtifactFacadeLocal {

    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtifactFacade() {
        super(Artifact.class);
    }

    @Override
    public Collection<Artifact> findAllArtifactsByUser(User user) {
        return em.createNamedQuery("findAllArtifactsByUser").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<Artifact> findAllArtifactsByCollaborator(User user) {
        return em.createNamedQuery("findAllArtifactsByCollaborator").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<Artifact> findAllArtifactsByMilestone(Milestone milestone) {        
        return em.createNamedQuery("findAllArtifactsByMileston").setParameter("milestone", milestone).getResultList();
    }
    
}
