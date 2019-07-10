/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.push.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tropicscrum.backend.client.facade.SprintFacadeRemote;
import com.tropicscrum.backend.client.facade.SprintUserFacadeRemote;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import com.tropicscrum.frontend.controllers.application.LoginAppBean;
import com.tropicscrum.frontend.push.model.PokerMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.event.WebsocketEvent;
import javax.faces.event.WebsocketEvent.Closed;
import javax.faces.event.WebsocketEvent.Opened;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Edgar Mosquera
 */
@Named
@ApplicationScoped
public class TaskSprintMessageResource implements Serializable {

    SprintUserFacadeRemote sprintUserFacadeRemote = new ServiceLocatorDelegate<SprintUserFacadeRemote>().getService(SprintUserFacadeRemote.JNDI_REMOTE_NAME);
    SprintFacadeRemote sprintFacadeRemote = new ServiceLocatorDelegate<SprintFacadeRemote>().getService(SprintFacadeRemote.JNDI_REMOTE_NAME);

    @Inject
    private LoginAppBean loginAppBean;

    @Inject
    @Push(channel = "poker")
    private PushContext push;

    public PushContext getPush() {
        return push;
    }

    public void onOpen(@Observes @Opened WebsocketEvent event) {
        String channel = event.getChannel(); // Returns <f:websocket channel>.
        Long userId = event.getUser(); // Returns <f:websocket user>, if any.                  

        SprintUser sprintUser = sprintUserFacadeRemote.find(userId);

        Collection<Long> users = new ArrayList<>();

        Collection<SprintUser> sprintUsers = sprintUserFacadeRemote.findSprintTeam(sprintUser.getSprint());

        sprintUsers.forEach(su -> users.add(su.getId()));

        final ObjectMapper mapper = new ObjectMapper();

        PokerMessage pokerMessage = new PokerMessage();

        pokerMessage.setShowEstimate(Boolean.FALSE);
        pokerMessage.setIsVote(Boolean.FALSE);
        pokerMessage.setIsTask(Boolean.FALSE);
        pokerMessage.setIsClean(Boolean.FALSE);
        pokerMessage.setIsLogin(Boolean.TRUE);
        pokerMessage.getPokerVote().setSprintUser(sprintUser.getId().toString());

        if (!loginAppBean.isUserLogged(sprintUser)) {
            loginAppBean.getUsersLogged().add(sprintUser);
        }

        try {
            String jsonMessage = mapper.writeValueAsString(pokerMessage);
            push.send(jsonMessage, users);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(TaskSprintMessageResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onClose(@Observes @Closed WebsocketEvent event) {
        String channel = event.getChannel(); // Returns <f:websocket channel>.
        Long userId = event.getUser(); // Returns <f:websocket user>, if any.                  

        SprintUser sprintUser = sprintUserFacadeRemote.find(userId);

        Collection<Long> users = new ArrayList<>();

        Collection<SprintUser> sprintUsers = sprintUserFacadeRemote.findSprintTeam(sprintUser.getSprint());

        sprintUsers.forEach(su -> users.add(su.getId()));

        final ObjectMapper mapper = new ObjectMapper();

        PokerMessage pokerMessage = new PokerMessage();

        pokerMessage.setShowEstimate(Boolean.FALSE);
        pokerMessage.setIsVote(Boolean.FALSE);
        pokerMessage.setIsTask(Boolean.FALSE);
        pokerMessage.setIsClean(Boolean.FALSE);
        pokerMessage.setIsLogin(Boolean.TRUE);
        pokerMessage.getPokerVote().setSprintUser(sprintUser.getId().toString());

        loginAppBean.getUsersLogged().remove(sprintUser);

        try {
            String jsonMessage = mapper.writeValueAsString(pokerMessage);
            push.send(jsonMessage, users);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(TaskSprintMessageResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
