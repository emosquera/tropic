/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.base.business;

/**
 *
 * @author syslife02
 * @param <E>
 */
public interface FacadeServiceLocatorDelegate<E> {
    E getService(String serviceName);
}
