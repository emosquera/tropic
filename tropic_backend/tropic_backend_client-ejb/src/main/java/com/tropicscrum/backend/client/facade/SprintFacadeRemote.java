/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.User;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface SprintFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/sprintFacadeRemote";
    
    Sprint create(Sprint sprint);

    Sprint edit(Sprint sprint) throws UpdateException;

    void remove(Sprint sprint);

    Sprint find(Object id);

    Collection<Sprint> findAll();

    Collection<Sprint> findRange(int[] range);

    int count();
    
    /**
     * Retorna una lista de Sprints que han sido creados por un usuario especifico. 
     * El Usuario debe ser un objeto de tipo {@link User}
     * <p>
     * Este metodo retornara null en caso de no encontrar Sprints asociados al usuario           
     *
     * @author Edgar Mosquera
     * @param you Un objeto de tipo {@link User}
     * @return Lista de {@link Sprint}
     * @see Sprint     
     * @see User
     */
    Collection<Sprint> findMySprints(User you);
    
    /**
     * Retorna una lista de Sprints que contienen Tareas asociadas a Historias de Projectos donde colabora un usuario. 
     * El Usuario debe ser un objeto de tipo {@link User}
     * <p>
     * Este metodo retornara una lista vacia en caso de no encontrar Sprints asociados a Pyoyectos donde colabora el usuario           
     *
     * @author Edgar Mosquera
     * @param you Un objeto de tipo {@link User}
     * @return Lista de {@link Sprint}
     * @see Sprint
     * @see Project
     * @see History
     * @see User
     */
    Collection<Sprint> findMyColabs(User you);
}
