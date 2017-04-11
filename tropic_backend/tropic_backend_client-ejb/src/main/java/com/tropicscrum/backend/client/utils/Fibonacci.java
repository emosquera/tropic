/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.utils;

import com.tropicscrum.backend.client.model.SprintVelocity;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Edgar Mosquera
 */
public class Fibonacci {    
    
    private Collection<SprintVelocity> sprintVelocitys;

    public Collection<SprintVelocity> getSprintVelocitys() {
        SprintVelocity sprintVelocity = new SprintVelocity();
        sprintVelocity.setPoint(0.5);
        sprintVelocity.setHours(0.5);
                
        sprintVelocitys = new ArrayList<>();
        sprintVelocitys.add(sprintVelocity);
        
        for (int i = 2; i<=8; i++) {
            SprintVelocity sv = new SprintVelocity();
            sv.setPoint((double) fibonacci(i));
            switch (sv.getPoint().intValue()){
                case 1:
                    sv.setHours(1.0);
                    break;
                case 2:
                    sv.setHours(2.0);
                    break;
                case 3:
                    sv.setHours(4.0);
                    break;
                case 5:
                    sv.setHours(8.0);
                    break;
                case 8:
                    sv.setHours(12.0);
                    break;
                case 13:
                    sv.setHours(16.0);
                    break;
                case 21:
                    sv.setHours(24.0);
                    break;
                default:
                    sv.setHours((double) i);
                    break;
            }
            sprintVelocitys.add(sv);
        }
        return sprintVelocitys;
    }

    public void setSprintVelocitys(Collection<SprintVelocity> sprintVelocitys) {
        this.sprintVelocitys = sprintVelocitys;
    }
    
    public int fibonacci(int n) {
        if (n > 1) {
            return fibonacci(n - 1) + fibonacci(n - 2);  //función recursiva
        } else if (n == 1) {  // caso base
            return 1;
        } else if (n == 0) {  // caso base
            return 0;
        } else { //error
            System.out.println("Debes ingresar un tamaño mayor o igual a 1");
            return -1;
        }
    }
}
