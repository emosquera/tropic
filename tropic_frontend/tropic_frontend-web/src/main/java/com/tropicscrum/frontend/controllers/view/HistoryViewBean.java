/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.facade.HistoryFacadeRemote;
import com.tropicscrum.backend.client.facade.ProjectFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author syslife02
 */
@Named(value = "historyViewBean")
@ViewScoped
public class HistoryViewBean implements Serializable {

    private User user;
    
    private History history;
    private List<Project> myProjects;
    
    private List<History> histories;
    private List<History> collabHistories;
    private Boolean modify = false;
    private Boolean delete = false;
    
    @EJB(lookup = ProjectFacadeRemote.JNDI_REMOTE_NAME)
    ProjectFacadeRemote projectFacadeRemote;
    
    @EJB(lookup = HistoryFacadeRemote.JNDI_REMOTE_NAME)
    HistoryFacadeRemote historyFacadeRemote;

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

    public List<Project> getMyProjects() {
        if (myProjects == null) {
            myProjects = new ArrayList<>();
        }
        return myProjects;
    }

    public void setMyProjects(List<Project> myProjects) {
        this.myProjects = myProjects;
    }

    public List<History> getHistories() {
        if (histories == null) {
            histories = new ArrayList<>();
        }
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public List<History> getCollabHistories() {
        if (collabHistories == null) {
            return new ArrayList<>();
        }
        return collabHistories;
    }

    public void setCollabHistories(List<History> collabHistories) {
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
    
    /**
     * Creates a new instance of HistoryViewBean
     */
    public HistoryViewBean() {
    }
    
    @PostConstruct
    public void init() {
        final History redirectHistory = (History) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("history");
        if (redirectHistory != null) {
            setHistory(redirectHistory);
        } else {
            setHistory(new History());
        }        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().remove("history");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User) session.getAttribute("user");     
        List<Project> projects = projectFacadeRemote.findAllMine(user);
        List<Project> collabsProjects = projectFacadeRemote.findAllMyCollabs(user);        
        
        getMyProjects().addAll(projects);
        
        for (Project p : collabsProjects) {
            if (!p.getAuthor().equals(user)) {
                getMyProjects().add(p);
            }
        }
        
        setHistories(historyFacadeRemote.findAllMine(user));
        setCollabHistories(historyFacadeRemote.findAllMyCollabs(user));
    }
}
