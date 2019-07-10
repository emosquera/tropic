/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.web.service;

import com.tropicscrum.backend.client.facade.ScheduleFacadeRemote;
import com.tropicscrum.backend.client.model.Schedule;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless
@Path("init")
public class InitData {

    ScheduleFacadeRemote scheduleFacadeRemote = new ServiceLocatorDelegate<ScheduleFacadeRemote>().getService(ScheduleFacadeRemote.JNDI_REMOTE_NAME);
    
    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Collection<Schedule> createDefaultSchedule() {
        Collection<Schedule> allSchedule = new ArrayList<>();
        
        for(int x = 1; x<=7; x++) {
            for (int y = 6; y <= 22; y++) {
                try {
                    Schedule schedule = new Schedule();
                    Date start = new SimpleDateFormat("hh:mm:ss a").parse(String.valueOf(y >= 13 ? y - 12 : y) + ":00:00 " + (y <= 11 ? "AM" : "PM"));                                        
                    Date end = new SimpleDateFormat("hh:mm:ss a").parse(String.valueOf(y + 1 >= 13 ? y - 11 : y + 1) + ":00:00 " + (y + 1 <= 11 ? "AM" : "PM"));                    
                    schedule.setDayOfWeek(x);
                    schedule.setStart(start);
                    schedule.setEnd(end);                    
                    scheduleFacadeRemote.create(schedule);
                    allSchedule.add(schedule);
                } catch (ParseException ex) {
                    Logger.getLogger(InitData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return allSchedule;
    }
    
}
