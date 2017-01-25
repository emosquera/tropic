/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface SprintFacadeLocal {

    void create(Sprint sprint);

    void edit(Sprint sprint) throws OldVersionException;

    void remove(Sprint sprint);

    Sprint find(Object id);

    Collection<Sprint> findAll();

    Collection<Sprint> findRange(int[] range);

    int count();
    
    Collection<Sprint> findAllByUser(User user);
}
