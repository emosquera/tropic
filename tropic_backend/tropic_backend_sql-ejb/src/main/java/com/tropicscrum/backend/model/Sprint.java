/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import com.tropicscrum.backend.client.dto.SprintDTO;
import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.sql.facade.BasicAttributesFacade;
import com.tropicscrum.backend.listener.BasicAttributeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "sprint")
@EntityListeners(BasicAttributeListener.class)
public class Sprint extends SprintDTO implements BasicAttributesFacade {

    private List<SprintUser> sprintUsers;

    private List<Task> tasks;
    private List<History> histories;

    private static final long serialVersionUID = 1L;
    private BasicAttributes basicAttributes;
    private Users author;

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

    @Embedded
    @Override
    public BasicAttributes getBasicAttributes() {
        return basicAttributes;
    }

    public void setBasicAttributes(BasicAttributes basicAttributes) {
        this.basicAttributes = basicAttributes;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Override
    public GeneralStatus getStatus() {
        return super.getStatus(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "start_date")
    @Override
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getStart() {
        return super.getStart(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "name")
    @Override
    public String getName() {
        return super.getName(); //To change body of generated methods, choose Tools | Templates.
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
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.Sprint[ id=" + id + " ]";
    }

    @OneToMany(mappedBy = "sprint")
    public List<Task> getTasks() {
        if (tasks == null) {
            return new ArrayList<>();
        }
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @ManyToMany
    public List<History> getHistories() {
        if (histories == null) {
            return new ArrayList<>();
        }
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    @OneToMany(mappedBy = "sprint")
    public List<SprintUser> getSprintUsers() {
        if (sprintUsers == null) {
            return new ArrayList<>();
        }
        return sprintUsers;
    }

    public void setSprintUsers(List<SprintUser> sprintUsers) {
        this.sprintUsers = sprintUsers;
    }
    
}
