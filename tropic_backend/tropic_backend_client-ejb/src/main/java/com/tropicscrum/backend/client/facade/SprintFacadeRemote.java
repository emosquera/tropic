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
 * @author Edgar Mosquera
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
     * Retorna una lista de Sprints asociados a Projectos donde colabora un usuario. 
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
    
    
    /**
     * Retorna una lista de Sprints donde el Usuario puede crear Tareas. 
     * Cada Sprint en la lista retornada contiene el proyecto al cual pertence 
     * y este contiene la lista de sus Historias y a su vez cada historia tendra una lista de sus hitos.
     * El Usuario debe ser un objeto de tipo {@link User}
     * <p>
     * Un usuario puede crear Tareas en un Sprint si es Autor del Proyecto del Sprint, si colabora en 
     * el Proyecto del Sprint o si pertenece al equipo del Sprint y tiene marcada la opcion de Crear Tareas.
     * Este metodo retornara una lista vacia en caso de no encontrar Sprints con las condiciones anteriormente descritas.           
     *
     * @author Edgar Mosquera
     * @param you Un objeto de tipo {@link User}
     * @return Lista de {@link Sprint}
     * @see Sprint
     * @see Project
     * @see History
     * @see User
     */
    Collection<Sprint> findSprintsCreateTask(User you);
    
    /**
     * Retorna una lista de Sprints donde el Usuario participa como parte del Equipo.      
     * El Usuario debe ser un objeto de tipo {@link User}
     * <p>
     * Este metodo retornara una lista vacia en caso de no encontrar Sprints con las condiciones anteriormente descritas.           
     *
     * @author Edgar Mosquera
     * @param you Un objeto de tipo {@link User}
     * @return Lista de {@link Sprint}
     * @see Sprint
     * @see User
     */
    Collection<Sprint> findSprintsTeam(User you);
}
