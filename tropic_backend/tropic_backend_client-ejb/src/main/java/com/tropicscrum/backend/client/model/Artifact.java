/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import com.tropicscrum.backend.client.enums.MeasureUnit;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Edgar Mosquera
 */
@Entity
@Table(name = "artifact")
public class Artifact extends BasicAttributes {

    private String code;
    private String description; 
    private MeasureUnit measureUnit;
    private Double finalSize;
    private Technology technology;
    private Milestone milestone;
    private User author;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "measure_unit")
    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    @Column(name = "final_size")
    public Double getFinalSize() {
        return finalSize;
    }

    public void setFinalSize(Double finalSize) {
        this.finalSize = finalSize;
    }

    @ManyToOne
    @JoinColumn(name = "technology_id")
    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artifact)) {
            return false;
        }
        Artifact other = (Artifact) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.client.model.Artifact[ id=" + this.getId() + " ]";
    }
    
}
