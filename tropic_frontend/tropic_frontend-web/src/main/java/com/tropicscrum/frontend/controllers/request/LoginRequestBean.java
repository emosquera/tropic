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
import com.tropicscrum.frontend.controllers.view.IndexViewBean;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author syslife02
 */
@Named(value = "loginRequestBean")
@RequestScoped
public class LoginRequestBean {

    private final Md5Converter md5Converter = new Md5Converter();    
    
    @Inject
    IndexViewBean indexViewBean;
    
    @EJB(lookup = UsersFacadeRemote.JNDI_REMOTE_NAME)
    UsersFacadeRemote userFacadeRemote;
    
    public String login() throws LoginException {        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
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
            FacesContext context = FacesContext.getCurrentInstance();
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
