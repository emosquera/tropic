/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Edgar Mosquera
 */
public class SortMapByValue {

    /*
	 * Sort a map according to values.
	 * 
	 * @param <K> the key of the map.
	 * 
	 * @param <V> the value to sort according to.
	 * 
	 * @param mapToSort the map to sort.
	 * 
	 * @return a map sorted on the values.
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(final Map<K, V> mapToSort) {
        List<Map.Entry<K, V>> entries = new ArrayList<>(mapToSort.size());

        entries.addAll(mapToSort.entrySet());

        // Sorts the specified list according to the order induced by the specified comparator
        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(final Map.Entry<K, V> entry1, final Map.Entry<K, V> entry2) {
                // Compares this object with the specified object for order
                return entry1.getValue().compareTo(entry2.getValue());
            }
        });

        Map<K, V> sortedMap = new LinkedHashMap<>();

        // The Map.entrySet method returns a collection-view of the map
        for (Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> reverseSortMapByValue(final Map<K, V> mapToSort) {
        List<Map.Entry<K, V>> entries = new ArrayList<>(mapToSort.size());

        entries.addAll(mapToSort.entrySet());

        // Sorts the specified list according to the order induced by the specified comparator
        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(final Map.Entry<K, V> entry1, final Map.Entry<K, V> entry2) {
                // Compares this object with the specified object for order
                return -entry1.getValue().compareTo(entry2.getValue());
            }
        });

        Map<K, V> sortedMap = new LinkedHashMap<>();

        // The Map.entrySet method returns a collection-view of the map
        for (Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
