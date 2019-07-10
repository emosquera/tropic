/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.frontend.controllers.view.ListSprintsViewBean;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "listSprintsRequestBean")
@RequestScoped
public class ListSprintsRequestBean implements Serializable {

    private Sprint sprintRedirect;
    
    @Inject
    ListSprintsViewBean listSprintsViewBean;
    
    @Inject
    ExternalContext extContext;

    public Sprint getSprintRedirect() {
        return sprintRedirect;
    }

    public void setSprintRedirect(Sprint sprintRedirect) {
        this.sprintRedirect = sprintRedirect;
    }
    
    /**
     * Creates a new instance of EstimateRequestBean
     */
    public ListSprintsRequestBean() {
    }
    
    public String initSprint() {
        if (sprintRedirect != null) {           
            extContext.getFlash().put("sprint", sprintRedirect);
        }
        return "/home/" + listSprintsViewBean.getPage() + "?faces-redirect=true";
    }    
}
