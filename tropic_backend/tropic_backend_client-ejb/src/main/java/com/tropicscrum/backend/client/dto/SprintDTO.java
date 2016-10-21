/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author syslife02
 */
@XmlRootElement(name = "sprint")
public class SprintDTO extends GeneralDTO {
    private String name;
    private Date start;
    private GeneralStatus status;
    private List<HistoryDTO> historyDTOs;
    private List<TaskDTO> taskDTOs;
    private List<SprintUserDTO> sprintUserDTOs;
    private UserDTO authorDTO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public List<HistoryDTO> getHistoryDTOs() {
        if (historyDTOs == null) {
            historyDTOs = new ArrayList<>();
        }
        return historyDTOs;
    }

    public void setHistoryDTOs(List<HistoryDTO> historyDTOs) {
        this.historyDTOs = historyDTOs;
    }

    public List<TaskDTO> getTaskDTOs() {
        if (taskDTOs == null) {
            taskDTOs = new ArrayList<>();
        }
        return taskDTOs;
    }

    public void setTaskDTOs(List<TaskDTO> taskDTOs) {
        this.taskDTOs = taskDTOs;
    }

    public List<SprintUserDTO> getSprintUserDTOs() {
        if (sprintUserDTOs == null) {
            sprintUserDTOs = new ArrayList<>();
        }
        return sprintUserDTOs;
    }

    public void setSprintUserDTOs(List<SprintUserDTO> sprintUserDTOs) {
        this.sprintUserDTOs = sprintUserDTOs;
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
    
    @Override
    public String toString() {
        return new StringBuilder().
                append("SprintDTO{ id = ").append(this.getId()).
                append(", version = ").append(this.getVersion()).
                append(", created = ").append(this.getCreated()).
                append(", name = ").append(name).
                append(", start = ").append(start).
                append(", status = ").append(status).
                append(", authorDTO = ").append(authorDTO.toString()).
                append('}').toString();
    }
}
