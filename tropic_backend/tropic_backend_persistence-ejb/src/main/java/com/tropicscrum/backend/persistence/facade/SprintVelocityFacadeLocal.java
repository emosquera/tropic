/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.SprintVelocity;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Edgar Mosquera
 */
@Local
public interface SprintVelocityFacadeLocal {

    void create(SprintVelocity sprintVelocity);

    void edit(SprintVelocity sprintVelocity) throws OldVersionException;

    void remove(SprintVelocity sprintVelocity);

    SprintVelocity find(Object id);

    Collection<SprintVelocity> findAll();

    Collection<SprintVelocity> findRange(int[] range);

    int count();
    
}
