/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tropicscrum.backend.client.utils.NumberToFormattedString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Edgar Mosquera
 */
@Entity
@Table(name = "sprint_velocity")
public class SprintVelocity extends BasicAttributes {
    private Double point;    
    private Double hours;
    private Sprint sprint;    

    private transient NumberToFormattedString numberToFormattedString = new NumberToFormattedString();

    @Column(name = "point")
    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    @Column(name = "hours")
    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public SprintVelocity(Double point, Double hours) {
        this.point = point;
        this.hours = hours;
    }

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public SprintVelocity() {
    }       
    
    @Transient
    @JsonIgnore
    public NumberToFormattedString getNumberToFormattedString() {
        if (numberToFormattedString == null) {
            numberToFormattedString = new NumberToFormattedString();
        }
        return numberToFormattedString;
    }
    
    @Transient
    @JsonIgnore
    public String getFormattedPoint() {
        return getNumberToFormattedString().DoubleToString(point);
    }
    
    @Transient
    @JsonIgnore
    public String getFormattedHours() {
        return getNumberToFormattedString().DoubleToString(hours);
    }
}
