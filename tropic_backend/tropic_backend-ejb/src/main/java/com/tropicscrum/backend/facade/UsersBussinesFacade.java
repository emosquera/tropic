/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.facade;

import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.model.User;
import com.tropicscrum.backend.sql.facade.UsersFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author syslife02
 */
@Stateless(name = "UserBussinesFacade", mappedName = UsersFacadeRemote.JNDI_REMOTE_NAME)
@Remote(UsersFacadeRemote.class)
public class UsersBussinesFacade implements UsersFacadeRemote {
    
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
            User user = usersFacadeLocal.find(id);
            return user;
        } catch (Exception ex) {
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
    
    public boolean isAlive() {
        return true;
    }              
}
