/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.exceptions.LoginException;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.base.locator.ServiceVerifier;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */
@Remote
public interface UsersFacadeRemote extends ServiceVerifier {
    public final String JNDI_REMOTE_NAME = "ejb/usersFacadeRemote";
    
    User create(User user);

    User edit(User user);

    void remove(User user);

    User find(Object id);
    
    User findWithProjects(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
    User login(String email, String password) throws LoginException;
    
    Boolean emailExist(String email);
    
    void sendConfirmEmail(User user, String emailContent);
    
    List<User> getAllContainsEmailExceptYou(User you, String email);
}
