/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.listener;

import com.tropicscrum.backend.facade.BasicAttributesFacade;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author syslife02
 */
public class BasicAttributeListener {
    
    @PrePersist
    public void auditCreation(BasicAttributesFacade basicAttributesFacade) {
        basicAttributesFacade.getBasicAttributes().prePersist();
    }
    
    @PreUpdate
    public void auditUpdate(BasicAttributesFacade basicAttributesFacade) {
        basicAttributesFacade.getBasicAttributes().preUdpate();
    }
}
