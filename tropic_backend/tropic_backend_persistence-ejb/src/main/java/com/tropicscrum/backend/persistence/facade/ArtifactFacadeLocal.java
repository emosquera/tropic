/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Artifact;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Edgar Mosquera
 */
@Local
public interface ArtifactFacadeLocal {

    void create(Artifact artifact);

    void edit(Artifact artifact) throws OldVersionException;

    void remove(Artifact artifact);

    Artifact find(Object id);

    Collection<Artifact> findAll();

    Collection<Artifact> findRange(int[] range);

    int count();
    
    Collection<Artifact> findAllArtifactsByUser(User user);
    
    Collection<Artifact> findAllArtifactsByCollaborator(User user);
    
    Collection<Artifact> findAllArtifactsByMilestone(Milestone milestone);
    
}
