/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface MilestoneFacadeLocal {

    void create(Milestone milestone);

    void edit(Milestone milestone) throws OldVersionException;

    void remove(Milestone milestone);

    Milestone find(Object id);

    List<Milestone> findAll();

    List<Milestone> findRange(int[] range);

    int count();
    
}
