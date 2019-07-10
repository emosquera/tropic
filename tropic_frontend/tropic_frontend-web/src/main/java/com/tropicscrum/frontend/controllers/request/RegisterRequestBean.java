/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.utils.Md5Converter;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import com.tropicscrum.frontend.controllers.view.RegisterViewBean;
import com.tropicscrum.frontend.utils.RequestDomain;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import java.util.Base64;


/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "registerRequestBean")
@RequestScoped
public class RegisterRequestBean implements Serializable {
    
    private final Md5Converter md5Converter = new Md5Converter();

    @Inject
    RegisterViewBean registerViewBean;

    UsersFacadeRemote userFacadeRemote = new ServiceLocatorDelegate<UsersFacadeRemote>().getService(UsersFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    FacesContext context;
    
    /**
     * Creates a new instance of RegisterRequestBean
     */
    public RegisterRequestBean() {
    }
    
    public String registerUser() {  
        try {                            
            registerViewBean.getUser().setPassword(md5Converter.StringToMD5(registerViewBean.getUser().getPassword()));
            if (registerViewBean.getUser().getAvatar() == null) {
                registerViewBean.getUser().setAvatar("/images/default-user.png");
            }                                 
            if (!registerViewBean.getUser().getAvatar().equals("/images/default-user.png")) {
                if (registerViewBean.getCroppedImage() != null) {
                    final Properties settingsProps = new Properties();
                    settingsProps.load(getClass().getResourceAsStream("/settings.properties"));
                    String path = "/" + settingsProps.getProperty("staticPath") + registerViewBean.getUser().getAvatar();
                    try (FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path))) {
                        imageOutput.write(registerViewBean.getCroppedImage().getBytes(), 0, registerViewBean.getCroppedImage().getBytes().length);
                    }
                }
            }
            registerViewBean.setUser(userFacadeRemote.create(registerViewBean.getUser()));
            String encodedId = Base64.getEncoder().encodeToString(registerViewBean.getUser().getId().toString().getBytes());
            RequestDomain requestDomain = new RequestDomain();
            InputStream input = new URL(requestDomain.getApplicationPath(context) + "/template/email/confirmEmail.xhtml?id=" + encodedId).openStream();
            String result = IOUtils.toString(input, StandardCharsets.UTF_8);
            userFacadeRemote.sendConfirmEmail(registerViewBean.getUser(), result);                            
        } catch (NoSuchAlgorithmException | IOException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar el usuario"));
        }
        
        return "thanks_register?faces-redirect=true";
        
    }    
}
