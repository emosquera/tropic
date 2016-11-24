/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.List;
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

    List<Sprint> findAll();

    List<Sprint> findRange(int[] range);

    int count();
    
}
