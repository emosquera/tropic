/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import com.tropicscrum.backend.client.dto.TaskProgressDTO;
import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.sql.facade.BasicAttributesFacade;
import com.tropicscrum.backend.listener.BasicAttributeListener;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "task_progress")
@EntityListeners(BasicAttributeListener.class)
public class TaskProgress extends TaskProgressDTO implements BasicAttributesFacade {

    private static final long serialVersionUID = 1L;
    private BasicAttributes basicAttributes;
    private SprintUser sprintUser;
    private Task task;

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

    @ManyToOne
    @JoinColumn(name = "task_id")
    public Task getTask() {
        if (task == null) {
            return new Task();
        }
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Embedded
    @Override
    public BasicAttributes getBasicAttributes() {
        return basicAttributes;
    }

    public void setBasicAttributes(BasicAttributes basicAttributes) {
        this.basicAttributes = basicAttributes;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "final_status")
    @Override
    public GeneralStatus getFinalStatus() {
        return super.getFinalStatus(); //To change body of generated methods, choose Tools | Templates.
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "start_status")
    @Override
    public GeneralStatus getStartEstatus() {
        return super.getStartEstatus(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "date_execution")
    @Override
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Calendar getDateExecution() {
        return super.getDateExecution(); //To change body of generated methods, choose Tools | Templates.
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
        if (!(object instanceof TaskProgress)) {
            return false;
        }
        TaskProgress other = (TaskProgress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.TaskProgress[ id=" + id + " ]";
    }
    
}
