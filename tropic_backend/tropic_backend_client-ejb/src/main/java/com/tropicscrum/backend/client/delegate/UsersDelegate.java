/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.delegate;

import com.tropicscrum.backend.client.dto.UserDTO;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.base.locator.AbstractDelegate;
import java.util.List;

/**
 *
 * @author syslife02
 */
public class UsersDelegate extends AbstractDelegate<UsersFacadeRemote> implements UsersFacadeRemote {

    @Override
    public UserDTO create(UserDTO userDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).create(userDTO);
    }

    @Override
    public UserDTO edit(UserDTO userDTO) {
        return this.getDelegado(JNDI_REMOTE_NAME).edit(userDTO);
    }

    @Override
    public void remove(UserDTO userDTO) {
        this.getDelegado(JNDI_REMOTE_NAME).remove(userDTO);
    }

    @Override
    public UserDTO find(Object id) {
        return this.getDelegado(JNDI_REMOTE_NAME).find(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return this.getDelegado(JNDI_REMOTE_NAME).findAll();
    }

    @Override
    public List<UserDTO> findRange(int[] range) {
        return this.getDelegado(JNDI_REMOTE_NAME).findRange(range);
    }

    @Override
    public int count() {
        return this.getDelegado(JNDI_REMOTE_NAME).count();
    }

    @Override
    public boolean isAlive() {
        return this.getDelegado(JNDI_REMOTE_NAME).isAlive();
    }
    
}
