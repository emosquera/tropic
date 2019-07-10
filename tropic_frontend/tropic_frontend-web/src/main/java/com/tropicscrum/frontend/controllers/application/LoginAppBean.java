/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.application;

import com.tropicscrum.backend.client.model.SprintUser;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "loginAppBean")
@ApplicationScoped
public class LoginAppBean {
    
    private Collection<SprintUser> usersLogged;

    public Collection<SprintUser> getUsersLogged() {
        return usersLogged;
    }

    public void setUsersLogged(Collection<SprintUser> usersLogged) {
        this.usersLogged = usersLogged;
    }    

    public Boolean isUserLogged(SprintUser sprintUser) {
        return this.usersLogged.stream().anyMatch(ul -> ul.equals(sprintUser));                
    }

    /**
     * Creates a new instance of LoginAppBean
     */
    public LoginAppBean() {
        usersLogged = new ArrayList<>();
    }
    
}
