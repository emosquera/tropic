/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.web.service;

import com.tropicscrum.backend.client.facade.TechnologyFacadeRemote;
import com.tropicscrum.backend.client.model.Technology;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author syslife02
 */
@Stateless
@Path("technology")
public class InitTechnology {

    TechnologyFacadeRemote technologyFacadeRemote = new ServiceLocatorDelegate<TechnologyFacadeRemote>().getService(TechnologyFacadeRemote.JNDI_REMOTE_NAME);    
    
    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Collection<Technology> createDefaultSchedule() {
        Collection<Technology> allTechnology = new ArrayList<>();

        Technology technologyPython = new Technology("PYTHON");
        technologyFacadeRemote.create(technologyPython);
        allTechnology.add(technologyPython);
        
        Technology technologyDc = new Technology("DJANGO CONTROLLER");
        technologyFacadeRemote.create(technologyDc);
        allTechnology.add(technologyDc);
        
        Technology technologyDt = new Technology("DJANGO TEMPLATE");
        technologyFacadeRemote.create(technologyDt);
        allTechnology.add(technologyDt);       
        
        Technology technologyJs = new Technology("JAVASCRIPT");
        technologyFacadeRemote.create(technologyJs);
        allTechnology.add(technologyJs);
        
        Technology technologyCss = new Technology("CSS3");
        technologyFacadeRemote.create(technologyCss);
        allTechnology.add(technologyCss);
        
        Technology technologyHtml = new Technology("HTML");
        technologyFacadeRemote.create(technologyHtml);
        allTechnology.add(technologyHtml);
        
        Technology technologyUml = new Technology("USE CASE DIAGRAM (UML)");
        technologyFacadeRemote.create(technologyUml);
        allTechnology.add(technologyUml);
        
        Technology technologyNarr = new Technology("USE CASE NARRATIVE");
        technologyFacadeRemote.create(technologyNarr);
        allTechnology.add(technologyNarr);
        
        Technology technologyMockup = new Technology("MOCKUP");
        technologyFacadeRemote.create(technologyMockup);
        allTechnology.add(technologyMockup);
        
        return allTechnology;
    }
}
