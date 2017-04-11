/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Edgar Mosquera
 */
@Entity
@Table(name = "technology")
public class Technology extends BasicAttributes {

    private Collection<Artifact> artifacts;
    
    private String technology;

    @Column(name = "technology")
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
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
        if (!(object instanceof Technology)) {
            return false;
        }
        Technology other = (Technology) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "Technology{" + "technology=" + technology + '}';
    }

    @OneToMany(mappedBy = "technology")
    public Collection<Artifact> getArtifacts() {
        if (artifacts == null) {
            artifacts = new ArrayList<>();
        }
        return artifacts;
    }

    public void setArtifacts(Collection<Artifact> artifacts) {
        this.artifacts = artifacts;
    }
        
}
