/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author conamerica15
 */
@Embeddable
public class BasicAttributes implements Serializable {    
    
    private Calendar modified;
    private Calendar created;
    
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getModified() {
        return modified;
    }

    public void setModified(Calendar modified) {
        this.modified = modified;
    }

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }
           
    public void prePersist() {
        this.created = this.modified = Calendar.getInstance();
    }

    public void preUdpate() {
        this.modified = Calendar.getInstance();
    }            
}