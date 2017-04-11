/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.facade.HistoryFacadeRemote;
import com.tropicscrum.backend.client.facade.MilestoneFacadeRemote;
import com.tropicscrum.backend.client.facade.SprintUserFacadeRemote;
import com.tropicscrum.backend.client.facade.TaskFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.client.model.UserEstimate;
import com.tropicscrum.frontend.push.model.PokerMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named(value = "pokerViewBean")
@ViewScoped
public class PokerViewBean implements Serializable {

    private User user;
    private Sprint sprint;
    private Collection<History> sprintHistories;
    private Task taskSelected;
    private Boolean isScrumMaster = false;    
    private PokerMessage pokerMessage;
    private UserEstimate userEstimate;
    private String styleAnimateTask = "";
    private String styleAnimateEstimate = "";
    private Boolean isVoted = Boolean.FALSE;
    
    @EJB(lookup = HistoryFacadeRemote.JNDI_REMOTE_NAME)
    HistoryFacadeRemote historyFacadeRemote;

    @EJB(lookup = MilestoneFacadeRemote.JNDI_REMOTE_NAME)
    MilestoneFacadeRemote milestoneFacadeRemote;

    @EJB(lookup = TaskFacadeRemote.JNDI_REMOTE_NAME)
    TaskFacadeRemote taskFacadeRemote;

    @EJB(lookup = SprintUserFacadeRemote.JNDI_REMOTE_NAME)
    SprintUserFacadeRemote sprintUserFacadeRemote;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Collection<History> getSprintHistories() {
        if (sprintHistories == null) {
            sprintHistories = new ArrayList<>();
        }
        return sprintHistories;
    }

    public void setSprintHistories(Collection<History> sprintHistories) {
        this.sprintHistories = sprintHistories;
    }

    public Task getTaskSelected() {
        return taskSelected;
    }

    public void setTaskSelected(Task taskSelected) {
        this.taskSelected = taskSelected;
    }

    public Boolean getIsScrumMaster() {
        return isScrumMaster;
    }

    public void setIsScrumMaster(Boolean isScrumMaster) {
        this.isScrumMaster = isScrumMaster;
    }

    public PokerMessage getPokerMessage() {
        return pokerMessage;
    }

    public void setPokerMessage(PokerMessage pokerMessage) {
        this.pokerMessage = pokerMessage;
    }

    public UserEstimate getUserEstimate() {
        return userEstimate;
    }

    public void setUserEstimate(UserEstimate userEstimate) {
        this.userEstimate = userEstimate;
    }

    public String getStyleAnimateTask() {
        return styleAnimateTask;
    }

    public void setStyleAnimateTask(String styleAnimateTask) {
        this.styleAnimateTask = styleAnimateTask;
    }

    public String getStyleAnimateEstimate() {
        return styleAnimateEstimate;
    }

    public void setStyleAnimateEstimate(String styleAnimateEstimate) {
        this.styleAnimateEstimate = styleAnimateEstimate;
    }

    public Boolean getIsVoted() {
        return isVoted;
    }

    public void setIsVoted(Boolean isVoted) {
        this.isVoted = isVoted;
    }

    /**
     * Creates a new instance of PokerViewBean
     */
    public PokerViewBean() {
    }

    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User) session.getAttribute("user");

        final Sprint redirectSprint = (Sprint) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("sprint");
        if (redirectSprint != null) {
            setSprint(redirectSprint);
            setSprintHistories(historyFacadeRemote.findSprintHistories(sprint));
            for (History history : getSprintHistories()) {
                history.setMilestones(milestoneFacadeRemote.findSprintHistoryMilestones(history, sprint));
                for (Milestone milestone : history.getMilestones()) {
                    milestone.setTasks(taskFacadeRemote.findSprintMilestoneTasks(milestone, sprint));
                }
            }

            getSprint().setSprintUsers(sprintUserFacadeRemote.findSprintTeam(sprint));
            setIsScrumMaster(sprintUserFacadeRemote.isSprintUserScrumMaster(sprint, user));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().remove("sprint");

            pokerMessage = new PokerMessage();
            pokerMessage.setTaskCode(null);
            pokerMessage.setShowEstimate(Boolean.FALSE);
            
            userEstimate = new UserEstimate();
                        
            for (SprintUser su : getSprint().getSprintUsers()) {               
                if (su.getUser().equals(user)) {
                    userEstimate.setSprintUser(su);       
                    break;
                }
            }         

        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/tropic/home/sprints.xhtml?page=poker");
            } catch (IOException ex) {
                Logger.getLogger(PokerViewBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
