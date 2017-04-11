/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.HistoryFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.HistoryFacadeLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless(name = "historyFacadeRemote", mappedName = HistoryFacadeRemote.JNDI_REMOTE_NAME)
@Remote(HistoryFacadeRemote.class)
public class HistoryBusinessFacade implements HistoryFacadeRemote {

    @EJB
    HistoryFacadeLocal historyFacadeLocal;
    
    @Override
    public History create(History history) {
        historyFacadeLocal.create(history);
        return history;
    }

    @Override
    public History edit(History history) throws UpdateException {
        try {
            historyFacadeLocal.edit(history);
            return history;
        } catch (OldVersionException e) {
            throw new UpdateException("No se puede actualizar la Historia. Esta ha sido modificada en otra sesion. Se muestra la historia actualizada");
        }                
    }

    @Override
    public void remove(History history) {
        historyFacadeLocal.remove(history);
    }

    @Override
    public History find(Object id) {
        return historyFacadeLocal.find(id);
    }

    @Override
    public Collection<History> findAll() {
        return historyFacadeLocal.findAll();
    }

    @Override
    public Collection<History> findRange(int[] range) {
        return historyFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return historyFacadeLocal.count();
    }

    @Override
    public Collection<History> findAllMine(User you) {
        Collection<History> histories = historyFacadeLocal.findAllByUser(you);
        return histories;
    }

    @Override
    public Collection<History> findAllMyCollabs(User you) {
        Collection<History> histories = historyFacadeLocal.findAllByCollaborator(you);
        return histories;
    }    

    @Override
    public Collection<History> findProjectHistories(Project project) {
        Collection<History> histories = historyFacadeLocal.findByProject(project);
        return histories;
    }

    @Override
    public Collection<History> findSprintHistories(Sprint sprint) {
        Collection<History> histories = historyFacadeLocal.findBySprint(sprint);
        return histories;
    }
}
