/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.TaskProgress;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless
public class TaskProgressFacade extends AbstractFacade<TaskProgress> implements TaskProgressFacadeLocal {

    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskProgressFacade() {
        super(TaskProgress.class);
    }

    @Override
    public Collection<TaskProgress> findAllProgressByTask(Task task) {
        return em.createNamedQuery("findAllProgressByTask").setParameter("task", task).getResultList();
    }

    @Override
    public Collection<TaskProgress> findAllProgressInProgressBySprintUser(SprintUser sprintUser) {
        return em.createNamedQuery("findAllProgressInProgressBySprintUser").setParameter("sprintUser", sprintUser).getResultList();
    }
    
}
