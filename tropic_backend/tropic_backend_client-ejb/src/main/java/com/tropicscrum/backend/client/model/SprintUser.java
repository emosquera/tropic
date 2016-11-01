/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.tropicscrum.backend.client.enums.Color;
import com.tropicscrum.backend.client.enums.ScrumRole;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "sprint_user")
public class SprintUser extends BasicAttributes {
    private Color color;
    private ScrumRole role;
    private Boolean createTask;
    private List<TaskProgress> taskProgresss;
    private List<UserSchedule> userSchedules;
    private List<UserEstimate> userEstimates;
    private Sprint sprint;
    private User user;    

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "team_role")
    public ScrumRole getRole() {
        return role;
    }

    public void setRole(ScrumRole role) {
        this.role = role;
    }

    @Column(name = "create_task")
    public Boolean getCreateTask() {
        return createTask;
    }

    public void setCreateTask(Boolean createTask) {
        this.createTask = createTask;
    }

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    public Sprint getSprint() {
        if (sprint == null) {
            sprint = new Sprint();
        }
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SprintUser)) {
            return false;
        }
        SprintUser other = (SprintUser) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.SprintUser[ id=" + getId() + " ]";
    }

    @OneToMany(mappedBy = "sprintUser")
    public List<UserEstimate> getUserEstimates() {
        if (userEstimates == null) {
            userEstimates = new ArrayList<>();
        }
        return userEstimates;
    }

    public void setUserEstimates(List<UserEstimate> userEstimates) {
        this.userEstimates = userEstimates;
    }

    @OneToMany(mappedBy = "sprintUser")
    public List<UserSchedule> getUserSchedules() {
        if (userSchedules == null) {
            userSchedules = new ArrayList<>();
        }
        return userSchedules;
    }

    public void setUserSchedules(List<UserSchedule> userSchedules) {
        this.userSchedules = userSchedules;
    }

    @OneToMany(mappedBy = "sprintUser")
    public List<TaskProgress> getTaskProgresss() {
        if (taskProgresss == null) {
            taskProgresss = new ArrayList<>();
        }
        return taskProgresss;
    }

    public void setTaskProgresss(List<TaskProgress> taskProgresss) {
        this.taskProgresss = taskProgresss;
    }
    
}
