/*
 * To change this license header, choose License Headers in ProjectDTO Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edgar Mosquera
 */

@XmlRootElement(name = "project")
public class ProjectDTO extends GeneralDTO{
    private String description;
    private UserDTO authorDTO;
    private List<HistoryDTO> historyDTOs;
    private List<UserDTO> collaboratorDTOs;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(UserDTO authorDTO) {
        this.authorDTO = authorDTO;
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

    public List<UserDTO> getCollaboratorDTOs() {
        if (collaboratorDTOs == null) {
            collaboratorDTOs = new ArrayList<>();
        }
        return collaboratorDTOs;
    }

    public void setCollaboratorDTOs(List<UserDTO> collaboratorDTOs) {
        this.collaboratorDTOs = collaboratorDTOs;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().
                append("ProjectDTO{ id = ").append(this.getId()).
                append(", version = ").append(this.getVersion()).
                append(", created = ").append(this.getCreated()).
                append(", description = ").append(description).
                append(", authorDTO = ").append(authorDTO.toString()).
                append('}').toString();
    }
}
