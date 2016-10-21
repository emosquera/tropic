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
@XmlRootElement(name = "user_estimate")
public class UserEstimateDTO extends GeneralDTO{
    private SprintUserDTO sprintUserDTO;
    private TaskDTO taskDTO;
    private int points;

    public SprintUserDTO getSprintUserDTO() {
        if (sprintUserDTO == null) {
            sprintUserDTO = new SprintUserDTO();
        }
        return sprintUserDTO;
    }

    public void setSprintUserDTO(SprintUserDTO sprintUserDTO) {
        this.sprintUserDTO = sprintUserDTO;
    }

    public TaskDTO getTaskDTO() {
        if (taskDTO == null) {
            taskDTO = new TaskDTO();
        }
        return taskDTO;
    }

    public void setTaskDTO(TaskDTO taskDTO) {
        this.taskDTO = taskDTO;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().
                append("UserEstimateDTO{id = ").append(this.id).
                append(", sprintUserDTO = ").append(sprintUserDTO.toString()).
                append(", taskDTO = ").append(taskDTO.toString()).
                append(", points = ").append(points).
                append('}').toString();
    }
}
