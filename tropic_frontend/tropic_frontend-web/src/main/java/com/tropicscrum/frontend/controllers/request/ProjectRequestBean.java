/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.ProjectFacadeRemote;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import com.tropicscrum.frontend.controllers.view.ProjectViewBean;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "projectRequestBean")
@RequestScoped
public class ProjectRequestBean implements Serializable {

    private History historyRedirect;
    
    @Inject
    ProjectViewBean projectViewBean;

    ProjectFacadeRemote projectFacadeRemote = new ServiceLocatorDelegate<ProjectFacadeRemote>().getService(ProjectFacadeRemote.JNDI_REMOTE_NAME);

    UsersFacadeRemote usersFacadeRemote = new ServiceLocatorDelegate<UsersFacadeRemote>().getService(UsersFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    FacesContext context;
    
    @Inject
    ExternalContext extContext;

    public History getHistoryRedirect() {
        return historyRedirect;
    }

    public void setHistoryRedirect(History historyRedirect) {
        this.historyRedirect = historyRedirect;
    }

    /**
     * Creates a new instance of ProjectRequestBean
     */
    public ProjectRequestBean() {
    }
    
    public void registerProject(ActionEvent actionEvent) {
        try {
            projectViewBean.getProject().setAuthor(projectViewBean.getUser());       
            Project p = projectFacadeRemote.create(projectViewBean.getProject());  
            projectViewBean.getMyProyects().add(p);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Proyecto creado satisfactoriamente"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo registrar el Proyecto"));
        }
    }
    
    public void updateProject(ActionEvent actionEvent) {
        try {            
            projectFacadeRemote.edit(projectViewBean.getProject());              
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Proyecto actualizado satisfactoriamente"));
        } catch (UpdateException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", e.getMessage()));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo actualizar el Proyecto"));
        }
    }
    
    public void newProject(ActionEvent actionEvent) {
        projectViewBean.setProject(new Project());
        projectViewBean.setModify(Boolean.FALSE);
        projectViewBean.setDelete(Boolean.FALSE);
    }
    
    public void deleteProject(ActionEvent actionEvent) {
        try {  
            projectViewBean.getMyProyects().remove(projectViewBean.getProject());
            projectFacadeRemote.remove(projectViewBean.getProject());     
            projectViewBean.setModify(Boolean.FALSE);
            projectViewBean.setDelete(Boolean.FALSE);
            projectViewBean.setProject(new Project());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Proyecto eliminado satisfactoriamente"));
        } catch (Exception ex) {
            projectViewBean.getMyProyects().add(projectViewBean.getProject());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo eliminar el Proyecto"));
        }        
    }
    
    public Collection<User> getUsersByEmail(String query) {
        Collection<User> users = usersFacadeRemote.getAllContainsEmailExceptYou(projectViewBean.getUser(), query); 
        users.removeAll(projectViewBean.getProject().getCollaborators());
        return users;
                
    }
    
    public String goToHistory() {
        if (historyRedirect != null) {
            extContext.getFlash().put("history", historyRedirect);
        }
        return "/home/history?faces-redirect=true";
    }
}
