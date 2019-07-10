/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.MilestoneFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.MilestoneFacadeLocal;
import java.util.Collection;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless(name = "milestoneFacadeRemote", mappedName = MilestoneFacadeRemote.JNDI_REMOTE_NAME)
@Remote(MilestoneFacadeRemote.class)
public class MilestoneBusinessFacade implements MilestoneFacadeRemote {

    @Inject
    MilestoneFacadeLocal milestoneFacadeLocal;
    
    @Override
    public Milestone create(Milestone milestone) {
        milestoneFacadeLocal.create(milestone);
        return milestone;
    }

    @Override
    public Milestone edit(Milestone milestone) throws UpdateException{
        try {
            milestoneFacadeLocal.edit(milestone);
            return milestone;
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar el Hito. Este ha sido modificado en otra sesion. Se muestra el hito actualizado");
        }        
    }

    @Override
    public void remove(Milestone milestone) {
        milestoneFacadeLocal.remove(milestone);
    }

    @Override
    public Milestone find(Object id) {
        return milestoneFacadeLocal.find(id);
    }

    @Override
    public Collection<Milestone> findAll() {
        return milestoneFacadeLocal.findAll();
    }

    @Override
    public Collection<Milestone> findRange(int[] range) {
        return milestoneFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return milestoneFacadeLocal.count();
    }

    @Override
    public Collection<Milestone> findAllMine(User you) {        
        return milestoneFacadeLocal.findAllByUser(you);
    }

    @Override
    public Collection<Milestone> findAllMyColabs(User you) {
        return milestoneFacadeLocal.findAllByCollaborator(you);
    }

    @Override
    public Collection<Milestone> findHistoryMilestones(History history) {
        return milestoneFacadeLocal.findByHistory(history);
    }

    @Override
    public Collection<Milestone> findSprintHistoryMilestones(History history, Sprint sprint) {
        return milestoneFacadeLocal.findByHistoryAndSprint(history, sprint);
    }

    @Override
    public Collection<Milestone> findSprintMilestones(Sprint sprint) {
        return milestoneFacadeLocal.findBySprint(sprint);
    }
    
}
