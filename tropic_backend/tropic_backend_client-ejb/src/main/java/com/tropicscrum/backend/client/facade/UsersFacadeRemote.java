/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.model.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */
@Remote
public interface UsersFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/usersFacadeRemote";
    
    User create(User user);

    User edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
}
