/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.User;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author syslife02
 */
@Named(value = "findUsersRequestBean")
@RequestScoped
public class FindUsersRequestBean {
    
    @EJB(lookup = UsersFacadeRemote.JNDI_REMOTE_NAME)
    UsersFacadeRemote usersFacadeRemote;

    /**
     * Creates a new instance of FindUsersRequestBean
     */
    public FindUsersRequestBean() {
    }
    
    public Collection<User> getUsersByEmail(String query) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            return usersFacadeRemote.getAllContainsEmailExceptYou(user, query);
        } else {
            return new ArrayList<>();
        }        
    }    
}
