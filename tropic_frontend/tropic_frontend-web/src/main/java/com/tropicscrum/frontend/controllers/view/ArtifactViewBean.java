/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.enums.MeasureUnit;
import com.tropicscrum.backend.client.facade.ArtifactFacadeRemote;
import com.tropicscrum.backend.client.facade.SprintFacadeRemote;
import com.tropicscrum.backend.client.facade.TechnologyFacadeRemote;
import com.tropicscrum.backend.client.model.Artifact;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.Technology;
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
@Named(value = "artifactViewBean")
@ViewScoped
public class ArtifactViewBean implements Serializable {

    private User user;
    private Artifact artifact;
    private Boolean modify = false;
    private Boolean delete = false;
    private Boolean lockSprint = false;
    
    private Collection<Sprint> mySprints;
    
    private Sprint sprintSelected;
    private History historySelected;
    
    private Collection<Artifact> myArtifacts;
    private Collection<Artifact> myCollabArtifacts;
    private Collection<Technology> technologies;
    
    @EJB(lookup = SprintFacadeRemote.JNDI_REMOTE_NAME)
    SprintFacadeRemote sprintFacadeRemote;
    
    @EJB(lookup = ArtifactFacadeRemote.JNDI_REMOTE_NAME)
    ArtifactFacadeRemote artifactFacadeRemote;
    
    @EJB(lookup = TechnologyFacadeRemote.JNDI_REMOTE_NAME)
    TechnologyFacadeRemote technologyFacadeRemote;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
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

    public Sprint getSprintSelected() {
        return sprintSelected;
    }

    public void setSprintSelected(Sprint sprintSelected) {
        this.sprintSelected = sprintSelected;
    }

    public History getHistorySelected() {
        return historySelected;
    }

    public void setHistorySelected(History historySelected) {
        this.historySelected = historySelected;
    }

    public Collection<Artifact> getMyArtifacts() {
        if (myArtifacts == null) {
            myArtifacts = new ArrayList<>();
        }
        return myArtifacts;
    }

    public void setMyArtifacts(Collection<Artifact> myArtifacts) {
        this.myArtifacts = myArtifacts;
    }

    public Collection<Artifact> getMyCollabArtifacts() {
        if (myCollabArtifacts == null) {
            myCollabArtifacts = new ArrayList<>();
        }
        return myCollabArtifacts;
    }

    public void setMyCollabArtifacts(Collection<Artifact> myCollabArtifacts) {
        this.myCollabArtifacts = myCollabArtifacts;
    }

    public Collection<Technology> getTechnologies() {
        if (technologies == null) {
            technologies = new ArrayList<>();
        }
        return technologies;
    }

    public void setTechnologies(Collection<Technology> technologies) {
        this.technologies = technologies;
    }
 
    /**
     * Creates a new instance of TaskViewBean
     */
    public ArtifactViewBean() {
    }
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User) session.getAttribute("user");    
        setArtifact(new Artifact());
        setMySprints(sprintFacadeRemote.findSprintsCreateTask(user));
        
        setMyArtifacts(artifactFacadeRemote.findMyArtifacts(user));
        setMyCollabArtifacts(artifactFacadeRemote.findMyCollabs(user));
        setTechnologies(technologyFacadeRemote.findAll());        
    }
    
    public MeasureUnit[] getMeasureUnit() {
        return MeasureUnit.values();
    }
}
