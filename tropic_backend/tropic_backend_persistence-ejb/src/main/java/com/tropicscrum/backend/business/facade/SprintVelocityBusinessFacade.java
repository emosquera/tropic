/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.SprintVelocityFacadeRemote;
import com.tropicscrum.backend.client.model.SprintVelocity;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.SprintVelocityFacadeLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless(name = "sprintVelocityBusinessFacade", mappedName = SprintVelocityFacadeRemote.JNDI_REMOTE_NAME)
@Remote(SprintVelocityFacadeRemote.class)
public class SprintVelocityBusinessFacade implements SprintVelocityFacadeRemote {

    @EJB
    SprintVelocityFacadeLocal sprintVelocityFacadeLocal; 

    @Override
    public SprintVelocity create(SprintVelocity sprintVelocity) {
        sprintVelocityFacadeLocal.create(sprintVelocity);
        return sprintVelocity;
    }

    @Override
    public SprintVelocity edit(SprintVelocity sprintVelocity) throws UpdateException {
        try {
            sprintVelocityFacadeLocal.edit(sprintVelocity);
            return sprintVelocity;
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar el la velocidad del Sprint. Esta ha sido modificada en otra sesion. Se muestra la velocidad del Sprint actualizada");
        }
    }

    @Override
    public void remove(SprintVelocity sprintVelocity) {
        sprintVelocityFacadeLocal.remove(sprintVelocity);
    }

    @Override
    public SprintVelocity find(Object id) {
        return sprintVelocityFacadeLocal.find(id);
    }

    @Override
    public Collection<SprintVelocity> findAll() {
        return sprintVelocityFacadeLocal.findAll();
    }

    @Override
    public Collection<SprintVelocity> findRange(int[] range) {
        return sprintVelocityFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return sprintVelocityFacadeLocal.count();
    }
    
    
}
