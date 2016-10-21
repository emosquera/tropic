/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.client.enums.TaskType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author syslife02
 */
@XmlRootElement(name = "task")
public class TaskDTO extends GeneralDTO{
    private String content;
    private SprintDTO sprintDTO;
    private MilestoneDTO milestoneDTO;
    private Double points;
    private Double estimatedDuration;
    private TaskType type;
    private GeneralStatus status;
    private UserDTO authorDTO;
    private List<UserEstimateDTO> userEstimateDTOs;
    private List<TaskProgressDTO> taskProgressDTOs;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SprintDTO getSprintDTO() {
        if (sprintDTO == null) {
            sprintDTO = new SprintDTO();
        }
        return sprintDTO;
    }

    public void setSprintDTO(SprintDTO sprintDTO) {
        this.sprintDTO = sprintDTO;
    }

    public MilestoneDTO getMilestoneDTO() {
        if (milestoneDTO == null) {
            milestoneDTO = new MilestoneDTO();
        }
        return milestoneDTO;
    }

    public void setMilestoneDTO(MilestoneDTO milestoneDTO) {
        this.milestoneDTO = milestoneDTO;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Double estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public UserDTO getAuthorDTO() {
        if (authorDTO == null) {
            authorDTO = new UserDTO();
        }
        return authorDTO;
    }

    public void setAuthorDTO(UserDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public List<UserEstimateDTO> getUserEstimateDTOs() {
        if (userEstimateDTOs == null) {
            userEstimateDTOs = new ArrayList<>();
        }
        return userEstimateDTOs;
    }

    public void setUserEstimateDTOs(List<UserEstimateDTO> userEstimateDTOs) {
        this.userEstimateDTOs = userEstimateDTOs;
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
                append("TaskDTO{ id = ").append(this.getId()).
                append(", version = ").append(this.getVersion()).
                append(", created = ").append(this.getCreated()).
                append(", content ").append(content).
                append(", sprintDTO = ").append(sprintDTO.toString()).
                append(", milestoneDTO = ").append(milestoneDTO.toString()).
                append(", points = ").append(points).
                append(", estimatedDuration = ").append(estimatedDuration).
                append(", type = ").append(type).
                append(", status = ").append(status).
                append(", authorDTO = ").append(authorDTO.toString()).
                append('}').toString();
    }
    
}
