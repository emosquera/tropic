/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import com.tropicscrum.backend.client.dto.ProjectDTO;
import com.tropicscrum.backend.sql.facade.BasicAttributesFacade;
import com.tropicscrum.backend.listener.BasicAttributeListener;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "project")
@EntityListeners(BasicAttributeListener.class)
public class Project extends ProjectDTO implements BasicAttributesFacade {
    private BasicAttributes basicAttributes;     
    private Users author;
    private List<History> histories;
    private List<Users> collaborators;
        
    @ManyToOne
    @JoinColumn(name = "user_id")
    public Users getAuthor() {
        if (author == null) {
            author = new Users();
        }
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Override
    public Long getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }    

    @Column(name = "description")
    @Override
    public String getDescription() {
        return super.getDescription(); //To change body of generated methods, choose Tools | Templates.
    }

    @Version
    @Column(name = "version")
    @Override
    public Integer getVersion() {
        return super.getVersion(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Embedded
    @Override
    public BasicAttributes getBasicAttributes() {
        if (basicAttributes == null) {
            basicAttributes = new BasicAttributes();
        }
        return basicAttributes;
    }

    @Override
    public void setBasicAttributes(BasicAttributes basicAttributes) {
        this.basicAttributes = basicAttributes;
    }

    @OneToMany(mappedBy = "project")
    public List<History> getHistories() {
        if (histories == null) {
            histories = new ArrayList<>();
        }
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    @ManyToMany
    public List<Users> getCollaborators() {
        if (collaborators == null) {
            collaborators = new ArrayList<>();
        }
        return collaborators;
    }

    public void setCollaborators(List<Users> collaborators) {
        this.collaborators = collaborators;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.Project[ id=" + id + " ]";
    }
    
}
