/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import com.tropicscrum.backend.client.enums.Color;
import com.tropicscrum.backend.client.enums.ScrumRole;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author syslife02
 */
@XmlRootElement(name = "sprint_user")
public class SprintUserDTO extends GeneralDTO{
    private SprintDTO sprintDTO;
    private UserDTO userDTO;
    private Color color;
    private ScrumRole role;
    private Boolean createTask;
    private List<UserScheduleDTO> userScheduleDTOs;
    private List<TaskProgressDTO> taskProgressDTOs;

    public SprintDTO getSprintDTO() {
        if (sprintDTO == null) {
            sprintDTO = new SprintDTO();
        }
        return sprintDTO;
    }

    public void setSprintDTO(SprintDTO sprintDTO) {
        this.sprintDTO = sprintDTO;
    }

    public UserDTO getUserDTO() {
        if (userDTO == null) {
            userDTO = new UserDTO();
        }
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ScrumRole getRole() {
        return role;
    }

    public void setRole(ScrumRole role) {
        this.role = role;
    }

    public Boolean getCreateTask() {
        return createTask;
    }

    public void setCreateTask(Boolean createTask) {
        this.createTask = createTask;
    }

    public List<UserScheduleDTO> getUserScheduleDTOs() {
        if (userScheduleDTOs == null) {
            userScheduleDTOs = new ArrayList<>();
        }
        return userScheduleDTOs;
    }

    public void setUserScheduleDTOs(List<UserScheduleDTO> userScheduleDTOs) {
        this.userScheduleDTOs = userScheduleDTOs;
    }

    public List<TaskProgressDTO> getTaskProgressDTOs() {
        if (taskProgressDTOs == null) {
            taskProgressDTOs = new ArrayList<>();
        }
        return taskProgressDTOs;
    }

    public void setTaskProgressDTOs(List<TaskProgressDTO> taskProgressDTOs) {
        this.taskProgressDTOs = taskProgressDTOs;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().
                append("SprintUserDTO{id = ").append(this.getId()).
                append(", sprintDTO = ").append(sprintDTO.toString()).
                append(", userDTO = ").append(userDTO.toString()).
                append(", color = ").append(color).
                append(", role = ").append(role).
                append(", createTask = ").append(createTask).
                append('}').toString();
    }
}
