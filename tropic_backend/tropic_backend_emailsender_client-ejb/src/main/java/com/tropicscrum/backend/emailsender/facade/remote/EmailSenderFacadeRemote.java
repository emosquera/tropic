/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.emailsender.facade.remote;

import javax.ejb.Remote;

/**
 *
 * @author syslife02
 */
@Remote
public interface EmailSenderFacadeRemote {
    public final String JNDI_REMOTE_NAME = "ejb/emailSenderFacadeRemote";
    
    public void createAndSendEmail(String emailReceiver, String subject, String content, boolean isHtml);
}
