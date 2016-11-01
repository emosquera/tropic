/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.exceptions.LoginException;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.utils.Md5Converter;
import com.tropicscrum.frontend.controllers.view.IndexViewBean;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

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
    
    public void login(ActionEvent actionEvent) throws LoginException {        
        try {
            try {
                userFacadeRemote.login(indexViewBean.getUser().getEmail(), md5Converter.StringToMD5(indexViewBean.getUser().getPassword()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (LoginException ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
            indexViewBean.getUser().setPassword("");
        }        
    }
    /**
     * Creates a new instance of LoginRequestBean
     */
    public LoginRequestBean() {
    }    
}
