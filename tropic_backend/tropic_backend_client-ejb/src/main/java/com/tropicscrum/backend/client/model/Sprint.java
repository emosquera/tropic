/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.client.utils.Fibonacci;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;

/**
 *
 * @author Edgar Mosquera
 */
@Entity
@Table(name = "sprint")
@NamedQueries({
    @NamedQuery(name = "findAllSprintsByUser", query = "Select s from Sprint s where s.author = :user"),    
    @NamedQuery(name = "findAllSprintsByCollaborator", query = "Select DISTINCT s from Sprint s where :user != s.author and (:user MEMBER OF s.project.collaborators or :user = s.project.author)"),
    @NamedQuery(name = "findAllSprintsByUserCanCreateTask", query = "Select s.sprint from SprintUser s where :user = s.user and s.createTask = TRUE"),
    @NamedQuery(name = "findAllSprintsBySprintUser", query = "Select s.sprint from SprintUser s where :user = s.user"),
})
public class Sprint extends BasicAttributes {

    private Collection<Milestone> milestones;

    private Collection<SprintVelocity> sprintVelocitys;
    private String code;
    private String name;
    private Date start;
    private GeneralStatus status;
    private Collection<SprintUser> sprintUsers;
    private User author;
    private Project project;        

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "start_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.Sprint[ id=" + getId() + " ]";
    }

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<SprintUser> getSprintUsers() {
        if (sprintUsers == null) {
            sprintUsers = new ArrayList<>();
        }
        return sprintUsers;
    }

    public void setSprintUsers(Collection<SprintUser> sprintUsers) {
        this.sprintUsers = sprintUsers;
    }

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("point")
    public Collection<SprintVelocity> getSprintVelocitys() {
        if (sprintVelocitys == null) {
            sprintVelocitys = new Fibonacci().getSprintVelocitys();
        }
        for (SprintVelocity sv : sprintVelocitys) {
            sv.setSprint(this);
        }
        return sprintVelocitys;
    }

    public void setSprintVelocitys(Collection<SprintVelocity> sprintVelocitys) {
        this.sprintVelocitys = sprintVelocitys;
    }

    @OneToMany(mappedBy = "sprint")
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
