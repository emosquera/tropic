/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "logoutRequestBean")
@RequestScoped
public class LogoutRequestBean {
    
    @Inject
    ExternalContext extContext;

    /**
     * Creates a new instance of LogoutRequestBean
     */
    public LogoutRequestBean() {
    }
    
    public String logout() {
        HttpSession session = (HttpSession) extContext.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
}
