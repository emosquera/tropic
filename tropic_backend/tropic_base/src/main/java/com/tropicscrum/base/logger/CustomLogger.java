/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tropicscrum.base.logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar Mosquera
 */
public class CustomLogger {

    private static Logger generalLogger;

    public static Logger getGeneralLogger(String nombre, String propiedad) {
        generalLogger = Logger.getLogger(nombre);
        if (propiedad.compareTo("off") == 0) {
            generalLogger.setLevel(Level.OFF);
        }else{
            generalLogger.setLevel(Level.ALL);
        }
        return generalLogger;
    }

    public static void setGeneralLogger(Logger generalLogger) {
        CustomLogger.generalLogger = generalLogger;
    }
}
