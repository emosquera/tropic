/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Edgar Mosquera
 */
@ApplicationException(rollback=true)
public class LoginException extends Exception {
    public LoginException(String message) {
        super(message);
    }    

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }        
}
