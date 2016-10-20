/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.facade;

import com.tropicscrum.backend.client.dto.UserDTO;
import com.tropicscrum.base.locator.ServiceVerifier;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */
@Remote
public interface UsersFacadeRemote extends ServiceVerifier {
    public final String JNDI_REMOTE_NAME = "ejb/usersFacadeRemote";
    
    UserDTO create(UserDTO userDTO);

    UserDTO edit(UserDTO userDTO);

    void remove(UserDTO userDTO);

    UserDTO find(Object id);

    List<UserDTO> findAll();

    List<UserDTO> findRange(int[] range);

    int count();
}