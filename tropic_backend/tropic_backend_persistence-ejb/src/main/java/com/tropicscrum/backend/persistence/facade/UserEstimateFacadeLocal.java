/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.UserEstimate;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Edgar Mosquera
 */
@Local
public interface UserEstimateFacadeLocal {

    void create(UserEstimate userEstimate);

    void edit(UserEstimate userEstimate) throws OldVersionException;

    void remove(UserEstimate userEstimate);

    UserEstimate find(Object id);

    Collection<UserEstimate> findAll();

    Collection<UserEstimate> findRange(int[] range);

    int count();
    
}
