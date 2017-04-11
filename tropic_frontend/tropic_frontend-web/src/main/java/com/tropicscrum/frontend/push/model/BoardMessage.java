/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.push.model;

/**
 *
 * @author Edgar Mosquera
 */
public class BoardMessage {
    private String action;
    private String taskId;
    private String taskProgressId;
    private String sprintUserId;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskProgressId() {
        return taskProgressId;
    }

    public void setTaskProgressId(String taskProgressId) {
        this.taskProgressId = taskProgressId;
    }    

    public String getSprintUserId() {
        return sprintUserId;
    }

    public void setSprintUserId(String sprintUserId) {
        this.sprintUserId = sprintUserId;
    }

    public BoardMessage(String action, String taskId, String taskProgressId) {
        this.action = action;
        this.taskId = taskId;
        this.taskProgressId = taskProgressId;
    }

    public BoardMessage(String action, String taskId, String taskProgressId, String sprintUserId) {
        this.action = action;
        this.taskId = taskId;
        this.taskProgressId = taskProgressId;
        this.sprintUserId = sprintUserId;
    }

    public BoardMessage(String action, String taskProgressId) {
        this.action = action;
        this.taskProgressId = taskProgressId;
    }        

    public BoardMessage() {
    }        
}
