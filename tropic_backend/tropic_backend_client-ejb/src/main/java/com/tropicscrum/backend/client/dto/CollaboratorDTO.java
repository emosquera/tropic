/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author syslife02
 */
@XmlRootElement(name = "collaborator")
public class CollaboratorDTO {
    private UserDTO userDTO;
    private ProjectDTO projectDTO;
    private Boolean createHistory;
    private Boolean createSprint;
    private Boolean createMilestone;
    private Boolean createTask;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    public Boolean getCreateHistory() {
        return createHistory;
    }

    public void setCreateHistory(Boolean createHistory) {
        this.createHistory = createHistory;
    }

    public Boolean getCreateSprint() {
        return createSprint;
    }

    public void setCreateSprint(Boolean createSprint) {
        this.createSprint = createSprint;
    }

    public Boolean getCreateMilestone() {
        return createMilestone;
    }

    public void setCreateMilestone(Boolean createMilestone) {
        this.createMilestone = createMilestone;
    }

    public Boolean getCreateTask() {
        return createTask;
    }

    public void setCreateTask(Boolean createTask) {
        this.createTask = createTask;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().
                append("CollaboratorDTO{projectDTO = ").append(projectDTO.toString()).
                append(", UserDTO = ").append(userDTO.toString()).
                append(", createHistory = ").append(createHistory).
                append(", createSprint = ").append(createSprint).
                append(", createMilestone = ").append(createMilestone).
                append(", createTask = ").append(createTask).
                append('}').toString();
    }
}
