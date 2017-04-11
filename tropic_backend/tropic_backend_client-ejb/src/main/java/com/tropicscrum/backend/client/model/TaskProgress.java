/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Edgar Mosquera
 */
@Entity
@Table(name = "task_progress")
@NamedQueries({
    @NamedQuery(name = "findAllProgressByTask", query = "Select t from TaskProgress t where t.task = :task ORDER BY t.dateExecution DESC"),
    @NamedQuery(name = "findAllProgressInProgressBySprintUser", query = "Select t from TaskProgress t where t.sprintUser = :sprintUser AND t.startEstatus = com.tropicscrum.backend.client.enums.GeneralStatus.IN_PROGRESS AND t.finalStatus IS NULL AND t.task.status = com.tropicscrum.backend.client.enums.GeneralStatus.IN_PROGRESS"),
})
public class TaskProgress extends BasicAttributes {
    private Calendar dateExecution;
    private Calendar finalDate;
    private Double timeInProgress;
    private GeneralStatus startEstatus;
    private GeneralStatus finalStatus;
    private SprintUser sprintUser;
    private Task task;    

    @Column(name = "date_execution")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Calendar getDateExecution() {
        return dateExecution;
    }

    public void setDateExecution(Calendar dateExecution) {
        this.dateExecution = dateExecution;
    }

    @Column(name = "final_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Calendar getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Calendar finalDate) {
        this.finalDate = finalDate;
    }

    @Column(name = "time_progress")    
    public Double getTimeInProgress() {
        return timeInProgress;
    }

    public void setTimeInProgress(Double timeInProgress) {
        this.timeInProgress = timeInProgress;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "start_status")
    public GeneralStatus getStartEstatus() {
        return startEstatus;
    }

    public void setStartEstatus(GeneralStatus startEstatus) {
        this.startEstatus = startEstatus;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "final_status")
    public GeneralStatus getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(GeneralStatus finalStatus) {
        this.finalStatus = finalStatus;
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
        if (!(object instanceof TaskProgress)) {
            return false;
        }
        TaskProgress other = (TaskProgress) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.TaskProgress[ id=" + getId() + " ]";
    }
    
}
