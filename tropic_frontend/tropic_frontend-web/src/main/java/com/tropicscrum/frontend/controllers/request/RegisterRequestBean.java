/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.utils.Md5Converter;
import com.tropicscrum.frontend.controllers.view.RegisterViewBean;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


/**
 *
 * @author syslife02
 */
@Named(value = "registerRequestBean")
@RequestScoped
public class RegisterRequestBean implements Serializable {
    
    private final Md5Converter md5Converter = new Md5Converter();

    @Inject
    RegisterViewBean registerViewBean;
    
    @EJB(lookup = UsersFacadeRemote.JNDI_REMOTE_NAME)
    UsersFacadeRemote userFacadeRemote;
    
    /**
     * Creates a new instance of RegisterRequestBean
     */
    public RegisterRequestBean() {
    }
    
    public void registerUser() {  
        try {                            
            registerViewBean.getUser().setPassword(md5Converter.StringToMD5(registerViewBean.getUser().getPassword()));
            if (registerViewBean.getUser().getAvatar() == null) {
                registerViewBean.getUser().setAvatar("/static/images/default-user.png");
            }
            registerViewBean.setUser(userFacadeRemote.create(registerViewBean.getUser()));
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gracias!", "Usuario Registrado correctamente"));          
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar el usuario"));
        }
        
    }    
}
