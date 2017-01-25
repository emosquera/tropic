/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface TaskFacadeLocal {

    void create(Task task);

    void edit(Task task) throws OldVersionException;

    void remove(Task task);

    Task find(Object id);

    Collection<Task> findAll();

    Collection<Task> findRange(int[] range);

    int count();
    
}
