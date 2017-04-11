/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.enums;

/**
 *
 * @author Edgar Mosquera
 */
public enum Gender {
    MALE("Masculino"),
    FEMALE("Femenino");
    
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private Gender(String label) {
        this.label = label;
    }
}
