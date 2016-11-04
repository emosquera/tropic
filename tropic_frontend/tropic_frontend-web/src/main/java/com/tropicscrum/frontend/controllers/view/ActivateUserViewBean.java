/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.User;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.util.Base64;

/**
 *
 * @author syslife02
 */
@Named(value = "activateUserViewBean")
@ViewScoped
public class ActivateUserViewBean implements Serializable {

    @EJB(lookup = UsersFacadeRemote.JNDI_REMOTE_NAME)
    UsersFacadeRemote usersFacadeRemote;

    private User user;
    private String message;

    @PostConstruct
    public void init() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, String> parameterMap = (Map<String, String>) context.getExternalContext().getRequestParameterMap();
            String paramEncoded = parameterMap.get("id");
            String param = new String(Base64.decode(paramEncoded));
            user = usersFacadeRemote.find(Long.parseLong(param));
            if (!user.getConfirmed()) {
                user.setConfirmed(Boolean.TRUE);
                usersFacadeRemote.edit(user);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Enhorabuena!", "Ahora puedes ingresar al sistema usando tu email y contrasena"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion!", "Este usuario ya se encuentra activo"));
            }                                     
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error al activar tu cuenta"));
        }

    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Creates a new instance of ActivateUserViewBean
     */
    public ActivateUserViewBean() {
    }

    
}
