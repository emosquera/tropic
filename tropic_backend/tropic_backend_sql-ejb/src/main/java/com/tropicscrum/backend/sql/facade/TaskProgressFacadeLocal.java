/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.sql.facade;

import com.tropicscrum.backend.model.TaskProgress;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface TaskProgressFacadeLocal {

    void create(TaskProgress taskProgress);

    void edit(TaskProgress taskProgress);

    void remove(TaskProgress taskProgress);

    TaskProgress find(Object id);

    List<TaskProgress> findAll();

    List<TaskProgress> findRange(int[] range);

    int count();
    
}
