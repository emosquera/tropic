/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface HistoryFacadeLocal {

    void create(History history);

    void edit(History history) throws OldVersionException;

    void remove(History history);

    History find(Object id);

    Collection<History> findAll();

    Collection<History> findRange(int[] range);

    int count();
    
    Collection<History> findAllByUser(User user);
    
    Collection<History> findAllByCollaborator(User user);
    
    Collection<History> findByProject(Project project);
}
