/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.email;

import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.frontend.utils.RequestDomain;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.util.Base64;

/**
 *
 * @author syslife02
 */
@Named(value = "confirmEmail")
@ViewScoped
public class confirmEmail implements Serializable {
    
    private final RequestDomain requestDomain = new RequestDomain();
    private final FacesContext context = FacesContext.getCurrentInstance();
    
    @EJB(lookup = UsersFacadeRemote.JNDI_REMOTE_NAME)
    UsersFacadeRemote usersFacadeRemote;
    
    private User user;
    private String encodedId;
    private String applicationPath;
    
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEncodedId() {
        return encodedId;
    }

    public void setEncodedId(String encodedId) {
        this.encodedId = encodedId;
    }

    public String getApplicationPath() {            
        applicationPath = requestDomain.getApplicationPath(context);
        return applicationPath;
    }

    public void setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
    }

    /**
     * Creates a new instance of confirmEmail
     */
    public confirmEmail() {
    }
    
    @PostConstruct
    public void init() {
        ExternalContext extContext = context.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) extContext.getRequestParameterMap();
        String paramEncoded = parameterMap.get("id");
        String param = new String(Base64.decode(paramEncoded));        
        User myUser = usersFacadeRemote.find(Long.parseLong(param));            
        setUser(myUser);
        setEncodedId(Base64.encodeToString(getUser().getId().toString().getBytes(), true));
    }
    
}
