/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.enums.ScrumRole;
import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.SprintUserFacadeRemote;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.SprintUserFacadeLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless(name = "sprinUserFacadeRemote", mappedName = SprintUserFacadeRemote.JNDI_REMOTE_NAME)
@Remote(SprintUserFacadeRemote.class)
public class SprintUserBusinessFacade implements SprintUserFacadeRemote {

    @EJB
    SprintUserFacadeLocal sprintUserFacadeLocal;
    
    @Override
    public SprintUser create(SprintUser sprintUser) {
        sprintUserFacadeLocal.create(sprintUser);
        return sprintUser;
    }

    @Override
    public SprintUser edit(SprintUser sprintUser) throws UpdateException {
        try {
            sprintUserFacadeLocal.edit(sprintUser);
            return sprintUser;
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar el Usuario del Sprint. Este ha sido modificado en otra sesion. Se muestra el Usuario del Sprint actualizado");
        }        
    }

    @Override
    public void remove(SprintUser sprintUser) {
        sprintUserFacadeLocal.remove(sprintUser);
    }

    @Override
    public SprintUser find(Object id) {
        return sprintUserFacadeLocal.find(id);
    }

    @Override
    public Collection<SprintUser> findAll() {
        return sprintUserFacadeLocal.findAll();
    }

    @Override
    public Collection<SprintUser> findRange(int[] range) {
        return sprintUserFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return sprintUserFacadeLocal.count();
    }

    @Override
    public Collection<SprintUser> findSprintTeam(Sprint sprint) {
        return sprintUserFacadeLocal.findBySprint(sprint);
    }

    @Override
    public Boolean isSprintUserScrumMaster(Sprint sprint, User user) {
        try {
            sprintUserFacadeLocal.findBySprintAndUserAndRole(sprint, user, ScrumRole.SCRUM_MASTER);
            return Boolean.TRUE;
        } catch (EJBException e) {
            return Boolean.FALSE;
        }
    }
    
}
