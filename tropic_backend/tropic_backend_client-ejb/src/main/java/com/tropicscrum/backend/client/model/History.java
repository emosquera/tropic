/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "history")
@NamedQueries({
    @NamedQuery(name = "findAllHistoriesByUser", query = "Select h from History h where h.author = :user"),
    @NamedQuery(name = "findAllHistoriesByCollaborator", query = "Select DISTINCT h from History h where :user != h.author and (:user MEMBER OF h.project.collaborators or :user = h.project.author)"),
    @NamedQuery(name = "findByProject", query = "Select h from History h where h.project = :project"),
    @NamedQuery(name = "findBySprint", query = "Select DISTINCT h from History h INNER JOIN h.milestones m where m.sprint = :sprint"),
})
public class History extends BasicAttributes {
    private String code;
    private String title;
    private String content;
    private GeneralStatus status;
    private Collection<Milestone> milestones;
    private Project project;
    private User author;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project getProject() {
        if (project == null) {
            project = new Project();
        }
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.History[ id=" + this.getId() + " ]";
    }

    @OneToMany(mappedBy = "history")
    public Collection<Milestone> getMilestones() {
        if (milestones == null) {
            milestones = new ArrayList<>();
        }
        return milestones;
    }

    public void setMilestones(Collection<Milestone> milestones) {
        this.milestones = milestones;
    } 
}
