/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import com.tropicscrum.backend.client.dto.UserDTO;
import com.tropicscrum.backend.client.enums.Gender;
import com.tropicscrum.backend.sql.facade.BasicAttributesFacade;
import com.tropicscrum.backend.listener.BasicAttributeListener;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "users")
@EntityListeners(BasicAttributeListener.class)
public class Users extends UserDTO implements BasicAttributesFacade {

    private List<SprintUser> sprintUsers;

    private List<Project> projects_collaborator;

    private static final long serialVersionUID = 1L;
    private BasicAttributes basicAttributes; 
    private List<Project> projects;

    @Embedded
    @Override
    public BasicAttributes getBasicAttributes() {
        return basicAttributes;
    }

    public void setBasicAttributes(BasicAttributes basicAttributes) {
        this.basicAttributes = basicAttributes;
    }
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Override
    public Long getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "avatar")
    @Override
    public String getAvatar() {
        return super.getAvatar(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "password")
    @Override
    public String getPassword() {
        return super.getPassword(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "email")
    @Override
    public String getEmail() {
        return super.getEmail(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "last_name")
    @Override
    public String getLastName() {
        return super.getLastName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "fisrt_name")
    @Override
    public String getFirstName() {
        return super.getFirstName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @Override
    public Gender getGender() {
        return super.getGender(); //To change body of generated methods, choose Tools | Templates.
    }        

    @Version
    @Column(name = "version")
    @Override
    public Integer getVersion() {
        return super.getVersion(); //To change body of generated methods, choose Tools | Templates.
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.User[ id=" + id + " ]";
    }
    
    @OneToMany(mappedBy = "author")
    public List<Project> getProjects() {
        if (projects == null) {
            return new ArrayList<>();
        }
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @ManyToMany(mappedBy = "collaborators")
    public List<Project> getProjects_collaborator() {
        if (projects_collaborator == null) {
            return new ArrayList<>();
        }
        return projects_collaborator;
    }

    public void setProjects_collaborator(List<Project> projects_collaborator) {
        this.projects_collaborator = projects_collaborator;
    }

    @OneToMany(mappedBy = "user")
    public List<SprintUser> getSprintUsers() {
        if (sprintUsers == null) {
            return new ArrayList<>();
        }
        return sprintUsers;
    }

    public void setSprintUsers(List<SprintUser> sprintUsers) {
        this.sprintUsers = sprintUsers;
    }

    public Users() {
    }
    
    public Users(UserDTO userDTO) {
        super.setId(userDTO.getId());
        super.setFirstName(userDTO.getFirstName());
        super.setLastName(userDTO.getLastName());
        super.setGender(userDTO.getGender());
        super.setAvatar(userDTO.getAvatar());
        super.setPassword(userDTO.getPassword());        
    }
}
