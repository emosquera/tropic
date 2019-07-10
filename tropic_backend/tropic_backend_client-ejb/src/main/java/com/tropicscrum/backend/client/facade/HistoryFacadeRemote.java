/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.User;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Edgar Mosquera
 */
@Remote
public interface HistoryFacadeRemote {

    public final String JNDI_REMOTE_NAME = "ejb:tropic_backend-ear-1.0-SNAPSHOT/tropic_backend_persistence-ejb-1.0-SNAPSHOT/historyFacadeRemote!com.tropicscrum.backend.client.facade.HistoryFacadeRemote";
    //public final String JNDI_REMOTE_NAME = "ejb/historyFacadeRemote";

    History create(History history);

    History edit(History history) throws UpdateException;

    void remove(History history);

    History find(Object id);

    Collection<History> findAll();

    Collection<History> findRange(int[] range);

    int count();

    Collection<History> findAllMine(User you);

    Collection<History> findAllMyCollabs(User you);

    /**
     * Retorna una lista de historias que pertenecen a un proyecto. 
     * El proyecto debe ser un objeto de tipo {@link Project}
     * <p>
     * Este metodo retornara null si no consigue ninguna historia asociada al 
     * proyecto.     
     *
     * @author Edgar Mosquera
     * @param project Un objeto de tipo Proyecto
     * @return La lista de Historias que pertenecen al Proyecto dado
     * @see History
     * @see Project
     */
    Collection<History> findProjectHistories(Project project);
    
    /**
     * Retorna una lista de historias que poseen Tareas del Sprint dado.  
     * Cada Historia tendra sus Hitos y estos tendran las tareas
     * <p>
     * Este metodo retornara null si no consigue ninguna historia asociada al 
     * proyecto.     
     *
     * @author Edgar Mosquera
     * @param sprint Un objeto de tipo Sprint
     * @return La lista de Historias que poseen Tareas del Sprint dado
     * @see History
     * @see Sprint
     */
    Collection<History> findSprintHistories(Sprint sprint);
}
