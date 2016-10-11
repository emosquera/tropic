/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import com.tropicscrum.backend.client.enums.GeneralStatus;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author syslife02
 */
@XmlRootElement(name = "history")
public class HistoryDTO extends GeneralDTO{
    private String title;
    private String content;
    private ProjectDTO projectDTO;
    private UserDTO authorDTO;
    private GeneralStatus status;
    private List<SprintDTO> sprintDTOs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    public UserDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(UserDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public List<SprintDTO> getSprintDTOs() {
        if (sprintDTOs == null) {
            sprintDTOs = new ArrayList<>();
        }
        return sprintDTOs;
    }

    public void setSprintDTOs(List<SprintDTO> sprintDTOs) {
        this.sprintDTOs = sprintDTOs;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().
                append("HistoryDTO{ id = ").append(this.getId()).
                append(", version = ").append(this.getVersion()).
                append(", created = ").append(this.getCreated()).
                append(", title = ").append(title).
                append(", content = ").append(content).
                append(", project = ").append(projectDTO.toString()).
                append(", authorDTO = ").append(authorDTO.toString()).
                append(", status = ").append(status).
                append('}').toString();
    }
}
