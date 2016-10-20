/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import com.tropicscrum.backend.client.dto.TaskDTO;
import com.tropicscrum.backend.client.enums.GeneralStatus;
import com.tropicscrum.backend.client.enums.TaskType;
import com.tropicscrum.backend.sql.facade.BasicAttributesFacade;
import com.tropicscrum.backend.listener.BasicAttributeListener;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author syslife02
 */
@Entity
@Table(name = "task")
@EntityListeners(BasicAttributeListener.class)
public class Task extends TaskDTO implements BasicAttributesFacade {

    private List<TaskProgress> taskProgresss;

    private List<UserEstimate> userEstimates;

    private static final long serialVersionUID = 1L;
    private BasicAttributes basicAttributes;
    private Milestone milestone;
    private Users author;
    private Sprint sprint;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Override
    public GeneralStatus getStatus() {
        return super.getStatus(); //To change body of generated methods, choose Tools | Templates.
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @Override
    public TaskType getType() {
        return super.getType(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "estimated_duration")
    @Override
    public Double getEstimatedDuration() {
        return super.getEstimatedDuration(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "points")
    @Override
    public Double getPoints() {
        return super.getPoints(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "content")
    @Override
    public String getContent() {
        return super.getContent(); //To change body of generated methods, choose Tools | Templates.
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

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    public Milestone getMilestone() {
        if (milestone == null) {
            return new Milestone();
        }
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
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

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    public Sprint getSprint() {
        if (sprint == null) {
            return new Sprint();
        }
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.Task[ id=" + id + " ]";
    }

    @Embedded
    @Override
    public BasicAttributes getBasicAttributes() {
        return basicAttributes;
    }
    
    public void setBasicAttributes(BasicAttributes basicAttributes) {
        this.basicAttributes = basicAttributes;
    }

    @OneToMany(mappedBy = "task")
    public List<UserEstimate> getUserEstimates() {
        if (userEstimates == null) {
            return new ArrayList<>();
        }
        return userEstimates;
    }

    public void setUserEstimates(List<UserEstimate> userEstimates) {
        this.userEstimates = userEstimates;
    }

    @OneToMany(mappedBy = "task")
    public List<TaskProgress> getTaskProgresss() {
        if (taskProgresss == null) {
            return new ArrayList<>();
        }
        return taskProgresss;
    }

    public void setTaskProgresss(List<TaskProgress> taskProgresss) {
        this.taskProgresss = taskProgresss;
    }
}
