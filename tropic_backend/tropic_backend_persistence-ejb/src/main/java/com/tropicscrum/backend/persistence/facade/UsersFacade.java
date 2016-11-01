/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.InvalidCredentials;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author syslife02
 */
@Stateless
public class UsersFacade extends AbstractFacade<User> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(User.class);
    }

    @Override
    public User login(String email, String password) throws InvalidCredentials {     
        try {
            User user = (User) em.createNamedQuery("findByEmail").setParameter("email", email).getSingleResult();  
            if (user.getPassword().equals(password)) {
                return user;
            } else {
              throw new InvalidCredentials("Password icorrecto");
            }            
        } catch (NoResultException e) {
            throw new InvalidCredentials("Usuario no Encontrado", e);
        }        
    }    

    @Override
    public Boolean emailExist(String email) {
        try {
            em.createNamedQuery("findByEmail").setParameter("email", email).getSingleResult();  
            return true;            
        } catch (NoResultException e) {
            return false;
        }        
    }
}
