/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author syslife02
 */
@XmlRootElement(name = "task_progress")
public class TaskProgressDTO extends GeneralDTO {
    private SprintUserDTO sprintUserDTO;
    private TaskDTO taskDTO;
    private Calendar dateExecution;
    private GeneralStatus startEstatus;
    private GeneralStatus finalStatus;

    public SprintUserDTO getSprintUserDTO() {
        return sprintUserDTO;
    }

    public void setSprintUserDTO(SprintUserDTO sprintUserDTO) {
        this.sprintUserDTO = sprintUserDTO;
    }

    public TaskDTO getTaskDTO() {
        return taskDTO;
    }

    public void setTaskDTO(TaskDTO taskDTO) {
        this.taskDTO = taskDTO;
    }

    public Calendar getDateExecution() {
        return dateExecution;
    }

    public void setDateExecution(Calendar dateExecution) {
        this.dateExecution = dateExecution;
    }

    public GeneralStatus getStartEstatus() {
        return startEstatus;
    }

    public void setStartEstatus(GeneralStatus startEstatus) {
        this.startEstatus = startEstatus;
    }

    public GeneralStatus getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(GeneralStatus finalStatus) {
        this.finalStatus = finalStatus;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().
                append("TaskProgressDTO{id = ").append(this.getId()).
                append(", sprintUserDTO = ").append(sprintUserDTO.toString()).
                append(", taskDTO = ").append(taskDTO.toString()).
                append(", dateExecution = ").append(dateExecution).
                append(", startEstatus = ").append(startEstatus).
                append(", finalStatus = ").append(finalStatus).
                append('}').toString();
    }
    
}
