/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.web.service;

import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.TaskProgressFacadeRemote;
import com.tropicscrum.backend.client.model.TaskProgress;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless
@Path("fixtime")
public class FixTaskTime {

    @EJB(lookup = TaskProgressFacadeRemote.JNDI_REMOTE_NAME)
    TaskProgressFacadeRemote taskProgressFacadeRemote;

    @GET
    @Produces({"application/json; charset=UTF-8"})
    public Collection<TaskProgress> fixTaskTime() throws UpdateException {
        Collection<TaskProgress> taskProgresss = taskProgressFacadeRemote.findAll();
        Collection<TaskProgress> taskProgresssFixed = new ArrayList<>();
        for (TaskProgress taskProgress : taskProgresss) {
            if (taskProgress.getFinalDate() != null) {
                double timeElapsed = taskProgress.getFinalDate().getTimeInMillis() - taskProgress.getDateExecution().getTimeInMillis();
                if (taskProgress.getTimeInProgress() == null || !taskProgress.getTimeInProgress().equals(timeElapsed)) {
                    taskProgress.setTimeInProgress(timeElapsed);
                    try {
                        taskProgressFacadeRemote.edit(taskProgress);
                    } catch (UpdateException ex) {
                        throw new UpdateException("Error en la correcion de tiempos");
                    }
                    taskProgresssFixed.add(taskProgress);
                }
            }
        }
        return taskProgresssFixed;
    }
}
