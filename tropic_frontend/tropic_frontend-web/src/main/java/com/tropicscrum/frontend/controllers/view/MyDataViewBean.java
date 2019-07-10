/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.facade.ProjectFacadeRemote;
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
@Named(value = "myDataViewBean")
@ViewScoped
public class MyDataViewBean implements Serializable{
    private User user;
    private Collection<Project> myProjects;

    ProjectFacadeRemote projectFacadeRemote = new ServiceLocatorDelegate<ProjectFacadeRemote>().getService(ProjectFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    ExternalContext extContext;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Project> getMyProjects() {
        if (myProjects == null) {
            myProjects = new ArrayList<>();
        }
        return myProjects;
    }

    public void setMyProjects(Collection<Project> myProjects) {
        this.myProjects = myProjects;
    }

    /**
     * Creates a new instance of MyDataViewBean
     */
    public MyDataViewBean() {
    }
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) extContext.getSession(false);
        user = (User) session.getAttribute("user");     
        
        Collection<Project> projects = projectFacadeRemote.findAllMine(user);
        Collection<Project> collabsProjects = projectFacadeRemote.findAllMyCollabs(user);        
        
        getMyProjects().addAll(projects);
        
        collabsProjects.stream().filter((p) -> (!p.getAuthor().equals(user))).forEachOrdered((p) -> {
            getMyProjects().add(p);
        });
    }
    
}
