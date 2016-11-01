/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "user_schedule")
public class UserSchedule extends BasicAttributes {
    private int dayOfWeek;
    private Calendar start;
    private Calendar end;
    private SprintUser sprintUser;

    @Column(name = "day_of_week")
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Column(name = "start_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    @Column(name = "final_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSchedule)) {
            return false;
        }
        UserSchedule other = (UserSchedule) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.UserSchedule[ id=" + getId() + " ]";
    }
    
}
