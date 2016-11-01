/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.model.UserSchedule;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface UserScheduleFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/userScheduleFacadeRemote";
    
    UserSchedule create(UserSchedule userSchedule);

    UserSchedule edit(UserSchedule userSchedule);

    void remove(UserSchedule userSchedule);

    UserSchedule find(Object id);

    List<UserSchedule> findAll();

    List<UserSchedule> findRange(int[] range);

    int count();
}
