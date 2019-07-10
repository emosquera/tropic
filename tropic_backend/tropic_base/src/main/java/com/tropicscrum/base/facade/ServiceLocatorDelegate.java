/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.base.facade;

import com.tropicscrum.base.locator.AbstractDelegate;
import com.tropicscrum.base.business.FacadeServiceLocatorDelegate;

/**
 *
 * @author syslife02
 * @param <T>
 */
public class ServiceLocatorDelegate<T> extends AbstractDelegate<T> implements FacadeServiceLocatorDelegate<T> {

    @Override
    public T getService(String serviceName) {
        return this.getDelegado(serviceName);
    }
    
}
