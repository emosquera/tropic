/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.model.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author syslife02
 */
@Named(value = "indexViewBean")
@ViewScoped
public class IndexViewBean implements Serializable {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Creates a new instance of indexViewBean
     */
    public IndexViewBean() {        
    }
    
    @PostConstruct
    public void init() {
        user = new User();
    }
    
    
}
