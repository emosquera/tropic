/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.utils;

import com.tropicscrum.frontend.utils.facade.Predicate;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Edgar Mosquera
 */
public class FilterList {

    public static <T> Collection<T> filter(Collection<T> col, Predicate<T> predicate) {
        Collection<T> result = new ArrayList<>();
        col.stream().filter((element) -> (predicate.apply(element))).forEachOrdered((element) -> {
            result.add(element);
        });
        return result;
    }
}
