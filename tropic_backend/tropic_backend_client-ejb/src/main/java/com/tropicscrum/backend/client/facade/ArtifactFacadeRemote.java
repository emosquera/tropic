/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.Artifact;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */
@Remote
public interface ArtifactFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/artifactFacadeRemote";
    
    Artifact create(Artifact artifact);

    Artifact edit(Artifact artifact) throws UpdateException;

    void remove(Artifact artifact);

    Artifact find(Object id);

    Collection<Artifact> findAll();

    Collection<Artifact> findRange(int[] range);

    int count();    
}
