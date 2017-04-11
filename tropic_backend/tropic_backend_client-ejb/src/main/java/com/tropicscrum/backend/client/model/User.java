/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.tropicscrum.backend.client.enums.Gender;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
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
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "findByEmail", query = "Select u from User u where u.email = :email"),
    @NamedQuery(name = "findOtherByEmail", query = "Select u from User u where u != :user and u.email like concat('%', :email, '%')"),
    @NamedQuery(name = "filterByEmail", query = "Select u from User u where u.email like concat('%', :email, '%')")})
public class User extends BasicAttributes {

    private Collection<Artifact> artifacts;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String avatar;
    private Gender gender;
    private Boolean confirmed = false;
    private Collection<SprintUser> sprintUsers;
    private Collection<Project> projectsCollaborator;
    private Collection<Project> projects;
    
    private String completeName;

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "confirmed")
    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    public Collection<Project> getProjects() {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    @ManyToMany(mappedBy = "collaborators")
    public Collection<Project> getProjectsCollaborator() {
        if (projectsCollaborator == null) {
            projectsCollaborator = new ArrayList<>();
        }
        return projectsCollaborator;
    }

    public void setProjectsCollaborator(Collection<Project> projectsCollaborator) {
        this.projectsCollaborator = projectsCollaborator;
    }

    @OneToMany(mappedBy = "user")
    public Collection<SprintUser> getSprintUsers() {
        if (sprintUsers == null) {
            sprintUsers = new ArrayList<>();
        }
        return sprintUsers;
    }

    public void setSprintUsers(Collection<SprintUser> sprintUsers) {
        this.sprintUsers = sprintUsers;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.User[ id=" + getId() + " ]";
    }
    
    @Transient
    public String getCompleteName() {     
        completeName = getFirstName() + " " + getLastName();
        return completeName.trim();
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    @OneToMany(mappedBy = "author")
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
