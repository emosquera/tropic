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
    private SprintUserDTO sprintUserDTOs;
    private TaskDTO taskDTO;
    private Calendar dateExecution;
    private GeneralStatus startEstatus;
    private GeneralStatus finalStatus;
}
