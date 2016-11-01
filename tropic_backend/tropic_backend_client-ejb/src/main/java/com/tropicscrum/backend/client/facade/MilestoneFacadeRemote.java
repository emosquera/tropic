/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.model.Milestone;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface MilestoneFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/milestoneFacadeRemote";
    
    Milestone create(Milestone milestone);

    Milestone edit(Milestone milestone);

    void remove(Milestone milestone);

    Milestone find(Object id);

    List<Milestone> findAll();

    List<Milestone> findRange(int[] range);

    int count();
}
