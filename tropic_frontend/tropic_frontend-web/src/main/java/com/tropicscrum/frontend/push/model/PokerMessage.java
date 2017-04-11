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
public class PokerMessage {
    private String taskCode;
    private Boolean showEstimate;
    private Boolean isVote;
    private Boolean isTask;
    private Boolean isClean;
    private Boolean isShow;
    private PokerVote pokerVote;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Boolean getShowEstimate() {
        return showEstimate;
    }

    public void setShowEstimate(Boolean showEstimate) {
        this.showEstimate = showEstimate;
    }

    public Boolean getIsVote() {
        return isVote;
    }

    public void setIsVote(Boolean isVote) {
        this.isVote = isVote;
    }

    public Boolean getIsTask() {
        return isTask;
    }

    public void setIsTask(Boolean isTask) {
        this.isTask = isTask;
    }

    public Boolean getIsClean() {
        return isClean;
    }

    public void setIsClean(Boolean isClean) {
        this.isClean = isClean;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public PokerVote getPokerVote() {
        if (pokerVote == null) {
            pokerVote = new PokerVote();
        }
        return pokerVote;
    }

    public void setPokerVote(PokerVote pokerVote) {
        this.pokerVote = pokerVote;
    }

    public PokerMessage() {
    }        
}
