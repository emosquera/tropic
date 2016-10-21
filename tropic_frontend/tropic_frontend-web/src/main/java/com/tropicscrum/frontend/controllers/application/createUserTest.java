/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.application;

import com.tropicscrum.backend.client.delegate.UsersDelegate;
import com.tropicscrum.backend.client.dto.UserDTO;
import com.tropicscrum.backend.client.enums.Gender;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author syslife02
 */
@Named(value = "createUserTest")
@ApplicationScoped
public class createUserTest implements Serializable {
    private UserDTO user;
    private final UsersDelegate userDelegate = new UsersDelegate();

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    /**
     * Creates a new instance of createUserTest
     */
    public createUserTest() {       
       
    }
    
    @PostConstruct
    public void init() {
       UserDTO newUser = new UserDTO();
       newUser.setFirstName("Edgar");
       newUser.setLastName("Mosquera");
       newUser.setEmail("edgarmosquera@gmail.com");
       newUser.setGender(Gender.MALE);
       newUser.setPassword("admin");
       newUser.setVersion(0);
       
       this.user = userDelegate.create(newUser);
    }
    
}
