/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Edgar Mosquera
 */
public class GeneralDTO implements Serializable{
    
    public Long id;
    public Integer version; 
    private Calendar created;
    private Calendar modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getModified() {
        return modified;
    }

    public void setModified(Calendar modified) {
        this.modified = modified;
    }

    @Override
   public int hashCode() {
       int hash = 0;
       hash += (id != null ? id.hashCode() : 0);
       return hash;
   }

   @Override
   public boolean equals(Object object) {
       // TODO: Warning - this method won't work in the case the id fields are not set
       if (!(object instanceof GeneralDTO)) {
           return false;
       }
       GeneralDTO other = (GeneralDTO) object;
       return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
   }
    
}
