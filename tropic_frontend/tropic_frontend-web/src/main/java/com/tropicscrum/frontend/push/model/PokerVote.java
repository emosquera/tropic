/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.push.model;

import com.tropicscrum.backend.client.model.SprintVelocity;

/**
 *
 * @author Edgar Mosquera
 */
public class PokerVote {
    private String sprintUser;
    private SprintVelocity sprintVelocity;

    public String getSprintUser() {
        return sprintUser;
    }

    public void setSprintUser(String sprintUser) {
        this.sprintUser = sprintUser;
    }

    public SprintVelocity getSprintVelocity() {
        if (sprintVelocity == null) {
            sprintVelocity = new SprintVelocity();
        }
        return sprintVelocity;
    }

    public void setSprintVelocity(SprintVelocity sprintVelocity) {
        this.sprintVelocity = sprintVelocity;
    }

    public PokerVote(String sprintUser, SprintVelocity sprintVelocity) {
        this.sprintUser = sprintUser;
        this.sprintVelocity = sprintVelocity;
    }

    public PokerVote() {
    }        
}
