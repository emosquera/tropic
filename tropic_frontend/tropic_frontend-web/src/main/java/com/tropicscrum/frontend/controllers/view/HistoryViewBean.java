/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.facade.HistoryFacadeRemote;
import com.tropicscrum.backend.client.facade.MilestoneFacadeRemote;
import com.tropicscrum.backend.client.facade.ProjectFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "historyViewBean")
@ViewScoped
public class HistoryViewBean implements Serializable {

    private User user;
    
    private History history;
    
    private Collection<History> histories;
    private Collection<History> collabHistories;
    private Boolean modify = false;
    private Boolean delete = false;
    private Boolean lockProject = false;
    private Collection<Milestone> historyMilestones;
    
    @EJB(lookup = ProjectFacadeRemote.JNDI_REMOTE_NAME)
    ProjectFacadeRemote projectFacadeRemote;
    
    @EJB(lookup = HistoryFacadeRemote.JNDI_REMOTE_NAME)
    HistoryFacadeRemote historyFacadeRemote;
    
    @EJB(lookup = MilestoneFacadeRemote.JNDI_REMOTE_NAME)
    MilestoneFacadeRemote milestoneFacadeRemote;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Collection<History> getHistories() {
        if (histories == null) {
            histories = new ArrayList<>();
        }
        return histories;
    }

    public void setHistories(Collection<History> histories) {
        this.histories = histories;
    }

    public Collection<History> getCollabHistories() {
        if (collabHistories == null) {
            return new ArrayList<>();
        }
        return collabHistories;
    }

    public void setCollabHistories(Collection<History> collabHistories) {
        this.collabHistories = collabHistories;
    }

    public Boolean getModify() {
        return modify;
    }

    public void setModify(Boolean modify) {
        this.modify = modify;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getLockProject() {
        return lockProject;
    }

    public void setLockProject(Boolean lockProject) {
        this.lockProject = lockProject;
    }

    public Collection<Milestone> getHistoryMilestones() {
        if (history == null) {
            historyMilestones = new ArrayList<>();
        } else {
            historyMilestones = milestoneFacadeRemote.findHistoryMilestones(history);
            if (historyMilestones == null) {
                historyMilestones = new ArrayList<>();
            }
        }
        return historyMilestones;
    }

    public void setHistoryMilestones(Collection<Milestone> historyMilestones) {
        this.historyMilestones = historyMilestones;
    }
    
    /**
     * Creates a new instance of HistoryViewBean
     */
    public HistoryViewBean() {
    }
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User) session.getAttribute("user");
        
        final History redirectHistory = (History) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("history");
        if (redirectHistory != null) {
            setHistory(redirectHistory);
            setModify(Boolean.TRUE);
            if (redirectHistory.getAuthor().equals(user)) {
                setDelete(Boolean.TRUE);
            } else {
                setDelete(Boolean.FALSE);
            }
        } else {
            setHistory(new History());
        }        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().remove("history");                     
        
        setHistories(historyFacadeRemote.findAllMine(user));
        setCollabHistories(historyFacadeRemote.findAllMyCollabs(user));        
    }
}
