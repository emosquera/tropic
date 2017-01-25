/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.ScheduleFacadeRemote;
import com.tropicscrum.backend.client.model.Schedule;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.ScheduleFacadeLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author syslife02
 */

@Stateless(name = "scheduleBusinessFacade", mappedName = ScheduleFacadeRemote.JNDI_REMOTE_NAME)
@Remote(ScheduleFacadeRemote.class)
public class ScheduleBusinessFacade implements ScheduleFacadeRemote{

    @EJB
    ScheduleFacadeLocal scheduleFacadeLocal;
    
    @Override
    public Schedule create(Schedule schedule) {
        scheduleFacadeLocal.create(schedule);
        return schedule;
    }

    @Override
    public Schedule edit(Schedule schedule) throws UpdateException {
        try {
            scheduleFacadeLocal.edit(schedule);            
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar el Sprint. Este ha sido modificado en otra sesion. Se muestra el Sprint actualizado");
        }
        return schedule;
    }

    @Override
    public void remove(Schedule schedule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Schedule find(Object id) {
        return scheduleFacadeLocal.find(id);
    }

    @Override
    public Collection<Schedule> findAll() {
        return scheduleFacadeLocal.findAll();
    }

    @Override
    public Collection<Schedule> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Schedule> getDefaultByDay(int dayOfWeek) {
        try {
            Collection<Schedule> schedules = new ArrayList<>();
            Date start = new SimpleDateFormat("hh:mm:ss a").parse("08:00:00 AM");
            Date end = new SimpleDateFormat("hh:mm:ss a").parse("11:00:00 AM");
            schedules.addAll(scheduleFacadeLocal.findByRangeOfDayAndTime(dayOfWeek, dayOfWeek, start, end));
            start = new SimpleDateFormat("hh:mm:ss a").parse("02:00:00 PM");
            end = new SimpleDateFormat("hh:mm:ss a").parse("05:00:00 PM");
            schedules.addAll(scheduleFacadeLocal.findByRangeOfDayAndTime(dayOfWeek, dayOfWeek, start, end));
            return schedules;
        } catch (ParseException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Collection<Schedule> getDefaultByWeek() {
        try {
            Collection<Schedule> schedules = new ArrayList<>();
            Date start = new SimpleDateFormat("hh:mm:ss a").parse("08:00:00 AM");
            Date end = new SimpleDateFormat("hh:mm:ss a").parse("11:00:00 AM");
            schedules.addAll(scheduleFacadeLocal.findByRangeOfDayAndTime(2, 6, start, end));
            start = new SimpleDateFormat("hh:mm:ss a").parse("02:00:00 PM");
            end = new SimpleDateFormat("hh:mm:ss a").parse("05:00:00 PM");
            schedules.addAll(scheduleFacadeLocal.findByRangeOfDayAndTime(2, 6, start, end));
            return schedules;
        } catch (ParseException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Collection<Schedule> findAllOrderByStart() {
        return scheduleFacadeLocal.findAllOrdered();
    }
    
}
