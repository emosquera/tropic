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
public class OldVersionException extends Exception {

    public OldVersionException(String message) {
        super(message);
    }
    
}
