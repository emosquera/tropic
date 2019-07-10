/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.ArtifactFacadeRemote;
import com.tropicscrum.backend.client.model.Artifact;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import com.tropicscrum.frontend.controllers.view.ArtifactViewBean;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "artifactRequetsBean")
@RequestScoped
public class ArtifactRequetsBean implements Serializable {

    @Inject 
    ArtifactViewBean artifactViewBean; 
    
    ArtifactFacadeRemote artifactFacadeRemote = new ServiceLocatorDelegate<ArtifactFacadeRemote>().getService(ArtifactFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    FacesContext context;
    
    /**
     * Creates a new instance of TaskRequetsBean
     */
    public ArtifactRequetsBean() {
    }
    
    private void clean() {
        Milestone milestone = new Milestone();
        if (artifactViewBean.getLockSprint()) {
            
            milestone = artifactViewBean.getArtifact().getMilestone();
        } else {
            artifactViewBean.setSprintSelected(new Sprint());
            artifactViewBean.setHistorySelected(new History());            
        }
        artifactViewBean.setArtifact(new Artifact()); 
        artifactViewBean.getArtifact().setMilestone(milestone);
        artifactViewBean.setModify(Boolean.FALSE);
        artifactViewBean.setDelete(Boolean.FALSE);
    }
    
    public void newArtifact(ActionEvent actionEvent) {
        clean();
    }
    
    public void createArtifact(ActionEvent actionEvent) {
        try {
            artifactViewBean.getArtifact().setAuthor(artifactViewBean.getUser());            
            Artifact a = artifactFacadeRemote.create(artifactViewBean.getArtifact());
            artifactViewBean.getMyArtifacts().add(a);
            clean();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Componente creado satisfactoriamente"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo registrar el Componente"));
        }
    }
    
    public void removeArtifact(ActionEvent actionEvent) {
        try {
            artifactFacadeRemote.remove(artifactViewBean.getArtifact());
            artifactViewBean.getMyArtifacts().remove(artifactViewBean.getArtifact());
            clean();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Componente eliminado satisfactoriamente"));
        } catch (Exception ex) {
            artifactViewBean.getMyArtifacts().add(artifactViewBean.getArtifact());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "No se pudo eliminar el Componente"));
        }
    }
    
    public void updateArtifact(ActionEvent actionEvent) {
        try {
            artifactFacadeRemote.edit(artifactViewBean.getArtifact());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Componente actualizado satisfactoriamente"));
        } catch (UpdateException e) {
           artifactViewBean.setArtifact(artifactFacadeRemote.find(artifactViewBean.getArtifact().getId()));
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", e.getMessage()));
        } catch (Exception ex) {
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo actualizar el Componente")); 
        }
    }
    
}
