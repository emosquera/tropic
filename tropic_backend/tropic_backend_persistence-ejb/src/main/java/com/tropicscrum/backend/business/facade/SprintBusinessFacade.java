/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.SprintFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.ProjectFacadeLocal;
import com.tropicscrum.backend.persistence.facade.SprintFacadeLocal;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author syslife02
 */
@Stateless(name = "sprintFacadeRemote", mappedName = SprintFacadeRemote.JNDI_REMOTE_NAME)
@Remote(SprintFacadeRemote.class)
public class SprintBusinessFacade implements SprintFacadeRemote{
    
    @EJB
    SprintFacadeLocal sprintFacadeLocal;
    
    @EJB
    ProjectFacadeLocal projectFacadeLocal;
    
    @Override
    public Sprint create(Sprint sprint) {
        sprintFacadeLocal.create(sprint);        
        for (SprintUser sprintUser : sprint.getSprintUsers()) {
            sprintUser.getSchedules().size();
        }
        return sprint;
    }

    @Override
    public Sprint edit(Sprint sprint) throws UpdateException {
        try {
            sprintFacadeLocal.edit(sprint);
            return sprint;
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar el Sprint. Este ha sido modificado en otra sesion. Se muestra el sprint actualizado");
        }        
    }

    @Override
    public void remove(Sprint sprint) {
        sprintFacadeLocal.remove(sprint);
    }

    @Override
    public Sprint find(Object id) {
        return sprintFacadeLocal.find(id);
    }

    @Override
    public Collection<Sprint> findAll() {
        return sprintFacadeLocal.findAll();
    }

    @Override
    public Collection<Sprint> findRange(int[] range) {
        return sprintFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return sprintFacadeLocal.count();
    }

    @Override
    public Collection<Sprint> findMySprints(User you) {
        Collection<Sprint> mySprints = sprintFacadeLocal.findAllByUser(you);
        for (Sprint sprint : mySprints) {
            for (SprintUser sprintUser : sprint.getSprintUsers()) {
                sprintUser.getSchedules().size();
            }
        }
        return sprintFacadeLocal.findAllByUser(you);
    }

    @Override
    public Collection<Sprint> findMyColabs(User you) {
        Collection<Sprint> mySprints = new ArrayList<>();
        Collection<Project> collabProject = projectFacadeLocal.findAllByCollaborator(you);
        for (Project project : collabProject) {
            Collection<History> histories = project.getHistories();
            for (History history : histories) {
                mySprints.retainAll(history.getSprints());
            }
        }
        return mySprints;
    }    
}
