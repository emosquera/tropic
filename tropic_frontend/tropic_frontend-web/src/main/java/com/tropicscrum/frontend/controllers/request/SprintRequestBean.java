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
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.client.utils.Fibonacci;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import com.tropicscrum.frontend.controllers.view.SprintViewBean;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
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
@Named(value = "sprintRequestBean")
@RequestScoped
public class SprintRequestBean implements Serializable {

    SprintUser sprintUserToRemove;
    
    private final Fibonacci fibonacci = new Fibonacci();
    
    @Inject
    SprintViewBean sprintViewBean;

    UsersFacadeRemote usersFacadeRemote = new ServiceLocatorDelegate<UsersFacadeRemote>().getService(UsersFacadeRemote.JNDI_REMOTE_NAME);

    SprintFacadeRemote sprintFacadeRemote = new ServiceLocatorDelegate<SprintFacadeRemote>().getService(SprintFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    FacesContext context;

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
    
    private void clean() {
        Project project = new Project();
        if (sprintViewBean.getLockProject()) {
            project = sprintViewBean.getSprint().getProject();
        }
        sprintViewBean.setSprint(new Sprint());
        sprintViewBean.getSprint().setSprintVelocitys(fibonacci.getSprintVelocitys());
        sprintViewBean.getSprint().getSprintVelocitys().forEach((sv) -> {
            sv.setSprint(sprintViewBean.getSprint());
        });
        sprintViewBean.getSprint().setProject(project);
        sprintViewBean.setModify(Boolean.FALSE);
        sprintViewBean.setDelete(Boolean.FALSE);
        sprintViewBean.setUserSelected(null);
        sprintViewBean.setEditingPerson(Boolean.FALSE);
    }    
    
    public void newSprint (ActionEvent actionEvent) {
        clean();
    }
    
    public Collection<User> getUsersByEmail(String query) {
        Collection<User> users = usersFacadeRemote.filterByEmail(query); 
        sprintViewBean.getSprint().getSprintUsers().forEach((sprintUser) -> {
            users.remove(sprintUser.getUser());
        });        
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
            clean();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sprint creado satisfactoriamente"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo crear el Sprint"));
        }
    }
    
    public void removeSprint(ActionEvent actionEvent) {
        try {
            sprintViewBean.getSprints().remove(sprintViewBean.getSprint());
            sprintFacadeRemote.remove(sprintViewBean.getSprint());
            clean();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sprint eliminado satisfactoriamente"));
        } catch (Exception ex) {
            sprintViewBean.getSprints().add(sprintViewBean.getSprint());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo eliminar el Sprint"));
        }
    }
    
    public void updateSprint(ActionEvent actionEvent) {
        try {
            sprintFacadeRemote.edit(sprintViewBean.getSprint());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sprint actualizado satisfactoriamente"));
        } catch (UpdateException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", e.getMessage()));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo actualizar el Sprint"));
        }
    }
}
