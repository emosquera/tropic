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
public enum Color {    
    White("Blanco", "#ffffff", "#000000"),
    Cyan("Cielo", "#d2f9f9", "#134656"),
    Silver("Plata", "#e8e8e8", "#2d2d2d"),
    Blue("Azul", "#71a5e6", "#082954"),
    Gray("Gris", "#b9b9b9", "#000000"),
    DarkBlue("Azul Oscuro", "#557c9e", "#f2f9ff"),
    Black("Negro", "#000000", "#ffffff"),
    LightBlue("Azul Claro", "#add8e6", "#093a65"),
    Orange("Naranja", "#fbd99b",  "#442f0a"),
    Purple("Morado", "#d7bfe6", "#420742"),
    Brown("Marron", "#eaa893", "#541515"),
    Yellow("Amarillo", "#ffffe6", "#4c5200"),
    Maroon("Madera", "#bb9a77", "#462506"),
    Red("Rojo", "#fdb6b6", "#691c1c"),
    Lime("Lima", "#bff3bf", "#044804"),
    Green("Verde", "#8ab386", "#163516"),
    Magenta("Magenta", "#f3caf3", "#6b076b"),
    Olive("Oliva", "#b3b37f", "#2f2f02");
    
    private final String name;
    private final String hexColor;
    private final String hexBorderColor;

    public String getName() {
        return name;
    }

    public String getHexColor() {
        return hexColor;
    }

    public String getHexBorderColor() {
        return hexBorderColor;
    }

    private Color(String name, String hexColor, String hexBorderColor) {
        this.name = name;
        this.hexColor = hexColor;
        this.hexBorderColor = hexBorderColor;
    }
    
    
}

