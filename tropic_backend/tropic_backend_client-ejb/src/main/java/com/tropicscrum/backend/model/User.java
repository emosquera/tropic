/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import com.tropicscrum.backend.client.enums.Gender;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "users")
public class User extends BasicAttributes {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String avatar;
    private Gender gender;
    private List<SprintUser> sprintUsers;
    private List<Project> projectsCollaborator;
    private List<Project> projects;

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

    @OneToMany(mappedBy = "author")
    public List<Project> getProjects() {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @ManyToMany(mappedBy = "collaborators")
    public List<Project> getProjectsCollaborator() {
        if (projectsCollaborator == null) {
            projectsCollaborator = new ArrayList<>();
        }
        return projectsCollaborator;
    }

    public void setProjectsCollaborator(List<Project> projectsCollaborator) {
        this.projectsCollaborator = projectsCollaborator;
    }

    @OneToMany(mappedBy = "user")
    public List<SprintUser> getSprintUsers() {
        if (sprintUsers == null) {
            sprintUsers = new ArrayList<>();
        }
        return sprintUsers;
    }

    public void setSprintUsers(List<SprintUser> sprintUsers) {
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
}
