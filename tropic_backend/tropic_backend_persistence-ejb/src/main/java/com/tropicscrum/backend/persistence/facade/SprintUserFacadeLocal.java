/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author syslife02
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
    
}
