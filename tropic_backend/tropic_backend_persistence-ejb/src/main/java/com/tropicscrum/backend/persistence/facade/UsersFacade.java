/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.InvalidCredentials;
import java.util.ArrayList;
import java.util.List;
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
    public Boolean emailExist(String email) {
        try {
            em.createNamedQuery("findByEmail").setParameter("email", email).getSingleResult();  
            return true;            
        } catch (NoResultException e) {
            return false;
        }        
    }

    @Override
    public List<User> findOtherByEmail(User you, String email) {
        try {
            List<User> users = em.createNamedQuery("findOtherByEmail").setParameter("user", you).setParameter("email", email.trim()).getResultList();  
            return users;            
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public User findByEmail(String email) throws InvalidCredentials {
        try {
            return (User) em.createNamedQuery("findByEmail").setParameter("email", email).getSingleResult();                
        } catch (NoResultException e) {
            throw new InvalidCredentials("Email no existe");
        }
        
    }
}
