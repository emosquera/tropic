/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */
@Remote
public interface HistoryFacadeRemote {

    public final String JNDI_REMOTE_NAME = "ejb/historyFacadeRemote";

    History create(History history);

    History edit(History history) throws UpdateException;

    void remove(History history);

    History find(Object id);

    List<History> findAll();

    List<History> findRange(int[] range);

    int count();

    List<History> findAllMine(User you);

    List<History> findAllMyCollabs(User you);

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
    List<History> findProjectHistories(Project project);
}
