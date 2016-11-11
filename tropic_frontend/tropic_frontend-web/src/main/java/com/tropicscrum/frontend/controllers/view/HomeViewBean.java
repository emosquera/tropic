/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author syslife02
 */
@Named(value = "homeViewBean")
@ViewScoped
public class HomeViewBean implements Serializable {

    private User user;

    @EJB(lookup = UsersFacadeRemote.JNDI_REMOTE_NAME)
    private UsersFacadeRemote usersFacadeRemote;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
            
    /**
     * Creates a new instance of HomeViewBean
     */
    public HomeViewBean() {
    }
    
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        User userSession = (User) session.getAttribute("user");
        //Refresh User from BD
        user = usersFacadeRemote.find(userSession.getId());
    }
}