/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Technology;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Edgar Mosquera
 */
@Local
public interface TechnologyFacadeLocal {
    
    void create(Technology technology);

    void edit(Technology technology) throws OldVersionException;

    void remove(Technology technology);

    Technology find(Object id);

    Collection<Technology> findAll();

    Collection<Technology> findRange(int[] range);

    int count();
}
