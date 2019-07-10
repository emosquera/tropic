/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.util.Base64;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "activateUserViewBean")
@ViewScoped
public class ActivateUserViewBean implements Serializable {

    UsersFacadeRemote usersFacadeRemote = new ServiceLocatorDelegate<UsersFacadeRemote>().getService(UsersFacadeRemote.JNDI_REMOTE_NAME);

    private User user;
    private String message;
    
    @Inject
    FacesContext context;

    @PostConstruct
    public void init() {
        try {
            Map<String, String> parameterMap = (Map<String, String>) context.getExternalContext().getRequestParameterMap();
            String paramEncoded = parameterMap.get("id");
            String param = new String(Base64.getDecoder().decode(paramEncoded));
            user = usersFacadeRemote.find(Long.parseLong(param));
            if (!user.getConfirmed()) {
                user.setConfirmed(Boolean.TRUE);
                usersFacadeRemote.edit(user);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Enhorabuena!", "Ahora puedes ingresar al sistema usando tu email y contrasena"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion!", "Este usuario ya se encuentra activo"));
            }                                     
        } catch (UpdateException | NumberFormatException e) {
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
