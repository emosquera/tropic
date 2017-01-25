/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.enums;

/**
 *
 * @author syslife02
 */
public enum ScrumRole {
    PRODUCT_OWNER("Product Owner"),
    SCRUM_MASTER("Scrum Master"),
    TEAM_EXECUTER("Desarrollador"),
    TEAM_TESTER("Analista de Pruebas");
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ScrumRole(String name) {
        this.name = name;
    }
    
    
}
