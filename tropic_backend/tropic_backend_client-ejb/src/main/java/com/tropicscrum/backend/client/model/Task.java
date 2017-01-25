/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.client.enums.TaskType;
import java.util.ArrayList;
import java.util.Collection;
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
@Table(name = "task")
public class Task extends BasicAttributes {
    private String content;
    private Double points;
    private Double estimatedDuration;
    private TaskType type;
    private GeneralStatus status;
    private Collection<TaskProgress> taskProgresss;
    private Collection<UserEstimate> userEstimates;
    private Milestone milestone;
    private User author;
    private Sprint sprint;

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "points")
    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    @Column(name = "estimated_duration")
    public Double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Double estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    public Milestone getMilestone() {
        if (milestone == null) {
            milestone = new Milestone();
        }
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getAuthor() {
        if (author == null) {
            author = new User();
        }
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.Task[ id=" + getId() + " ]";
    }

    @OneToMany(mappedBy = "task")
    public Collection<UserEstimate> getUserEstimates() {
        if (userEstimates == null) {
            userEstimates = new ArrayList<>();
        }
        return userEstimates;
    }

    public void setUserEstimates(Collection<UserEstimate> userEstimates) {
        this.userEstimates = userEstimates;
    }

    @OneToMany(mappedBy = "task")
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
