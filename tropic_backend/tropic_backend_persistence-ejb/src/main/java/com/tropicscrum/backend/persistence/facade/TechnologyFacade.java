/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.Technology;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless
public class TechnologyFacade extends AbstractFacade<Technology> implements TechnologyFacadeLocal {
    @PersistenceContext(unitName = "tropic_backend_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TechnologyFacade() {
        super(Technology.class);
    }        
}
