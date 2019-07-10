/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.push.resource;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
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
public class BoardMessageResource implements Serializable {

    @Inject
    @Push(channel = "board")
    private PushContext push;

    public PushContext getPush() {
        return push;
    }  
}
