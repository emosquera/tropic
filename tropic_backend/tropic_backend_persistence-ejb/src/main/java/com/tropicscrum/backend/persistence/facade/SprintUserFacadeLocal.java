/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.enums.ScrumRole;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Edgar Mosquera
 */
@Local
public interface SprintUserFacadeLocal {

    void create(SprintUser sprintUser);

    void edit(SprintUser sprintUser) throws OldVersionException;

    void remove(SprintUser sprintUser);

    SprintUser find(Object id);

    Collection<SprintUser> findAll();

    Collection<SprintUser> findRange(int[] range);

    int count();
    
    Collection<SprintUser> findBySprint(Sprint sprint);
    
    SprintUser findBySprintAndUserAndRole(Sprint sprint, User user, ScrumRole role);
}
