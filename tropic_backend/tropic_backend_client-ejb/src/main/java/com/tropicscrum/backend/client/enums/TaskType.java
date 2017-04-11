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
public enum TaskType {
    DESING("Diseno"),
    DEVELOPMENT("Desarrollo"),
    TESTING("Pruebas"),
    QA("Control de Calidad"),;
    
    private final String name;

    public String getName() {
        return name;
    }

    private TaskType(String name) {
        this.name = name;
    }        
}
