/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.enums.TaskType;
import com.tropicscrum.backend.client.facade.SprintFacadeRemote;
import com.tropicscrum.backend.client.facade.TaskFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "taskViewBean")
@ViewScoped
public class TaskViewBean implements Serializable {

    private User user;
    private Task task;
    private Boolean modify = false;
    private Boolean delete = false;
    private Boolean lockSprint = false;
    
    private Collection<Sprint> mySprints;
    
    private Sprint sprintSelected;
    private History historySelected;
    private Milestone milestoneSelected;
    
    private Collection<Task> myTasks;
    private Collection<Task> myCollabTasks;

    SprintFacadeRemote sprintFacadeRemote = new ServiceLocatorDelegate<SprintFacadeRemote>().getService(SprintFacadeRemote.JNDI_REMOTE_NAME);

    TaskFacadeRemote taskFacadeRemote = new ServiceLocatorDelegate<TaskFacadeRemote>().getService(TaskFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    ExternalContext extContext;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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

    public History getHistorySelected() {
        return historySelected;
    }

    public void setHistorySelected(History historySelected) {
        this.historySelected = historySelected;
    }

    public Collection<Task> getMyTasks() {
        if (myTasks == null) {
            myTasks = new ArrayList<>();
        }
        return myTasks;
    }

    public void setMyTasks(Collection<Task> myTasks) {
        this.myTasks = myTasks;
    }

    public Collection<Task> getMyCollabTasks() {
        if (myCollabTasks == null) {
            myCollabTasks = new ArrayList<>();
        }
        return myCollabTasks;
    }

    public void setMyCollabTasks(Collection<Task> myCollabTasks) {
        this.myCollabTasks = myCollabTasks;
    }

    public Sprint getSprintSelected() {
        return sprintSelected;
    }

    public void setSprintSelected(Sprint sprintSelected) {
        this.sprintSelected = sprintSelected;
    }

    public Milestone getMilestoneSelected() {
        return milestoneSelected;
    }

    public void setMilestoneSelected(Milestone milestoneSelected) {
        this.milestoneSelected = milestoneSelected;
    }
 
    /**
     * Creates a new instance of TaskViewBean
     */
    public TaskViewBean() {
    }
    
    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) extContext.getSession(false);
        user = (User) session.getAttribute("user");    
        setTask(new Task());
        setMySprints(sprintFacadeRemote.findSprintsCreateTask(user));
        
        setMyTasks(taskFacadeRemote.findMyTasks(user));
        setMyCollabTasks(taskFacadeRemote.findMyCollabs(user));
    }
    
    public TaskType[] getTaskType() {
        return TaskType.values();
    }
}
