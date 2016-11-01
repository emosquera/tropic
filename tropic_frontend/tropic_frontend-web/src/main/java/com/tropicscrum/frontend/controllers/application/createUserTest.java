/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.application;

import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.client.enums.Gender;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author syslife02
 */
@Named(value = "createUserTest")
@ApplicationScoped
public class createUserTest implements Serializable {
    private User user;
    
    @EJB(lookup = UsersFacadeRemote.JNDI_REMOTE_NAME)
    UsersFacadeRemote userFacadeRemote;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Creates a new instance of createUserTest
     */
    public createUserTest() {       
       
    }
    
    @PostConstruct
    public void init() {
       User newUser = new User();
       newUser.setFirstName("Edgar Francisco");
       newUser.setLastName("Mosquera Mosquera");
       newUser.setEmail("edgarmosquera@gmail.com");
       newUser.setGender(Gender.MALE);
       newUser.setPassword("admin");
       
       this.user = userFacadeRemote.create(newUser);
    }
    
}
