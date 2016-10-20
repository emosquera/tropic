/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.model;

import com.tropicscrum.backend.client.dto.SprintUserDTO;
import com.tropicscrum.backend.client.enums.Color;
import com.tropicscrum.backend.client.enums.ScrumRole;
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
@Table(name = "sprint_user")
@EntityListeners(BasicAttributeListener.class)
public class SprintUser extends SprintUserDTO implements BasicAttributesFacade {

    private List<TaskProgress> taskProgresss;

    private List<UserSchedule> userSchedules;

    private List<UserEstimate> userEstimates;

    private static final long serialVersionUID = 1L;
    private BasicAttributes basicAttributes;
    private Sprint sprint;
    private Users user;

    @Embedded
    @Override
    public BasicAttributes getBasicAttributes() {
        return basicAttributes;
    }

    public void setBasicAttributes(BasicAttributes basicAttributes) {
        this.basicAttributes = basicAttributes;
    }

    @Column(name = "create_task")
    @Override
    public Boolean getCreateTask() {
        return super.getCreateTask(); //To change body of generated methods, choose Tools | Templates.
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "team_role")
    @Override
    public ScrumRole getRole() {
        return super.getRole(); //To change body of generated methods, choose Tools | Templates.
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    @Override
    public Color getColor() {
        return super.getColor(); //To change body of generated methods, choose Tools | Templates.
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Users getUser() {
        if (user == null) {
            return new Users();
        }
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
        if (!(object instanceof SprintUser)) {
            return false;
        }
        SprintUser other = (SprintUser) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.tropicscrum.backend.model.SprintUser[ id=" + id + " ]";
    }

    @OneToMany(mappedBy = "sprintUser")
    public List<UserEstimate> getUserEstimates() {
        if (userEstimates == null) {
            return new ArrayList<>();
        }
        return userEstimates;
    }

    public void setUserEstimates(List<UserEstimate> userEstimates) {
        this.userEstimates = userEstimates;
    }

    @OneToMany(mappedBy = "sprintUser")
    public List<UserSchedule> getUserSchedules() {
        if (userSchedules == null) {
            return new ArrayList<>();
        }
        return userSchedules;
    }

    public void setUserSchedules(List<UserSchedule> userSchedules) {
        this.userSchedules = userSchedules;
    }

    @OneToMany(mappedBy = "sprintUser")
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
