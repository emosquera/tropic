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
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "projectViewBean")
@ViewScoped
public class ProjectViewBean implements Serializable {

    private User user;
    private Project project;
    private Collection<History> projectHistories;
    private Collection<Project> myProyects;
    private Collection<Project> collabProyects;
    private Boolean modify = false;
    private Boolean delete = false;

    ProjectFacadeRemote projectFacadeRemote = new ServiceLocatorDelegate<ProjectFacadeRemote>().getService(ProjectFacadeRemote.JNDI_REMOTE_NAME);        

    HistoryFacadeRemote historyFacadeRemote = new ServiceLocatorDelegate<HistoryFacadeRemote>().getService(HistoryFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    ExternalContext extContext;
    
    public Collection<Project> getMyProyects() {
        if (myProyects == null) {
            myProyects = new ArrayList<>();
        }
        return myProyects;
    }

    public void setMyProyects(Collection<Project> myProyects) {
        this.myProyects = myProyects;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        if (project == null) {
            project = new Project();
        }
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Collection<History> getProjectHistories() {
        if (project == null) {
            projectHistories = new ArrayList<>();
        } else {
            projectHistories = historyFacadeRemote.findProjectHistories(project);
            if (projectHistories == null) {
                projectHistories = new ArrayList<>();
            }
        }
        return projectHistories;
    }

    public void setProjectHistories(Collection<History> projectHistories) {
        this.projectHistories = projectHistories;
    }

    public Boolean getModify() {
        return modify;
    }

    public void setModify(Boolean modify) {
        this.modify = modify;
    }

    public Collection<Project> getCollabProyects() {
        if (collabProyects == null) {
            collabProyects = new ArrayList<>();
        }
        return collabProyects;
    }

    public void setCollabProyects(Collection<Project> collabProyects) {
        this.collabProyects = collabProyects;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
    
    /**
     * Creates a new instance of ProjectViewBean
     */
    public ProjectViewBean() {
    }
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) extContext.getSession(false);
        user = (User) session.getAttribute("user");                       
        setMyProyects(projectFacadeRemote.findAllMine(user));    
        setCollabProyects(projectFacadeRemote.findAllMyCollabs(user));
    }
    
}
