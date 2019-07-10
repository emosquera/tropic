/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.Schedule;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */
@Remote
public interface ScheduleFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb:tropic_backend-ear-1.0-SNAPSHOT/tropic_backend_persistence-ejb-1.0-SNAPSHOT/scheduleFacadeRemote!com.tropicscrum.backend.client.facade.ScheduleFacadeRemote";
    //public final String JNDI_REMOTE_NAME = "ejb/scheduleFacadeRemote";

    Schedule create(Schedule schedule);

    Schedule edit(Schedule schedule) throws UpdateException;

    void remove(Schedule schedule);

    Schedule find(Object id);

    Collection<Schedule> findAll();
    
    Collection<Schedule> findAllOrderByStart();

    Collection<Schedule> findRange(int[] range);

    int count();
    
    Collection<Schedule> getDefaultByDay(int dayOfWeek);
    
    Collection<Schedule> getDefaultByWeek();
}
