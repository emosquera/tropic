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
public enum MeasureUnit {
    CODE_LINES("Lineas de Codigo"),
    PAGES("Paginas");
    
    private final String name;

    public String getName() {
        return name;
    }

    private MeasureUnit(String name) {
        this.name = name;
    }        
}
