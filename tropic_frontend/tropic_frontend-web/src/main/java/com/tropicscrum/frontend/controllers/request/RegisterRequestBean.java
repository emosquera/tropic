/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.utils.Md5Converter;
import com.tropicscrum.frontend.controllers.view.RegisterViewBean;
import com.tropicscrum.frontend.utils.RequestDomain;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.primefaces.util.Base64;


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
            String encodedId = Base64.encodeToString(registerViewBean.getUser().getId().toString().getBytes(), true);
            RequestDomain requestDomain = new RequestDomain();
            FacesContext context = FacesContext.getCurrentInstance();    
            InputStream input = new URL(requestDomain.getApplicationPath(context) + "/template/email/confirmEmail.xhtml?id=" + encodedId).openStream();
            String result = IOUtils.toString(input, StandardCharsets.UTF_8);
            userFacadeRemote.sendConfirmEmail(registerViewBean.getUser(), result);                            
        } catch (NoSuchAlgorithmException | IOException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar el usuario"));
        }
        
    }    
}
