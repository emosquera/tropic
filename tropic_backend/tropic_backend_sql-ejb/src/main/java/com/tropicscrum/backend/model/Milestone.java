/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import com.tropicscrum.backend.client.dto.MilestoneDTO;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "milestone")
@EntityListeners(BasicAttributeListener.class)
public class Milestone extends MilestoneDTO implements BasicAttributesFacade {

    private List<Task> tasks;
    private BasicAttributes basicAttributes;
    private Users author;
    private History history;

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

    @ManyToOne
    @JoinColumn(name = "history_id")
    public History getHistory() {
        if (history == null) {
            history = new History();
        }
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
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

    @Override
    public String getMilestone() {
        return super.getMilestone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Version
    @Column(name = "version")
    @Override    
    public Integer getVersion() {
        return super.getVersion(); //To change body of generated methods, choose Tools | Templates.
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Override
    public Long getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
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
        if (!(object instanceof Milestone)) {
            return false;
        }
        Milestone other = (Milestone) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.Milestone[ id=" + id + " ]";
    }

    @OneToMany(mappedBy = "milestone")
    public List<Task> getTasks() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
}
