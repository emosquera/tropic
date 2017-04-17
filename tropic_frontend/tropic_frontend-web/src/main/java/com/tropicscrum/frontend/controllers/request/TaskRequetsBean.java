/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.TaskFacadeRemote;
import com.tropicscrum.backend.client.model.Artifact;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.frontend.controllers.view.TaskViewBean;
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
 * @author Edgar Mosquera
 */
@Named(value = "taskRequetsBean")
@RequestScoped
public class TaskRequetsBean implements Serializable {

    @Inject 
    TaskViewBean taskViewBean; 
    
    @EJB(lookup = TaskFacadeRemote.JNDI_REMOTE_NAME)
    TaskFacadeRemote taskFacadeRemote;
    
    /**
     * Creates a new instance of TaskRequetsBean
     */
    public TaskRequetsBean() {
    }
    
    private void clean() {
        Artifact artifact = new Artifact();
        if (taskViewBean.getLockSprint()) {
            artifact = taskViewBean.getTask().getArtifact();            
        } else {
            taskViewBean.setSprintSelected(new Sprint());
            taskViewBean.setHistorySelected(new History());
            taskViewBean.setMilestoneSelected(new Milestone());
        }
        taskViewBean.setTask(new Task());
        taskViewBean.getTask().setArtifact(artifact);        
        taskViewBean.setModify(Boolean.FALSE);
        taskViewBean.setDelete(Boolean.FALSE);
    }
    
    public void newTask(ActionEvent actionEvent) {
        clean();
    }
    
    public void createTask(ActionEvent actionEvent) {
        try {
            taskViewBean.getTask().setAuthor(taskViewBean.getUser());
            taskViewBean.getTask().setStatus(GeneralStatus.PENDING);
            Task t = taskFacadeRemote.create(taskViewBean.getTask());
            taskViewBean.getMyTasks().add(t);
            clean();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Tarea creada satisfactoriamente"));
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo registrar la Tarea"));
        }
    }
    
    public void removeTask(ActionEvent actionEvent) {
        try {
            taskFacadeRemote.remove(taskViewBean.getTask());
            taskViewBean.getMyTasks().remove(taskViewBean.getTask());
            clean();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Tarea eliminada satisfactoriamente"));
        } catch (Exception ex) {
            taskViewBean.getMyTasks().add(taskViewBean.getTask());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "No se pudo eliminar la Tarea"));
        }
    }
    
    public void updateTask(ActionEvent actionEvent) {
        try {
            taskFacadeRemote.edit(taskViewBean.getTask());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Tarea actualizada satisfactoriamente"));
        } catch (UpdateException e) {
           taskViewBean.setTask(taskFacadeRemote.find(taskViewBean.getTask().getId()));
           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", e.getMessage()));
        } catch (Exception ex) {
           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo actualizar la Tarea")); 
        }
    }
    
}
