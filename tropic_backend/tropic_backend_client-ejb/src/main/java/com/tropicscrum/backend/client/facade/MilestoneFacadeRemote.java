/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.User;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */

@Remote
public interface MilestoneFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb:tropic_backend-ear-1.0-SNAPSHOT/tropic_backend_persistence-ejb-1.0-SNAPSHOT/milestoneFacadeRemote!com.tropicscrum.backend.client.facade.MilestoneFacadeRemote";
    //public final String JNDI_REMOTE_NAME = "ejb/milestoneFacadeRemote";
    
    Milestone create(Milestone milestone);

    Milestone edit(Milestone milestone) throws UpdateException;

    void remove(Milestone milestone);

    Milestone find(Object id);

    Collection<Milestone> findAll();

    Collection<Milestone> findRange(int[] range);

    int count();
    
    Collection<Milestone> findAllMine(User you);
    
    Collection<Milestone> findAllMyColabs(User you);
    
    Collection<Milestone> findHistoryMilestones(History history);
    
    Collection<Milestone> findSprintMilestones(Sprint sprint);
    
    Collection<Milestone> findSprintHistoryMilestones(History history, Sprint sprint);
}
