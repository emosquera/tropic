/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Edgar Mosquera
 */
@Local
public interface MilestoneFacadeLocal {

    void create(Milestone milestone);

    void edit(Milestone milestone) throws OldVersionException;

    void remove(Milestone milestone);

    Milestone find(Object id);

    Collection<Milestone> findAll();

    Collection<Milestone> findRange(int[] range);

    int count();
    
    Collection<Milestone> findAllByUser(User user);
    
    Collection<Milestone> findAllByCollaborator(User user);
    
    Collection<Milestone> findByHistory(History history);
    
    Collection<Milestone> findBySprint(Sprint sprint);
    
    Collection<Milestone> findByHistoryAndSprint(History history, Sprint sprint);
    
}
