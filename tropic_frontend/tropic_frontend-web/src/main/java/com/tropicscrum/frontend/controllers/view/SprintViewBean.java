/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.view;

import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.client.enums.Color;
import com.tropicscrum.backend.client.enums.ScrumRole;
import com.tropicscrum.backend.client.facade.ScheduleFacadeRemote;
import com.tropicscrum.backend.client.facade.SprintFacadeRemote;
import com.tropicscrum.backend.client.model.Schedule;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.frontend.controllers.application.ScheduleAppBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author syslife02
 */
@Named(value = "sprintViewBean")
@ViewScoped
public class SprintViewBean implements Serializable {

    private User user;    
    private Sprint sprint;
    private Boolean modify = false;
    private Boolean delete = false;
    private User userSelected;
    private SprintUser sprintUserSelected;
    private Boolean createTask = false;
    private Color colorSelected;
    private ScrumRole selectedScrumRole;
    private Boolean editingPerson;
    
    private String styleColor;    
    
    private List<SelectItem> schedules;
    private Collection<Schedule> userSchedules;
    
    private Collection<Sprint> sprints;
    
    @Inject
    ScheduleAppBean scheduleAppBean;
    
    @EJB(lookup = ScheduleFacadeRemote.JNDI_REMOTE_NAME) 
    ScheduleFacadeRemote scheduleFacadeRemote;
    
    @EJB(lookup = SprintFacadeRemote.JNDI_REMOTE_NAME)
    SprintFacadeRemote sprintFacadeRemote;

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

    public User getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(User userSelected) {
        this.userSelected = userSelected;
    }

    public SprintUser getSprintUserSelected() {
        if (sprintUserSelected == null) {
            sprintUserSelected = new SprintUser();
        }
        return sprintUserSelected;
    }

    public void setSprintUserSelected(SprintUser sprintUserSelected) {
        this.sprintUserSelected = sprintUserSelected;
    }

    public Boolean getCreateTask() {
        return createTask;
    }

    public void setCreateTask(Boolean createTask) {
        this.createTask = createTask;
    }

    public Color getColorSelected() {
        return colorSelected;
    }

    public void setColorSelected(Color colorSelected) {
        this.colorSelected = colorSelected;
    }

    public ScrumRole getSelectedScrumRole() {
        return selectedScrumRole;
    }

    public void setSelectedScrumRole(ScrumRole selectedScrumRole) {
        this.selectedScrumRole = selectedScrumRole;
    }

    public String getStyleColor() {
        return styleColor;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }   

    public List<SelectItem> getSchedules() {
        if (schedules == null) {
            schedules = new ArrayList<>();
        }
        return schedules;
    }

    public void setSchedules(List<SelectItem> schedules) {
        this.schedules = schedules;
    }

    public Collection<Schedule> getUserSchedules() {
        if (userSchedules == null) {
            userSchedules = new ArrayList<>();
        }
        return userSchedules;
    }

    public void setUserSchedules(Collection<Schedule> userSchedules) {
        this.userSchedules = userSchedules;
    }

    public Boolean getEditingPerson() {
        return editingPerson;
    }

    public void setEditingPerson(Boolean editingPerson) {
        this.editingPerson = editingPerson;
    }

    public Collection<Sprint> getSprints() {
        if (sprints == null) {
            sprints = new ArrayList<>();
        }
        return sprints;
    }

    public void setSprints(Collection<Sprint> sprints) {
        this.sprints = sprints;
    }
    
    /**
     * Creates a new instance of SprintViewBean
     */
    public SprintViewBean()  {
    }
    
    @PostConstruct
    public void init() {
        setSprint(new Sprint());        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (User) session.getAttribute("user");          
        colorSelected = Color.values()[0];
        styleColor = "color" + colorSelected.toString();                                        
        
        setUserSchedules(scheduleFacadeRemote.getDefaultByWeek());
        editingPerson = false;
        
        setSprints(sprintFacadeRemote.findMySprints(user));
    }
    
    public Color[] getColors() {
        styleColor = "color" + Color.values()[0].toString();
        return Color.values();
    }
    
    public ScrumRole[] getScrumRoles() {        
        return ScrumRole.values();
    }    
    
}
