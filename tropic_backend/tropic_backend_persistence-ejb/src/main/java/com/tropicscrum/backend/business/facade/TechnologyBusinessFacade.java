/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.TechnologyFacadeRemote;
import com.tropicscrum.backend.client.model.Technology;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.TechnologyFacadeLocal;
import java.util.Collection;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless(name = "technologyFacadeRemote", mappedName = TechnologyFacadeRemote.JNDI_REMOTE_NAME)
@Remote(TechnologyFacadeRemote.class)
public class TechnologyBusinessFacade implements TechnologyFacadeRemote {

    @Inject
    TechnologyFacadeLocal technologyFacadeLocal;
    
    @Override
    public Technology create(Technology technology) {
        technologyFacadeLocal.create(technology);
        return technology;
    }

    @Override
    public Technology edit(Technology technology) throws UpdateException {
        try {
            technologyFacadeLocal.edit(technology);
            return technology;
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar la Tecnologia. Esta ha sido modificada en otra sesion. Se muestra la Tecnologia actualizada");
        }
    }

    @Override
    public void remove(Technology technology) {
        technologyFacadeLocal.remove(technology);
    }

    @Override
    public Technology find(Object id) {
        return technologyFacadeLocal.find(id);
    }

    @Override
    public Collection<Technology> findAll() {
        return technologyFacadeLocal.findAll();
    }

    @Override
    public Collection<Technology> findRange(int[] range) {
        return technologyFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return technologyFacadeLocal.count();
    }
    
}
