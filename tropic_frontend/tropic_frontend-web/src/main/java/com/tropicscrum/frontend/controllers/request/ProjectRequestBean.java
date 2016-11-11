/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.facade.ProjectFacadeRemote;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.frontend.controllers.view.ProjectViewBean;
import java.io.Serializable;
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
@Named(value = "projectRequestBean")
@RequestScoped
public class ProjectRequestBean implements Serializable {

    @Inject
    ProjectViewBean projectViewBean;
    
    @EJB(lookup = ProjectFacadeRemote.JNDI_REMOTE_NAME) 
    ProjectFacadeRemote projectFacadeRemote;
    
    @EJB(lookup = UsersFacadeRemote.JNDI_REMOTE_NAME) 
    UsersFacadeRemote usersFacadeRemote;
    
    /**
     * Creates a new instance of ProjectRequestBean
     */
    public ProjectRequestBean() {
    }
    
    public void registerProject(ActionEvent actionEvent) {
        try {
            projectViewBean.getProject().setAuthor(projectViewBean.getUser());       
            Project p = projectFacadeRemote.create(projectViewBean.getProject());  
            projectViewBean.setModify(Boolean.TRUE);
            projectViewBean.getMyProyects().add(p);
            usersFacadeRemote.edit(projectViewBean.getUser());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Proyecto creado satisfactoriamente"));
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo registrar el Proyecto"));
        }
    }
    
    public void updateProject(ActionEvent actionEvent) {
        try {            
            projectViewBean.setModify(Boolean.TRUE);
            projectFacadeRemote.edit(projectViewBean.getProject());              
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Proyecto actualizado satisfactoriamente"));
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo actualizar el Proyecto"));
        }
    }
    
    public void newProject(ActionEvent actionEvent) {
        projectViewBean.setProject(new Project());
        projectViewBean.setModify(Boolean.FALSE);
    }
}
