/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.persistence.facade;

import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.persistence.exceptions.InvalidCredentials;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author syslife02
 */
@Local
public interface UsersFacadeLocal {

    void create(User users);

    void edit(User users) throws OldVersionException;

    void remove(User users);

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
    User findByEmail(String email) throws InvalidCredentials ;
    
    Boolean emailExist(String email);
    
    List<User> findOtherByEmail(User you, String email);    
}
