/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.facade.SprintFacadeRemote;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "listSprintsViewBean")
@ViewScoped
public class ListSprintsViewBean implements Serializable {

    private User user;
    private String page;
    private String title;
    private Collection<Sprint> sprints;

    SprintFacadeRemote sprintFacadeRemote = new ServiceLocatorDelegate<SprintFacadeRemote>().getService(SprintFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    ExternalContext extContext;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Collection<Sprint> sprints) {
        this.sprints = sprints;
    }
    
    /**
     * Creates a new instance of EstimateViewBean
     */
    public ListSprintsViewBean() {
    }
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) extContext.getSession(false);
        user = (User) session.getAttribute("user");    
        
        setSprints(sprintFacadeRemote.findSprintsTeam(user));
        
        HttpServletRequest request = (HttpServletRequest) extContext.getRequest();
        setPage(request.getParameter("page"));
        
        if (getPage().equals("poker")) {
            setTitle("Elija el Sprint a estimar");
        } else if (getPage().equals("board")) {
            setTitle("Elija la Pizarra");
        }
    }
    
}
