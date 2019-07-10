/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "headerViewBean")
@ViewScoped
public class HeaderViewBean implements Serializable {

    private UsersFacadeRemote usersFacadeRemote = new ServiceLocatorDelegate<UsersFacadeRemote>().getService(UsersFacadeRemote.JNDI_REMOTE_NAME);
    
    private User user;
    
    @Inject
    ExternalContext extContext;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Creates a new instance of HeaderViewBean
     */
    public HeaderViewBean() {
    }
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) extContext.getSession(false);
        User userSession = (User) session.getAttribute("user");                
        //Refresh User from BD
        user = usersFacadeRemote.find(userSession.getId());        
    }
}
