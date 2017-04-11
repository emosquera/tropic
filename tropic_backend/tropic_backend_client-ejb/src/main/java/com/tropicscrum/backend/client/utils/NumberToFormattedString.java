/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.utils;

/**
 *
 * @author Edgar Mosquera
 */
public class NumberToFormattedString {    
    public String DoubleToString(Double value) {
        if (value == null) {
            value = 0.0;
        }
        if (Math.floor(value) == value) {
            Integer intValue = value.intValue();
            return intValue.toString();
        } else {
            Double frac = value - Math.floor(value);
            String halfString = "&frac12;";
            if (frac != 0.5) {
                return value.toString();
            } else {
                Integer intValue = value.intValue();
                if (intValue > 0) {
                    return intValue.toString() + halfString;
                } else {
                    return halfString;
                }                
            }            
        }
    }
}
