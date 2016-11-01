/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.UserSchedule;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface UserScheduleFacadeLocal {

    void create(UserSchedule userSchedule);

    void edit(UserSchedule userSchedule);

    void remove(UserSchedule userSchedule);

    UserSchedule find(Object id);

    List<UserSchedule> findAll();

    List<UserSchedule> findRange(int[] range);

    int count();
    
}
