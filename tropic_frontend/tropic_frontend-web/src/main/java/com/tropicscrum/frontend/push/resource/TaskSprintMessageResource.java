/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.push.resource;

import com.tropicscrum.frontend.push.encoders.PokerMessageDecoder;
import com.tropicscrum.frontend.push.encoders.PokerMessageEncoder;
import javax.faces.application.FacesMessage;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;

/**
 *
 * @author Edgar Mosquera
 */
@PushEndpoint("/poker/{sprint}/{userid}")
@Singleton
public class TaskSprintMessageResource {

    @PathParam("sprint")
    private String sprint;

    @PathParam("userid")
    private String userid;

    @OnMessage(decoders = {PokerMessageDecoder.class}, encoders = {PokerMessageEncoder.class})
    public FacesMessage onMessage(FacesMessage message) {
        return message;
    }
}
