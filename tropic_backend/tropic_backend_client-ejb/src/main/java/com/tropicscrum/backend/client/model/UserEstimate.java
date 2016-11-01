/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "user_estimate")
public class UserEstimate extends BasicAttributes {
    private int points;
    private SprintUser sprintUser;
    private Task task;

    @Column(name = "points")
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @ManyToOne
    @JoinColumn(name = "sprint_user_id")
    public SprintUser getSprintUser() {
        if (sprintUser == null) {
            sprintUser = new SprintUser();
        }
        return sprintUser;
    }

    public void setSprintUser(SprintUser sprintUser) {
        this.sprintUser = sprintUser;
    }

    @ManyToOne
    @JoinColumn(name = "task_id")
    public Task getTask() {
        if (task == null) {
            task = new Task();
        }
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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
        if (!(object instanceof UserEstimate)) {
            return false;
        }
        UserEstimate other = (UserEstimate) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.UserEstimate[ id=" + getId() + " ]";
    }
    
}
