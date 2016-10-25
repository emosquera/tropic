/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.model.History;
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

    History edit(History history);

    void remove(History history);

    History find(Object id);

    List<History> findAll();

    List<History> findRange(int[] range);

    int count();
}
