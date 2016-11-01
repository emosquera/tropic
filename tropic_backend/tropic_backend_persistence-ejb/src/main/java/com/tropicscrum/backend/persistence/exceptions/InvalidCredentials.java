/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author syslife02
 */
@ApplicationException(rollback=true)
public class InvalidCredentials extends Exception {

    public InvalidCredentials(String message) {
        super(message);
    }

    public InvalidCredentials(String message, Throwable cause) {
        super(message, cause);
    }    
}
