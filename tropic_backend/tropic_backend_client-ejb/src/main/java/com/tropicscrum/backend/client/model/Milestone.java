/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Edgar Mosquera
 */
@Entity
@Table(name = "milestone")
@NamedQueries({
    @NamedQuery(name = "findAllMilestonesByUser", query = "Select m from Milestone m where m.author = :user"),
    @NamedQuery(name = "findAllMielstoneByCollaborator", query = "Select DISTINCT m from Milestone m where :user != m.author and (:user MEMBER OF m.history.project.collaborators or :user = m.history.project.author)"),
    @NamedQuery(name = "findByHistory", query = "Select m from Milestone m where m.history = :history"),
    @NamedQuery(name = "findMilestonesBySprint", query = "Select m from Milestone m where m.sprint = :sprint"),
    @NamedQuery(name = "findByHistoryAndSprint", query = "Select DISTINCT m from Milestone m where m.history = :history and m.sprint = :sprint"),})
public class Milestone extends BasicAttributes {

    private Collection<Artifact> artifacts;

    private String milestone;
    private User author;
    private History history;
    private Sprint sprint;

    @Column(name = "milestone")
    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
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
    @JoinColumn(name = "history_id")
    public History getHistory() {
        if (history == null) {
            history = new History();
        }
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
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
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Milestone)) {
            return false;
        }
        Milestone other = (Milestone) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.Milestone[ id=" + getId() + " ]";
    }

    @Transient
    @JsonIgnore
    public Double getTaskEstimates() {
        Double total = 0.0;
        for (Artifact artifact : getArtifacts()) {
            total = artifact.getTasks().stream().filter((task) -> (task.getEstimatedDuration() != null)).map((task) -> task.getEstimatedDuration()).reduce(total, (accumulator, _item) -> accumulator + _item);
        }
        return total;
    }

    @OneToMany(mappedBy = "milestone")
    public Collection<Artifact> getArtifacts() {
        if (artifacts == null) {
            artifacts = new ArrayList<>();
        }
        return artifacts;
    }

    public void setArtifacts(Collection<Artifact> artifacts) {
        this.artifacts = artifacts;
    }
}
