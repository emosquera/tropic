/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.enums.Gender;
import com.tropicscrum.backend.client.model.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.CroppedImage;

/**
 *
 * @author syslife02
 */
@Named(value = "registerViewBean")
@ViewScoped
public class RegisterViewBean implements Serializable {

    private User user;
    private String avatarURL;
    private CroppedImage croppedImage;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Gender[] getGenders() {
        return Gender.values();
    }

    public String getAvatarURL() {
        if (user.getAvatar() == null) {
            avatarURL = "/images/default-user.png";
        }
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
        user.setAvatar(avatarURL);
    }

    public CroppedImage getCroppedImage() {    
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    
    /**
     * Creates a new instance of RegisterViewBean
     */
    public RegisterViewBean() {
    }
    
    @PostConstruct
    public void init() {
        user = new User();
    }        
}
