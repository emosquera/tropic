/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.frontend.controllers.view.RegisterViewBean;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;



/**
 *
 * @author syslife02
 */
@Named(value = "fileUploadRequestBean")
@RequestScoped
public class FileUploadRequestBean implements Serializable {

    @Inject
    RegisterViewBean registerViewBean;
    
    public FileUploadRequestBean() {
    }    
    
    /**
     * Creates a new instance of FileUploadRequestBean
     * @param event
     */            
    
    public void handleFileUpload(FileUploadEvent event) {                
        try {        
            
            //Se procede a cambiar el tamano de la imagen a 150 x 150 px
            BufferedImage imageUploaded = ImageIO.read(event.getFile().getInputstream());            

            int newHeight = imageUploaded.getHeight();
            int newWidth = imageUploaded.getWidth();
            
            if (newHeight > 250) {
                newHeight = 250;
                newWidth = newHeight * imageUploaded.getWidth() / imageUploaded.getHeight();
            } else if (newHeight < 150) {
                newHeight = 150;
                newWidth = newHeight * imageUploaded.getWidth() / imageUploaded.getHeight();
            }                         

            if (newWidth > 550) {                
                newWidth = 550;
            } else if (newWidth < 150 ) {
                newWidth = 150;
            }
            
            BufferedImage outputImage = new BufferedImage(newWidth, newHeight, imageUploaded.getType());
            
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(imageUploaded, 0, 0, newWidth, newHeight, null);
            g2d.dispose();
            
            final Properties settingsProps = new Properties();
            settingsProps.load(getClass().getResourceAsStream("/settings.properties"));
            UUID name = UUID.randomUUID();
            String extension = event.getFile().getContentType().replace("image/", "");
            if (extension.contains("jpeg")) {
                extension = "jpg";
            }
            String path = "/" + settingsProps.getProperty("staticPath") + settingsProps.getProperty("folderImages") + "/" + name + "." + extension;
            
            ImageIO.write(outputImage, extension, new File(path));

            registerViewBean.setAvatarURL("/" + settingsProps.getProperty("folderImages") + "/" + name + "." + extension);                        
            
        } catch (IOException ex) {
            Logger.getLogger(FileUploadRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
}