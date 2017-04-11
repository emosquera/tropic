/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.tropicscrum.backend.client.enums.Color;
import com.tropicscrum.backend.client.enums.ScrumRole;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Edgar Mosquera
 */
@Entity
@Table(name = "sprint_user")
@NamedQueries({
    @NamedQuery(name = "findAllSprintUserBySprint", query = "Select s from SprintUser s where s.sprint = :sprint"),    
    @NamedQuery(name = "findAllSprintUserBySprintAndUserAndRole", query = "Select s from SprintUser s where s.sprint = :sprint and s.user = :user and s.role = :role"),    
})
public class SprintUser extends BasicAttributes {
    private Color color;
    private ScrumRole role;
    private Boolean createTask;
    private Collection<TaskProgress> taskProgresss;
    private Collection<UserEstimate> userEstimates;
    private Sprint sprint;
    private User user;    
    private Collection<Schedule> schedules;

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

    @ManyToMany
    public Collection<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Collection<Schedule> schedules) {
        this.schedules = schedules;
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
    public Collection<UserEstimate> getUserEstimates() {
        if (userEstimates == null) {
            userEstimates = new ArrayList<>();
        }
        return userEstimates;
    }

    public void setUserEstimates(Collection<UserEstimate> userEstimates) {
        this.userEstimates = userEstimates;
    }

    @OneToMany(mappedBy = "sprintUser")
    public Collection<TaskProgress> getTaskProgresss() {
        if (taskProgresss == null) {
            taskProgresss = new ArrayList<>();
        }
        return taskProgresss;
    }

    public void setTaskProgresss(Collection<TaskProgress> taskProgresss) {
        this.taskProgresss = taskProgresss;
    }
}
