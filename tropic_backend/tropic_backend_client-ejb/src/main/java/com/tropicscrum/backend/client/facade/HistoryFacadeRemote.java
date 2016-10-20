/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.dto.HistoryDTO;
import com.tropicscrum.base.locator.ServiceVerifier;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface HistoryFacadeRemote extends ServiceVerifier {
    public final String JNDI_REMOTE_NAME = "ejb/historyFacadeRemote";
    
    HistoryDTO create(HistoryDTO historyDTO);

    HistoryDTO edit(HistoryDTO historyDTO);

    void remove(HistoryDTO historyDTO);

    HistoryDTO find(Object id);

    List<HistoryDTO> findAll();

    List<HistoryDTO> findRange(int[] range);

    int count();
}