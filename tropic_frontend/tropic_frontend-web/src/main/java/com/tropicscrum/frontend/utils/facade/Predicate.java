/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.utils.facade;

/**
 *
 * @author Edgar Mosquera
 * @param <T>
 */
public interface Predicate<T> {
    public boolean apply(T type);
}
