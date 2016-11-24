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
@Named(value = "projectViewBean")
@ViewScoped
public class ProjectViewBean implements Serializable {

    private User user;
    private Project project;
    private List<History> projectHistories;
    private List<Project> myProyects;
    private List<Project> collabProyects;
    private Boolean modify = false;
    private Boolean delete = false;
    
    @EJB(lookup = ProjectFacadeRemote.JNDI_REMOTE_NAME)
    ProjectFacadeRemote projectFacadeRemote;        

    @EJB(lookup = HistoryFacadeRemote.JNDI_REMOTE_NAME)
    HistoryFacadeRemote historyFacadeRemote;
    
    public List<Project> getMyProyects() {
        if (myProyects == null) {
            myProyects = new ArrayList<>();
        }
        return myProyects;
    }

    public void setMyProyects(List<Project> myProyects) {
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

    public List<History> getProjectHistories() {
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

    public void setProjectHistories(List<History> projectHistories) {
        this.projectHistories = projectHistories;
    }

    public Boolean getModify() {
        return modify;
    }

    public void setModify(Boolean modify) {
        this.modify = modify;
    }

    public List<Project> getCollabProyects() {
        if (collabProyects == null) {
            collabProyects = new ArrayList<>();
        }
        return collabProyects;
    }

    public void setCollabProyects(List<Project> collabProyects) {
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
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User) session.getAttribute("user");                       
        setMyProyects(projectFacadeRemote.findAllMine(user));    
        setCollabProyects(projectFacadeRemote.findAllMyCollabs(user));
    }
    
}
