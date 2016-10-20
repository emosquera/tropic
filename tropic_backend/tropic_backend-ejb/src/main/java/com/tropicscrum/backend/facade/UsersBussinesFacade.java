/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.facade;

import com.tropicscrum.backend.client.dto.UserDTO;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.model.Users;
import com.tropicscrum.backend.sql.facade.UsersFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author syslife02
 */
@Stateless(name = "TerminalBussinesFacade", mappedName = UsersFacadeRemote.JNDI_REMOTE_NAME)
@Remote(UsersFacadeRemote.class)
public class UsersBussinesFacade implements UsersFacadeRemote {
    
    @EJB
    UsersFacadeLocal usersFacadeLocal;

    @Override
    public UserDTO create(UserDTO userDTO) {
        Users user = new Users(userDTO);
        usersFacadeLocal.create(user);
        return user;
    }

    @Override
    public UserDTO edit(UserDTO userDTO) {
        Users user = new Users(userDTO);
        usersFacadeLocal.edit(user);
        return user;
    }

    @Override
    public void remove(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO find(Object id) {
        Users user = usersFacadeLocal.find(id);
        return user;
    }

    @Override
    public List<UserDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserDTO> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
