/*
 * To change this license header, choose License Headers in ProjectDTO Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import com.tropicscrum.backend.client.enums.Gender;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edgar Mosquera
 */
@XmlRootElement(name = "users")
public class UserDTO extends GeneralDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String avatar;
    private Gender gender;
    private List<ProjectDTO> projectDTOs;    
    private List<ProjectDTO> projectsCollaboratorDTOs;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String FirstName) {
        this.firstName = FirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String Avatar) {
        this.avatar = Avatar;
    } 

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public List<ProjectDTO> getProjectDTOs() {
        if (projectDTOs == null) {
            projectDTOs = new ArrayList<>();
        }
        return projectDTOs;
    }

    public void setProjectDTOs(List<ProjectDTO> Projetcs) {
        this.projectDTOs = Projetcs;
    }
    
    public String getName() {
        return new StringBuilder().append(firstName).append(" ").append(lastName).toString();
    }

    public List<ProjectDTO> getProjectsCollaboratorDTOs() {
        if (projectsCollaboratorDTOs == null) {
            projectsCollaboratorDTOs = new ArrayList<>();
        }
        return projectsCollaboratorDTOs;
    }

    public void setProjectsCollaboratorDTOs(List<ProjectDTO> projectsCollaboratorDTOs) {
        this.projectsCollaboratorDTOs = projectsCollaboratorDTOs;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().
                append("UserDTO{ id = ").append(this.getId()).
                append(", version = ").append(this.getVersion()).
                append(", created = ").append(this.getCreated()).
                append(", firstName = ").append(firstName).
                append(", lastName = ").append(lastName).
                append(", email = ").append(email).
                append(", avatar = ").append(avatar).
                append('}').toString();
    }
}
