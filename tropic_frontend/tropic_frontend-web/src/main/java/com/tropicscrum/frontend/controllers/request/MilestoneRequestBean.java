/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.HistoryFacadeRemote;
import com.tropicscrum.backend.client.facade.MilestoneFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.frontend.controllers.view.MilestoneViewBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
@Named(value = "milestoneRequestBean")
@RequestScoped
public class MilestoneRequestBean implements Serializable {

    
    @Inject
    MilestoneViewBean milestoneViewBean;
    
    @EJB(lookup = MilestoneFacadeRemote.JNDI_REMOTE_NAME)
    MilestoneFacadeRemote milestoneFacadeRemote;
    
    @EJB(lookup = HistoryFacadeRemote.JNDI_REMOTE_NAME)
    HistoryFacadeRemote historyFacadeRemote;
    /**
     * Creates a new instance of MilestoneRequestBean
     */
    public MilestoneRequestBean() {
    }
    
    private void clean() {
        Sprint sprint = new Sprint();
        History history = new History();
        if (milestoneViewBean.getLockSprint()) {
            sprint = milestoneViewBean.getMilestone().getSprint();
            history = milestoneViewBean.getMilestone().getHistory();
        }
        milestoneViewBean.setMilestone(new Milestone());
        milestoneViewBean.getMilestone().setSprint(sprint);
        milestoneViewBean.getMilestone().setHistory(history);
        milestoneViewBean.setModify(Boolean.FALSE);
        milestoneViewBean.setDelete(Boolean.FALSE);
    }
    
    public void newMilestone(ActionEvent actionEvent) {
        clean();
    }
    
    public void createMilestone(ActionEvent actionEvent) {
        try {
            milestoneViewBean.getMilestone().setAuthor(milestoneViewBean.getUser());
            Milestone m = milestoneFacadeRemote.create(milestoneViewBean.getMilestone());
            milestoneViewBean.getMilestones().add(m);
            clean();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Hito creado satisfactoriamente"));
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo registrar el Hito"));
        }
    }
    
    public void updateMilestone(ActionEvent actionEvent) {
        try {
            milestoneFacadeRemote.edit(milestoneViewBean.getMilestone());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Hito actualizado satisfactoriamente"));
        } catch (UpdateException e) {
            milestoneViewBean.setMilestone(milestoneFacadeRemote.find(milestoneViewBean.getMilestone().getId()));
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", e.getMessage()));
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo actualizar el Hito"));
        }
    }
    
    public void removeMilestone(ActionEvent actionEvent) {
        try {
            milestoneViewBean.getMilestones().remove(milestoneViewBean.getMilestone());
            milestoneFacadeRemote.remove(milestoneViewBean.getMilestone());
            clean();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Hito eliminado satisfactoriamente"));
        } catch (Exception ex) {
            milestoneViewBean.getMilestones().add(milestoneViewBean.getMilestone());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo eliminar el Hito"));
        }
    }
}
