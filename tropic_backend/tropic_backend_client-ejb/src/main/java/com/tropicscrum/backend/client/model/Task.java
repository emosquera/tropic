/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.client.enums.TaskType;
import com.tropicscrum.backend.client.utils.NumberToFormattedString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Edgar Mosquera
 */
@Entity
@Table(name = "task")
@NamedQueries({
    @NamedQuery(name = "findAllTasksByUser", query = "Select t from Task t where t.author = :user")
    ,    
    @NamedQuery(name = "findAllTasksByCollaborator", query = "Select DISTINCT t from Task t where :user != t.author and (:user MEMBER OF t.artifact.milestone.sprint.project.collaborators or :user = t.artifact.milestone.sprint.project.author)")
    ,
    @NamedQuery(name = "findAllTasksBySprint", query = "Select t from Task t where t.artifact.milestone.sprint = :sprint")
    ,
    @NamedQuery(name = "findAllTasksByArtifact", query = "Select t from Task t where t.artifact = :artifact"),})
public class Task extends BasicAttributes implements Comparable<Task> {

    private String code;
    private String content;
    private Double points;
    private Double estimatedDuration;
    private TaskType type;
    private GeneralStatus status;
    private Collection<TaskProgress> taskProgresss;
    private Collection<UserEstimate> userEstimates;
    private User author;
    private Artifact artifact;

    private transient NumberToFormattedString numberToFormattedString = new NumberToFormattedString();

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
    @JoinColumn(name = "artifact_id")
    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
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

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dateExecution DESC")
    public Collection<TaskProgress> getTaskProgresss() {
        if (taskProgresss == null) {
            taskProgresss = new ArrayList<>();
        }
        return taskProgresss;
    }

    public void setTaskProgresss(Collection<TaskProgress> taskProgresss) {
        this.taskProgresss = taskProgresss;
    }

    @Transient
    @JsonIgnore
    public NumberToFormattedString getNumberToFormattedString() {
        if (numberToFormattedString == null) {
            numberToFormattedString = new NumberToFormattedString();
        }
        return numberToFormattedString;
    }

    @Transient
    @JsonIgnore
    public String getFormattedPoints() {
        return getNumberToFormattedString().DoubleToString(points);
    }

    @Transient
    @JsonIgnore
    public String getFormattedHours() {
        return getNumberToFormattedString().DoubleToString(estimatedDuration);
    }

    @Transient
    @JsonIgnore
    public Double getTotalTime() {
        Double totalTime = 0.0;
        if (getTaskProgresss() != null) {
            totalTime = getTaskProgresss().stream().map((taskProgress) -> taskProgress.getTimeInProgress()).reduce(totalTime, (accumulator, _item) -> accumulator + _item);
        }
        return totalTime;
    }

    @Transient
    @JsonIgnore
    public HashMap<SprintUser, Double> getTaskProgressByUsers() {
        HashMap<SprintUser, Double> progressByUser = new HashMap<>();
        getTaskProgresss().forEach((taskProgress) -> {
            if (progressByUser.get(taskProgress.getSprintUser()) == null) {
                progressByUser.put(taskProgress.getSprintUser(), taskProgress.getTimeInProgress());
            } else {
                progressByUser.put(taskProgress.getSprintUser(), progressByUser.get(taskProgress.getSprintUser()) + taskProgress.getTimeInProgress());
            }
        });
        return progressByUser;
    }

    @Override
    public int compareTo(Task o) {
        return getCode().compareTo(o.getCode());
    }

}
