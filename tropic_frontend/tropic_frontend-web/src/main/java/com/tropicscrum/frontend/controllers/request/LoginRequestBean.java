/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.exceptions.LoginException;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.client.utils.Md5Converter;
import com.tropicscrum.base.locator.AbstractDelegate;
import com.tropicscrum.frontend.controllers.view.IndexViewBean;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import java.security.NoSuchAlgorithmException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "loginRequestBean")
@RequestScoped
public class LoginRequestBean extends AbstractDelegate<Object>{

    private final Md5Converter md5Converter = new Md5Converter();    
    
    @Inject
    IndexViewBean indexViewBean;        
    
    @Inject
    FacesContext context;
    
    @Inject
    ExternalContext extContext;
    
    UsersFacadeRemote userFacadeRemote= new ServiceLocatorDelegate<UsersFacadeRemote>().getService(UsersFacadeRemote.JNDI_REMOTE_NAME);
    
    public String login() throws LoginException {     
        HttpSession session = (HttpSession) extContext.getSession(false);
        try {
            try {
                User user = userFacadeRemote.login(indexViewBean.getUserName(), md5Converter.StringToMD5(indexViewBean.getPassword()));  
                session.setAttribute("user", user);                
                return "/home/home?faces-redirect=true";
            } catch (NoSuchAlgorithmException ex) {
                session.setAttribute("user", null);
                return null;
            } 
        } catch (LoginException ex) {            
            session.setAttribute("user", null);            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            indexViewBean.setPassword("");
            return null;
        }        
    }

    /**
     * Creates a new instance of LoginRequestBean
     */
    public LoginRequestBean() {
    }    
}
