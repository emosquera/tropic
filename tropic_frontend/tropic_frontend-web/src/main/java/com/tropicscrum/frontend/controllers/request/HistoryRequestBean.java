/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.HistoryFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.frontend.controllers.view.HistoryViewBean;
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
@Named(value = "historyRequestBean")
@RequestScoped
public class HistoryRequestBean implements Serializable {

    private Milestone milestoneRedirect;
    
    @Inject
    HistoryViewBean historyViewBean;
    
    @EJB(lookup = HistoryFacadeRemote.JNDI_REMOTE_NAME)
    HistoryFacadeRemote historyFacadeRemote;

    public Milestone getMilestoneRedirect() {
        return milestoneRedirect;
    }

    public void setMilestoneRedirect(Milestone milestoneRedirect) {
        this.milestoneRedirect = milestoneRedirect;
    }
    
    /**
     * Creates a new instance of HistoryRequestBean
     */
    public HistoryRequestBean() {
    }
    
    public void registerHistory(ActionEvent actionEvent) {
        try {
            historyViewBean.getHistory().setAuthor(historyViewBean.getUser());
            History h = historyFacadeRemote.create(historyViewBean.getHistory());
            historyViewBean.getHistories().add(h);           
            clean();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Historia creada satisfactoriamente"));
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo registrar la Historia"));
        }
    }
    
    public void updateHistory(ActionEvent actionEvent) {
        try {
            historyFacadeRemote.edit(historyViewBean.getHistory());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Historia actualizada satisfactoriamente"));
        } catch (UpdateException e) {
            historyViewBean.setHistory(historyFacadeRemote.find(historyViewBean.getHistory().getId()));
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", e.getMessage()));
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo actualizar la Historia"));
        }
    }
    
    private void clean() {
        Project project = new Project();
        if (historyViewBean.getLockProject()) {
            project = historyViewBean.getHistory().getProject();            
        } 
        historyViewBean.setHistory(new History());
        historyViewBean.getHistory().setProject(project);
        historyViewBean.setModify(Boolean.FALSE);
        historyViewBean.setDelete(Boolean.FALSE);
    }
    
    public void newHistory(ActionEvent actionEvent) {
        clean();
    }
    
    public void removeHistory(ActionEvent actionEvent) {
        try {
            historyViewBean.getHistories().remove(historyViewBean.getHistory());
            historyFacadeRemote.remove(historyViewBean.getHistory());            
            clean();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Historia eliminada satisfactoriamente"));
        } catch (Exception ex) {
            historyViewBean.getHistories().add(historyViewBean.getHistory());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo eliminar la Historia"));
        }
    }
    
    public String goToMilestone() {
        if ( milestoneRedirect != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("milestone", milestoneRedirect);
        }
        return "/home/milestone?faces-redirect=true";
    }
}
