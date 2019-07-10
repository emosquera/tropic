/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.Technology;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */
@Remote
public interface TechnologyFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb:tropic_backend-ear-1.0-SNAPSHOT/tropic_backend_persistence-ejb-1.0-SNAPSHOT/technologyFacadeRemote!com.tropicscrum.backend.client.facade.TechnologyFacadeRemote";
    //public final String JNDI_REMOTE_NAME = "ejb/technologyFacadeRemote";
    
    Technology create(Technology technology);

    Technology edit(Technology technology) throws UpdateException;

    void remove(Technology technology);

    Technology find(Object id);

    Collection<Technology> findAll();

    Collection<Technology> findRange(int[] range);

    int count();
}
