/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.sql.facade;

import com.tropicscrum.backend.model.SprintUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface SprintUserFacadeLocal {

    void create(SprintUser sprintUser);

    void edit(SprintUser sprintUser);

    void remove(SprintUser sprintUser);

    SprintUser find(Object id);

    List<SprintUser> findAll();

    List<SprintUser> findRange(int[] range);

    int count();
    
}
