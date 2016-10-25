/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author conamerica15
 */
@MappedSuperclass
public class BasicAttributes implements Serializable {    
    private Long id;
    private Integer version; 
    private Calendar modified;
    private Calendar created;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        if (version == null) {
            version = 0;
        }
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
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
           
    @PrePersist
    public void prePersist() {
        this.created = this.modified = Calendar.getInstance();
    }

    @PreUpdate
    public void preUdpate() {
        this.modified = Calendar.getInstance();
    }            
}