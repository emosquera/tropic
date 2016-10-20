/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.dto.MilestoneDTO;
import com.tropicscrum.base.locator.ServiceVerifier;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */

@Remote
public interface MilestoneFacadeRemote extends ServiceVerifier {
    public final String JNDI_REMOTE_NAME = "ejb/milestoneFacadeRemote";
    
    MilestoneDTO create(MilestoneDTO milestoneDTO);

    MilestoneDTO edit(MilestoneDTO milestoneDTO);

    void remove(MilestoneDTO milestoneDTO);

    MilestoneDTO find(Object id);

    List<MilestoneDTO> findAll();

    List<MilestoneDTO> findRange(int[] range);

    int count();
}
