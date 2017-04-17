/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.SprintFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.ArtifactFacadeLocal;
import com.tropicscrum.backend.persistence.facade.HistoryFacadeLocal;
import com.tropicscrum.backend.persistence.facade.MilestoneFacadeLocal;
import com.tropicscrum.backend.persistence.facade.ProjectFacadeLocal;
import com.tropicscrum.backend.persistence.facade.SprintFacadeLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless(name = "sprintFacadeRemote", mappedName = SprintFacadeRemote.JNDI_REMOTE_NAME)
@Remote(SprintFacadeRemote.class)
public class SprintBusinessFacade implements SprintFacadeRemote{
    
    @EJB
    SprintFacadeLocal sprintFacadeLocal;
    
    @EJB
    ProjectFacadeLocal projectFacadeLocal;
    
    @EJB
    HistoryFacadeLocal historyFacadeLocal;
    
    @EJB
    MilestoneFacadeLocal milestoneFacadeLocal;
    
    @EJB
    ArtifactFacadeLocal artifactFacadeLocal;
    
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
            sprint.getSprintVelocitys().size();
            for (SprintUser sprintUser : sprint.getSprintUsers()) {
                sprintUser.getSchedules().size();
            }
        }
        return mySprints;
    }

    @Override
    public Collection<Sprint> findMyColabs(User you) {
        Collection<Sprint> mySprints = sprintFacadeLocal.findAllByCollaborator(you);
        for (Sprint sprint : mySprints) {
            sprint.getSprintVelocitys().size();
            for (SprintUser sprintUser : sprint.getSprintUsers()) {
                sprintUser.getSchedules().size();
            }
        }
        return mySprints;
    }    

    @Override
    public Collection<Sprint> findSprintsCreateTask(User you) {
        Collection<Sprint> mySprints = sprintFacadeLocal.findAllByUser(you);
        Collection<Sprint> myCollabSprints = sprintFacadeLocal.findAllByCollaborator(you);
        Collection<Sprint> mySprintsCanCreate = sprintFacadeLocal.findAllSprintsByUserCanCreateTask(you);
        
        mySprints.addAll(myCollabSprints);
        for (Sprint sprint : mySprintsCanCreate) {
            if (!mySprints.contains(sprint)) {
                mySprints.add(sprint);
            }
        }
        
        for (Sprint s : mySprints) {            
            for (Milestone m : s.getMilestones()) {
                m.getArtifacts().size();
            }
            
            for (History h : s.getProject().getHistories()) {
                for (Milestone milestone : h.getMilestones()) {
                    milestone.getArtifacts().size();
                }
            }
        }
        return mySprints;
    }

    @Override
    public Collection<Sprint> findSprintsTeam(User you) {
        return sprintFacadeLocal.findAllSprintsBySprintUser(you);
    }
}
