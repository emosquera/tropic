/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Artifact;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.Task;
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
public class TaskFacade extends AbstractFacade<Task> implements TaskFacadeLocal {

    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskFacade() {
        super(Task.class);
    }

    @Override
    public Collection<Task> findAllTasksByUser(User user) {
        return em.createNamedQuery("findAllTasksByUser").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<Task> findAllTasksByCollaborator(User user) {
        return em.createNamedQuery("findAllTasksByCollaborator").setParameter("user", user).getResultList();
    }

    @Override
    public Collection<Task> findAllTasksBySprint(Sprint sprint) {
        return em.createNamedQuery("findAllTasksBySprint").setParameter("sprint", sprint).getResultList();
    }

    @Override
    public Collection<Task> findAllTasksByArtifact(Artifact artifact) {
        return em.createNamedQuery("findAllTasksByArtifact").setParameter("artifact", artifact).getResultList();
    }
    
}
