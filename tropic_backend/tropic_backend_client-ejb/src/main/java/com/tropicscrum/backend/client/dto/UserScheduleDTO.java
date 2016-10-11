/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import java.sql.Time;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author syslife02
 */
@XmlRootElement(name = "user_schedule")
public class UserScheduleDTO extends GeneralDTO {
    private SprintUserDTO sprintUserDTO;
    private int dayOfWeek;
    private Time start;
    private Time end;

    public SprintUserDTO getSprintUserDTO() {
        return sprintUserDTO;
    }

    public void setSprintUserDTO(SprintUserDTO sprintUserDTO) {
        this.sprintUserDTO = sprintUserDTO;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().
                append("UserScheduleDTO{id = ").append(this.getId()).
                append(", sprintUserDTO = ").append(sprintUserDTO.toString()).                
                append(", dayOfWeek = ").append(dayOfWeek).
                append(", start = ").append(start).
                append(", end = ").append(end).
                append('}').toString();
    }
}
