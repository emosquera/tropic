/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.application;

import com.tropicscrum.backend.client.facade.ScheduleFacadeRemote;
import com.tropicscrum.backend.client.model.Schedule;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author syslife02
 */
@Named(value = "scheduleAppBean")
@ApplicationScoped
public class ScheduleAppBean implements Serializable {

    private Collection<Schedule> schedule;
    
    @EJB(lookup = ScheduleFacadeRemote.JNDI_REMOTE_NAME)
    ScheduleFacadeRemote scheduleFacadeRemote;

    public Collection<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(Collection<Schedule> schedule) {
        this.schedule = schedule;
    }
    /**
     * Creates a new instance of ScheduleAppBean
     */
    public ScheduleAppBean() {
    }
    
    @PostConstruct
    public void init() {
        setSchedule(scheduleFacadeRemote.findAllOrderByStart());
    }
}
