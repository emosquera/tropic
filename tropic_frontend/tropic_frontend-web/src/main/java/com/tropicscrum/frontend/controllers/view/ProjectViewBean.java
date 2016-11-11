/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.facade.ProjectFacadeRemote;
import com.tropicscrum.backend.client.model.Project;
import com.tropicscrum.backend.client.model.User;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.util.Base64;

/**
 *
 * @author syslife02
 */
@Named(value = "projectViewBean")
@ViewScoped
public class ProjectViewBean implements Serializable {

    private User user;
    private Project project;
    private List<Project> myProyects;
    private User colaborator;
    private Boolean modify = false;
    
    @EJB(lookup = ProjectFacadeRemote.JNDI_REMOTE_NAME)
    ProjectFacadeRemote projectFacadeRemote;

    public List<Project> getMyProyects() {
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

    public User getColaborator() {
        return colaborator;
    }

    public void setColaborator(User colaborator) {
        this.colaborator = colaborator;
    }

    public Boolean getModify() {
        return modify;
    }

    public void setModify(Boolean modify) {
        this.modify = modify;
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
    }
    
}
