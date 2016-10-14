/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import com.tropicscrum.backend.client.dto.HistoryDTO;
import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.facade.BasicAttributesFacade;
import com.tropicscrum.backend.listener.BasicAttributeListener;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "history")
@EntityListeners(BasicAttributeListener.class)
public class History extends HistoryDTO implements BasicAttributesFacade {

    private static final long serialVersionUID = 1L;
    private BasicAttributes basicAttributes;
    private Project project;
    private Users author;

    @Embedded
    @Override
    public BasicAttributes getBasicAttributes() {
        return basicAttributes;
    }

    public void setBasicAttributes(BasicAttributes basicAttributes) {
        this.basicAttributes = basicAttributes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Override
    public Long getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Override
    public GeneralStatus getStatus() {
        return super.getStatus(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "content")
    @Override
    public String getContent() {
        return super.getContent(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "title")
    @Override
    public String getTitle() {
        return super.getTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project getProject() {
        if (project == null) {
            return new Project();
        }
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Users getAuthor() {
        if (author == null) {
            return new Users();
        }
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
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
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.History[ id=" + id + " ]";
    }
    
}
