/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "findUsersRequestBean")
@RequestScoped
public class FindUsersRequestBean {

    UsersFacadeRemote usersFacadeRemote = new ServiceLocatorDelegate<UsersFacadeRemote>().getService(UsersFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    ExternalContext extContext;

    /**
     * Creates a new instance of FindUsersRequestBean
     */
    public FindUsersRequestBean() {
    }
    
    public Collection<User> getUsersByEmail(String query) {
        HttpSession session = (HttpSession) extContext.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            return usersFacadeRemote.getAllContainsEmailExceptYou(user, query);
        } else {
            return new ArrayList<>();
        }        
    }    
}
