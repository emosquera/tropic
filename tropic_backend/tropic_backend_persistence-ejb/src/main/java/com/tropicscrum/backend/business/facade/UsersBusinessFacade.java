/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.business.facade;

import com.tropicscrum.backend.client.exceptions.LoginException;
import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.UsersFacadeRemote;
import com.tropicscrum.backend.client.model.User;
import com.tropicscrum.backend.emailsender.facade.remote.EmailSenderFacadeRemote;
import com.tropicscrum.backend.persistence.exceptions.InvalidCredentials;
import com.tropicscrum.backend.persistence.exceptions.OldVersionException;
import com.tropicscrum.backend.persistence.facade.UsersFacadeLocal;
import com.tropicscrum.base.facade.ServiceLocatorDelegate;
import java.util.Collection;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author Edgar Mosquera
 */
@Stateless(name = "usersFacadeRemote", mappedName = UsersFacadeRemote.JNDI_REMOTE_NAME)
@Remote(UsersFacadeRemote.class)
public class UsersBusinessFacade implements UsersFacadeRemote {

    @Inject
    UsersFacadeLocal usersFacadeLocal;
    
    EmailSenderFacadeRemote emailSenderFacadeRemote = new ServiceLocatorDelegate<EmailSenderFacadeRemote>().getService(EmailSenderFacadeRemote.JNDI_REMOTE_NAME);

    @Override
    public User create(User user) {
        try {
            usersFacadeLocal.create(user);
            return user;
        } catch (Exception ex) {
            return new User();
        }
    }

    @Override
    public User edit(User user) throws UpdateException {
        try {
            usersFacadeLocal.edit(user);
        } catch (OldVersionException e) {
            throw new UpdateException("No se puede actualizar el Usuario. Este ha sido modificado en otra sesion. Se muestra el Usuario actualizado");
        }
        
        return user;
    }

    @Override
    public void remove(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User find(Object id) {
        try {
            return usersFacadeLocal.find(id);
        } catch (NoResultException ex) {
            return new User();
        }
    }

    @Override
    public Collection<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<User> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public User login(String email, String password) throws LoginException {
        try {
            User user = usersFacadeLocal.findByEmail(email);            
            if (!user.getConfirmed()) {
                throw new LoginException("Debes activar tu usuario usando el link en el correo que enviamos a tu cuenta");
            }            
            if (user.getPassword().equals(password)) {
                return user;
            } else {
              throw new LoginException("Password icorrecto");
            }                        
        } catch (InvalidCredentials e) {
            throw new LoginException("Email no se encuentra registrado");
        } 
    }

    @Override
    public Boolean emailExist(String email) {
        return usersFacadeLocal.emailExist(email);
    }

    @Override
    public void sendConfirmEmail(User user, String emailContent) {
        emailSenderFacadeRemote.createAndSendEmail(user.getEmail(), "Tropic Scrum. Activacion de Cuenta", emailContent, true);     
    }

    @Override
    public Collection<User> getAllContainsEmailExceptYou(User you, String email) {
        return usersFacadeLocal.findOtherByEmail(you, email);
    }

    @Override
    public User findWithProjects(Object id) {
        try {
            User u = usersFacadeLocal.find(id);
            u.getProjects().size();
            return u;
        } catch (NoResultException ex) {
            return new User();
        }
    }

    @Override
    public Collection<User> filterByEmail(String email) {
        return usersFacadeLocal.filterByEmail(email);
    }
}
