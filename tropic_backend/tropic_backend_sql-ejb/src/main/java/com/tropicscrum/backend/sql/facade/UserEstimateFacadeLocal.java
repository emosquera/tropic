/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.sql.facade;

import com.tropicscrum.backend.model.UserEstimate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface UserEstimateFacadeLocal {

    void create(UserEstimate userEstimate);

    void edit(UserEstimate userEstimate);

    void remove(UserEstimate userEstimate);

    UserEstimate find(Object id);

    List<UserEstimate> findAll();

    List<UserEstimate> findRange(int[] range);

    int count();
    
}