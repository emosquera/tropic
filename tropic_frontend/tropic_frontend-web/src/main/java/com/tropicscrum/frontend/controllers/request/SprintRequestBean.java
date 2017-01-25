/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.SprintFacadeRemote;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.Schedule;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.frontend.controllers.view.SprintViewBean;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named(value = "sprintRequestBean")
@RequestScoped
public class SprintRequestBean implements Serializable {

    SprintUser sprintUserToRemove;
    
    @Inject
    SprintViewBean sprintViewBean;
    
    @EJB(lookup = UsersFacadeRemote.JNDI_REMOTE_NAME)
    UsersFacadeRemote usersFacadeRemote;
    
    @EJB(lookup = SprintFacadeRemote.JNDI_REMOTE_NAME)
    SprintFacadeRemote sprintFacadeRemote;

    public SprintUser getSprintUserToRemove() {
        return sprintUserToRemove;
    }

    public void setSprintUserToRemove(SprintUser sprintUserToRemove) {
        this.sprintUserToRemove = sprintUserToRemove;
    }
    
    /**
     * Creates a new instance of SprintRequestBean
     */
    public SprintRequestBean() {
    }
    
    public void createNewSprint () {
        sprintViewBean.setSprint(new Sprint());
        sprintViewBean.setModify(Boolean.FALSE);
        sprintViewBean.setDelete(Boolean.FALSE);
        sprintViewBean.setUserSelected(null);
        sprintViewBean.setEditingPerson(Boolean.FALSE);
    }
    
    public void newSprint (ActionEvent actionEvent) {
        createNewSprint();
    }
    
    public Collection<User> getUsersByEmail(String query) {
        Collection<User> users = usersFacadeRemote.filterByEmail(query); 
        for (SprintUser sprintUser : sprintViewBean.getSprint().getSprintUsers()) {
            users.remove(sprintUser.getUser());
        }        
        return users;                
    }
    
    public void changeColor () {        
        sprintViewBean.setStyleColor("color" + sprintViewBean.getSprintUserSelected().getColor().toString());        
    }
    
    public void setDefaultSchedule() {
        sprintViewBean.setEditingPerson(Boolean.FALSE);
        sprintViewBean.getSprintUserSelected().setSchedules(sprintViewBean.getUserSchedules());
    }
    
    public void addPersonTeam(ActionEvent actionEvent) {
        sprintViewBean.getSprintUserSelected().setUser(sprintViewBean.getUserSelected());
        sprintViewBean.getSprintUserSelected().setSprint(sprintViewBean.getSprint());
        sprintViewBean.getSprint().getSprintUsers().add(sprintViewBean.getSprintUserSelected());
        sprintViewBean.setUserSelected(null);
        sprintViewBean.setSprintUserSelected(null);
    }
    
    public void removePersonTeam() {
        for (Iterator<SprintUser> sprintUsers = sprintViewBean.getSprint().getSprintUsers().iterator(); sprintUsers.hasNext();) {
            SprintUser sprintUser = sprintUsers.next();
            if (sprintUser.getUser().equals(this.sprintUserToRemove.getUser())) {
                sprintUsers.remove();
                break;
            }
        }        
    }
    
    public void cleanPerson(ActionEvent actionEvent) {
        sprintViewBean.setEditingPerson(Boolean.FALSE);
        sprintViewBean.setUserSelected(null);
        sprintViewBean.setSprintUserSelected(null);
    }
    
    public void registerSprint(ActionEvent actionEvent) {
        try {
            sprintViewBean.getSprint().setAuthor(sprintViewBean.getUser());       
            Sprint s = sprintFacadeRemote.create(sprintViewBean.getSprint());  
            sprintViewBean.getSprints().add(s);
            createNewSprint();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sprint creado satisfactoriamente"));
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo crear el Sprint"));
        }
    }
    
    public void removeSprint(ActionEvent actionEvent) {
        try {
            sprintViewBean.getSprints().remove(sprintViewBean.getSprint());
            sprintFacadeRemote.remove(sprintViewBean.getSprint());
            createNewSprint();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sprint eliminado satisfactoriamente"));
        } catch (Exception ex) {
            sprintViewBean.getSprints().add(sprintViewBean.getSprint());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo eliminar el Sprint"));
        }
    }
    
    public void updateSprint(ActionEvent actionEvent) {
        try {
            sprintFacadeRemote.edit(sprintViewBean.getSprint());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sprint actualizado satisfactoriamente"));
        } catch (UpdateException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", e.getMessage()));
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo actualizar el Sprint"));
        }
    }
}
