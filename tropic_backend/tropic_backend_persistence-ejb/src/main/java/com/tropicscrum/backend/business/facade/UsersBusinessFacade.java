/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.LoginException;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.InvalidCredentials;
import com.tropicscrum.backend.persistence.facade.UsersFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author syslife02
 */
@Stateless(name = "UserBussinesFacade", mappedName = UsersFacadeRemote.JNDI_REMOTE_NAME)
@Remote(UsersFacadeRemote.class)
public class UsersBusinessFacade implements UsersFacadeRemote {
    
    @EJB
    UsersFacadeLocal usersFacadeLocal;

    @Override
    public User create(User user) {
        try {
            usersFacadeLocal.create(user);
            return user;
        } catch (Exception ex) {
            return new User();
        }
    }

    @Override
    public User edit(User user) {
        usersFacadeLocal.edit(user);
        return user;
    }

    @Override
    public void remove(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User find(Object id) {
        try {
            return usersFacadeLocal.find(id);            
        } catch (NoResultException ex) {
            return new User();
        }
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isAlive() {
        return true;
    }              

    @Override
    public User login(String email, String password) throws LoginException {
        try {
            User user = usersFacadeLocal.login(email, password);
            return user;
        } catch (InvalidCredentials e) {
            throw new LoginException(e.getMessage());            
        }
    }

    @Override
    public Boolean emailExist(String email) {
        return usersFacadeLocal.emailExist(email);
    }
}
