/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "schedule")
@NamedQueries({
    @NamedQuery(name = "findByRangeOfDayAndTime", query = "Select s from Schedule s where s.dayOfWeek >= :startDay and s.dayOfWeek <= :endDay and s.start >= :startTime and s.start <= :endTime order by s.start, s.dayOfWeek"),
    @NamedQuery(name = "findAllOrdered", query = "Select s from Schedule s order by s.start, s.dayOfWeek")})
@XmlRootElement
public class Schedule extends BasicAttributes {

    private Collection<SprintUser> sprintUsers;
    
    private int dayOfWeek;
    private Date start;
    private Date end;    
    private String formattedStart;
    private String formattedEnd;
    private String completeFormattedSchedule;

    @Column(name = "day_of_week")
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Column(name = "start_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Column(name = "final_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.client.model.Schedule[ id=" + getId() + " ]";
    }

    @ManyToMany(mappedBy = "schedules")
    public Collection<SprintUser> getSprintUsers() {
        return sprintUsers;
    }

    public void setSprintUsers(Collection<SprintUser> sprintUsers) {
        this.sprintUsers = sprintUsers;
    }

    @Transient
    public String getFormattedStart() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        formattedStart = timeFormat.format(start);
        return formattedStart;
    }

    public void setFormattedStart(String formattedStart) {
        this.formattedStart = formattedStart;
    }

    @Transient
    public String getFormattedEnd() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        formattedEnd = timeFormat.format(end);
        return formattedEnd;
    }

    public void setFormattedEnd(String formattedEnd) {
        this.formattedEnd = formattedEnd;
    }

    @Transient
    public String getCompleteFormattedSchedule() {
        String dayInLetter = "";
        SimpleDateFormat shortTimeFormat = new SimpleDateFormat("h");
        SimpleDateFormat largeTimeFormat = new SimpleDateFormat("h a");
        switch(dayOfWeek) {
            case 1:
                dayInLetter = "DOM";
                break;
            case 2:
                dayInLetter = "LUN";
                break;
            case 3:
                dayInLetter = "MAR";
                break;
            case 4:
                dayInLetter = "MIE";
                break;
            case 5:
                dayInLetter = "JUE";
                break;
            case 6:
                dayInLetter = "VIE";
                break;
            case 7:
                dayInLetter = "SAB";
                break;
        }
        completeFormattedSchedule = dayInLetter + ", " + shortTimeFormat.format(start) + " - "  + largeTimeFormat.format(end);
        return completeFormattedSchedule;
    }

    public void setCompleteFormattedSchedule(String completeFormattedSchedule) {
        this.completeFormattedSchedule = completeFormattedSchedule;
    }

}
