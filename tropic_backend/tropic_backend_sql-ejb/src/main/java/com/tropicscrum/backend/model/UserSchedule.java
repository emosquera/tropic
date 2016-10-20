/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import com.tropicscrum.backend.client.dto.UserScheduleDTO;
import com.tropicscrum.backend.sql.facade.BasicAttributesFacade;
import com.tropicscrum.backend.listener.BasicAttributeListener;
import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "user_schedule")
@EntityListeners(BasicAttributeListener.class)
public class UserSchedule extends UserScheduleDTO implements BasicAttributesFacade {

    private static final long serialVersionUID = 1L;
    private BasicAttributes basicAttributes;
    private SprintUser sprintUser;

    @ManyToOne
    @JoinColumn(name = "sprint_user_id")
    public SprintUser getSprintUser() {
        if (sprintUser == null) {
            return new SprintUser();
        }
        return sprintUser;
    }

    public void setSprintUser(SprintUser sprintUser) {
        this.sprintUser = sprintUser;
    }

    @Embedded
    @Override
    public BasicAttributes getBasicAttributes() {
        return basicAttributes;
    }

    public void setBasicAttributes(BasicAttributes basicAttributes) {
        this.basicAttributes = basicAttributes;
    }

    @Column(name = "final_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    @Override
    public Calendar getEnd() {
        return super.getEnd(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "start_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    @Override
    public Calendar getStart() {
        return super.getStart(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "day_of_week")
    @Override
    public int getDayOfWeek() {
        return super.getDayOfWeek(); //To change body of generated methods, choose Tools | Templates.
    }

    @Version
    @Column(name = "version")
    @Override
    public Integer getVersion() {
        return super.getVersion(); //To change body of generated methods, choose Tools | Templates.
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSchedule)) {
            return false;
        }
        UserSchedule other = (UserSchedule) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.UserSchedule[ id=" + id + " ]";
    }
    
}
