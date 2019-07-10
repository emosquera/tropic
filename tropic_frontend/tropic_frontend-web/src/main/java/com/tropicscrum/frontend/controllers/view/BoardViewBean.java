/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.client.facade.SprintUserFacadeRemote;
import com.tropicscrum.backend.client.facade.TaskFacadeRemote;
import com.tropicscrum.backend.client.facade.TaskProgressFacadeRemote;
import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.TaskProgress;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import com.tropicscrum.frontend.utils.FilterList;
import com.tropicscrum.frontend.utils.facade.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
@Named(value = "boardViewBean")
@ViewScoped
public class BoardViewBean implements Serializable {

    private User user;
    private Sprint sprint;
    private Collection<Task> sprintTasks;
    private Collection<Task> toDoTasks;
    private Collection<Task> doingTasks;
    private Collection<Task> doneTasks;
    private Collection<TaskProgress> taskProgresss;
    private SprintUser sprintUser;
    private Collection<TaskProgress> finishedProgresss;
    private Boolean isScrumMaster = false;    
    private HashMap<SprintUser, Double> progressByUsers;
    private List<SprintUser> progressByUsersKeys;

    TaskFacadeRemote taskFacadeRemote = new ServiceLocatorDelegate<TaskFacadeRemote>().getService(TaskFacadeRemote.JNDI_REMOTE_NAME);

    SprintUserFacadeRemote sprintUserFacadeRemote = new ServiceLocatorDelegate<SprintUserFacadeRemote>().getService(SprintUserFacadeRemote.JNDI_REMOTE_NAME);

    TaskProgressFacadeRemote taskProgressFacadeRemote = new ServiceLocatorDelegate<TaskProgressFacadeRemote>().getService(TaskProgressFacadeRemote.JNDI_REMOTE_NAME);
    
    @Inject
    ExternalContext extContext;
    
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

    public Collection<Task> getSprintTasks() {
        if (sprintTasks == null) {
            sprintTasks = new ArrayList<>();
        }
        return sprintTasks;
    }

    public void setSprintTasks(Collection<Task> sprintTasks) {
        this.sprintTasks = sprintTasks;
    }

    public Collection<Task> getToDoTasks() {
        if (toDoTasks == null) {
            toDoTasks = new ArrayList<>();
        }
        return toDoTasks;
    }

    public void setToDoTasks(Collection<Task> toDoTasks) {
        this.toDoTasks = toDoTasks;
    }

    public Collection<Task> getDoingTasks() {
        if (doingTasks == null) {
            doingTasks = new ArrayList<>();
        }
        return doingTasks;
    }

    public void setDoingTasks(Collection<Task> doingTasks) {
        this.doingTasks = doingTasks;
    }

    public Collection<Task> getDoneTasks() {
        if (doneTasks == null) {
            doneTasks = new ArrayList<>();
        }
        return doneTasks;
    }

    public void setDoneTasks(Collection<Task> doneTasks) {
        this.doneTasks = doneTasks;
    }

    public Collection<TaskProgress> getTaskProgresss() {
        if (taskProgresss == null) {
            taskProgresss = new ArrayList<>();
        }
        return taskProgresss;
    }

    public void setTaskProgresss(Collection<TaskProgress> taskProgresss) {
        this.taskProgresss = taskProgresss;
    }

    public SprintUser getSprintUser() {
        return sprintUser;
    }

    public void setSprintUser(SprintUser sprintUser) {
        this.sprintUser = sprintUser;
    }

    public Collection<TaskProgress> getFinishedProgresss() {
        if (finishedProgresss == null) {
            finishedProgresss = new ArrayList<>();
        }
        return finishedProgresss;
    }

    public void setFinishedProgresss(Collection<TaskProgress> finishedProgresss) {
        this.finishedProgresss = finishedProgresss;
    }

    public Boolean getIsScrumMaster() {
        return isScrumMaster;
    }

    public void setIsScrumMaster(Boolean isScrumMaster) {
        this.isScrumMaster = isScrumMaster;
    }

    public HashMap<SprintUser, Double> getProgressByUsers() {
        if (progressByUsers == null) {
            progressByUsers = new HashMap<>();
        }
        return progressByUsers;
    }

    public void setProgressByUsers(HashMap<SprintUser, Double> progressByUsers) {
        this.progressByUsers = progressByUsers;
    }

    public List<SprintUser> getProgressByUsersKeys() {     
        
        return progressByUsersKeys;
    }

    public void setProgressByUsersKeys(List<SprintUser> progressByUsersKeys) {
        this.progressByUsersKeys = progressByUsersKeys;
    }

    public List<SprintUser> getProgressByUser(Task task) {           
        for (Task t : getDoneTasks()) {
            if (t.equals(task)) {      
                setProgressByUsers(t.getTaskProgressByUsers());
                break;
            }
        }
        setProgressByUsersKeys(new ArrayList<>(getProgressByUsers().keySet()));
        return getProgressByUsersKeys();
    }

    public Double getTaskTotalTime(Task task) {
        Double taskTotalTime = 0.0;
        for (Task t : getDoneTasks()) {
            if (t.equals(task)) {                
                taskTotalTime = t.getTotalTime();
                break;
            }
        }
        return taskTotalTime;
    }
    
    public String millisecondsToRedeableTime(Double timeInMillies) {
        Double hours = timeInMillies / (60 * 60 * 1000) % 24;
        Double minutes = timeInMillies / (60 * 1000) % 60;
        Double seconds = timeInMillies / 1000 % 60;
        
        String redeableTime = "";
        if (hours >= 1) {
            redeableTime = String.valueOf(hours.intValue()) + "h";
        }
                
        if (minutes >= 1) {
            if (seconds > 30) {
                minutes += 1;
            }
            if (redeableTime.equals("")) {
                redeableTime = String.valueOf(minutes.intValue()) + "m";
            } else {
                redeableTime = redeableTime + ", " + String.valueOf(minutes.intValue()) + "m";
            }
        }
        
        
        if (redeableTime.equals("")) {
            redeableTime = String.valueOf(seconds.intValue()) + "s";
        }
        
        return redeableTime;
    }
    
    /**
     * Creates a new instance of BoardViewBean
     */
    public BoardViewBean() {
    }

    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) extContext.getSession(false);
        user = (User) session.getAttribute("user");

        final Sprint redirectSprint = (Sprint) extContext.getFlash().get("sprint");
        if (redirectSprint != null) {
            setSprint(redirectSprint);
            getSprint().setSprintUsers(sprintUserFacadeRemote.findSprintTeam(sprint));
            for (SprintUser su : getSprint().getSprintUsers()) {
                if (su.getUser().equals(user)) {
                    setSprintUser(su);
                    break;
                }
            }
            
            setSprintTasks(taskFacadeRemote.findSprintTasks(sprint));
            setIsScrumMaster(sprintUserFacadeRemote.isSprintUserScrumMaster(sprint, user));
            
            //Podamos las Listas
            Predicate<Task> toDoFilter = (Task task) -> task.getStatus().equals(GeneralStatus.PENDING);            
            setToDoTasks(FilterList.filter(sprintTasks, toDoFilter));
            
            Predicate<Task> doingFilter = (Task task) -> task.getStatus().equals(GeneralStatus.IN_PROGRESS);  
            setDoingTasks(FilterList.filter(sprintTasks, doingFilter));
            
            Predicate<Task> doneFilter = (Task task) -> task.getStatus().equals(GeneralStatus.FINISHED);  
            setDoneTasks(FilterList.filter(sprintTasks, doneFilter));
            
            getDoingTasks().stream().map((task) -> {
                task.setTaskProgresss(taskProgressFacadeRemote.findTaskActivity(task));
                return task;
            }).filter((task) -> (task.getTaskProgresss().iterator().hasNext())).forEachOrdered((task) -> {
                getTaskProgresss().add(task.getTaskProgresss().iterator().next());
            });
            
            getDoneTasks().stream().map((task) -> {
                task.setTaskProgresss(taskProgressFacadeRemote.findTaskActivity(task));
                return task;
            }).filter((task) -> (task.getTaskProgresss().iterator().hasNext())).forEachOrdered((task) -> {
                getFinishedProgresss().add(task.getTaskProgresss().iterator().next());
            });
            
            extContext.getFlash().remove("sprint");
        } else {
//            try {
//                extContext.redirect("/tropic/home/sprints.xhtml?page=board");
//            } catch (IOException ex) {
//                Logger.getLogger(PokerViewBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

}
