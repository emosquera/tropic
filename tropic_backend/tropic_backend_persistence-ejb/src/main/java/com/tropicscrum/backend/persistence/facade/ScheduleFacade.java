/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Schedule;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless
public class ScheduleFacade extends AbstractFacade<Schedule> implements ScheduleFacadeLocal {

    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScheduleFacade() {
        super(Schedule.class);
    }

    @Override
    public Collection<Schedule> findByRangeOfDayAndTime(int startDay, int endDay, Date startTime, Date endTime) {
        Collection<Schedule> schedules = em.createNamedQuery("findByRangeOfDayAndTime").setParameter("startDay", startDay).setParameter("endDay", endDay).setParameter("startTime", startTime).setParameter("endTime", endTime).getResultList();
        return schedules;
    }

    @Override
    public Collection<Schedule> findAllOrdered() {
        Collection<Schedule> schedules = em.createNamedQuery("findAllOrdered").getResultList();
        return schedules;
    }
    
}
