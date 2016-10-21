/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author syslife02
 */
@XmlRootElement(name = "milestone")
public class MilestoneDTO extends GeneralDTO{
    private String milestone;
    private HistoryDTO historyDTO;
    private List<TaskDTO> taskDTOs;
    private UserDTO authorDTO;

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public HistoryDTO getHistoryDTO() {
        return historyDTO;
    }

    public void setHistoryDTO(HistoryDTO historyDTO) {
        this.historyDTO = historyDTO;
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
                append("MilestoneDTO{ id = ").append(this.getId()).
                append(", version = ").append(this.getVersion()).
                append(", created = ").append(this.getCreated()).
                append(", milestone = ").append(milestone).
                append(", historyDTO = ").append(historyDTO.toString()). 
                append(", authorDTO = ").append(authorDTO.toString()). 
                append('}').toString();
    }
}
