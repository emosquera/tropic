/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.base.locator;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Edgar Mosquera
 * @param <T>
 */
public abstract class AbstractDelegate<T> {

    @SuppressWarnings("unchecked")
    protected T getDelegado(String jndiName) {
        T delegado = null;
        try {
            delegado = (T) ServiceLocator.instance().get(jndiName);
        } catch (NamingException e) {
            Logger.getLogger(AbstractDelegate.class.getName()).log(Level.SEVERE, e.toString());
        }
        return delegado;
    }
}
