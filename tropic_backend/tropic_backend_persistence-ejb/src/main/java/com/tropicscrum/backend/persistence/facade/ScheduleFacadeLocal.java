/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Schedule;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Edgar Mosquera
 */
@Local
public interface ScheduleFacadeLocal {

    void create(Schedule schedule);

    void edit(Schedule schedule) throws OldVersionException;

    void remove(Schedule schedule);

    Schedule find(Object id);

    Collection<Schedule> findAll();
    
    Collection<Schedule> findAllOrdered();

    Collection<Schedule> findRange(int[] range);

    int count();
    
    Collection<Schedule> findByRangeOfDayAndTime(int startDay, int endDay, Date startTime, Date endTime);
    
}
