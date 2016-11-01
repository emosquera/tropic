/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.model.Sprint;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface SprintFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/sprintFacadeRemote";
    
    Sprint create(Sprint sprint);

    Sprint edit(Sprint sprint);

    void remove(Sprint sprint);

    Sprint find(Object id);

    List<Sprint> findAll();

    List<Sprint> findRange(int[] range);

    int count();
}
