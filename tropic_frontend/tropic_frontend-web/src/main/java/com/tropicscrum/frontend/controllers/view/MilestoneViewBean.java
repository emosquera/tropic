/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.facade.MilestoneFacadeRemote;
import com.tropicscrum.backend.client.facade.SprintFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
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
@Named(value = "milestoneViewBean")
@ViewScoped
public class MilestoneViewBean implements Serializable{
    private User user;
    
    private Milestone milestone;
    
    private Collection<Milestone> milestones;
    private Collection<Milestone> collabMilestones;
    
    private Boolean modify = false;
    private Boolean delete = false;
        
    private Collection<History> projectHistories;
    
    private Collection<Sprint> mySprints; 
    
    private Boolean lockSprint = false;
    
    @EJB(lookup = SprintFacadeRemote.JNDI_REMOTE_NAME)
    SprintFacadeRemote sprintFacadeRemote;    
    
    @EJB(lookup = MilestoneFacadeRemote.JNDI_REMOTE_NAME)
    MilestoneFacadeRemote milestoneFacadeRemote;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Collection<Milestone> getMilestones() {
        if (milestones == null) {
            milestones = new ArrayList<>();
        }
        return milestones;
    }

    public void setMilestones(Collection<Milestone> milestones) {
        this.milestones = milestones;
    }

    public Collection<Milestone> getCollabMilestones() {
        if (collabMilestones == null) {
            collabMilestones = new ArrayList<>();
        }
        return collabMilestones;
    }

    public void setCollabMilestones(Collection<Milestone> collabMilestones) {
        this.collabMilestones = collabMilestones;
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

    public Collection<History> getProjectHistories() {
        return projectHistories;
    }

    public void setProjectHistories(Collection<History> projectHistories) {
        this.projectHistories = projectHistories;
    }

    public Boolean getLockSprint() {
        return lockSprint;
    }

    public void setLockSprint(Boolean lockSprint) {
        this.lockSprint = lockSprint;
    }

    public Collection<Sprint> getMySprints() {
        if (mySprints == null) {
            mySprints = new ArrayList<>();
        }
        return mySprints;
    }

    public void setMySprints(Collection<Sprint> mySprints) {
        this.mySprints = mySprints;
    }

    
    /**
     * Creates a new instance of MilestoneViewBean
     */
    public MilestoneViewBean(){
    }
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User) session.getAttribute("user");    
        
        final Milestone redirectMilestone = (Milestone) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("milestone");
        
        if (redirectMilestone != null) {
            setMilestone(redirectMilestone);
            setModify(Boolean.TRUE);
            if (redirectMilestone.getAuthor().equals(user)) {
                setDelete(Boolean.TRUE);
            } else {
                setDelete(Boolean.FALSE);
            }
        } else {
            setMilestone(new Milestone());
        }
        
        setMySprints(sprintFacadeRemote.findSprintsCreateTask(user));
        
        setMilestones(milestoneFacadeRemote.findAllMine(user));
        setCollabMilestones(milestoneFacadeRemote.findAllMyColabs(user));
    }
    
}
