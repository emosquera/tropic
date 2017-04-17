/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.ArtifactFacadeRemote;
import com.tropicscrum.backend.client.model.Artifact;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.ArtifactFacadeLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless(name = "artifactFacadeRemote", mappedName = ArtifactFacadeRemote.JNDI_REMOTE_NAME)
@Remote(ArtifactFacadeRemote.class)
public class ArtifactBusinessFacade implements ArtifactFacadeRemote {

    @EJB
    ArtifactFacadeLocal artifactFacadeLocal;
    
    @Override
    public Artifact create(Artifact artifact) {
        artifactFacadeLocal.create(artifact);
        return artifact;
    }

    @Override
    public Artifact edit(Artifact artifact) throws UpdateException {
        try {
            artifactFacadeLocal.edit(artifact);
            return artifact;
        } catch (OldVersionException ex) {
            throw new UpdateException("No se puede actualizar el Componente. Este ha sido modificada en otra sesion. Se muestra el componente actualizado");
        }
    }

    @Override
    public void remove(Artifact artifact) {
        artifactFacadeLocal.remove(artifact);
    }

    @Override
    public Artifact find(Object id) {
        return artifactFacadeLocal.find(id);
    }

    @Override
    public Collection<Artifact> findAll() {
        return artifactFacadeLocal.findAll();
    }

    @Override
    public Collection<Artifact> findRange(int[] range) {
        return artifactFacadeLocal.findRange(range);
    }

    @Override
    public int count() {
        return artifactFacadeLocal.count();
    }

    @Override
    public Collection<Artifact> findMyArtifacts(User you) {
        return deepArtifact(artifactFacadeLocal.findAllArtifactsByUser(you));
    }

    @Override
    public Collection<Artifact> findMyCollabs(User you) {
        return deepArtifact(artifactFacadeLocal.findAllArtifactsByCollaborator(you));
    }
    
    private Collection<Artifact> deepArtifact(Collection<Artifact> artifacts) {
        Collection<Artifact> myArtifacts = artifacts;
        for (Artifact artifact : myArtifacts) {
            for (History history : artifact.getMilestone().getSprint().getProject().getHistories()) {
                history.getMilestones().size();
            }
        }
        return myArtifacts;
    }

    @Override
    public Collection<Artifact> findMilestoneArtifacts(Milestone milestone) {
        return artifactFacadeLocal.findAllArtifactsByMilestone(milestone);
    }
    
}
