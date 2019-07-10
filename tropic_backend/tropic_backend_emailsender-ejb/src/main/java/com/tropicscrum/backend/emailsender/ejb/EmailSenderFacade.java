/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.emailsender.ejb;

import com.tropicscrum.backend.emailsender.facade.remote.EmailSenderFacadeRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author martin
 */
@Stateless(name = "emailSenderFacadeRemote", mappedName = EmailSenderFacadeRemote.JNDI_REMOTE_NAME)
@Remote(EmailSenderFacadeRemote.class)

public class EmailSenderFacade implements EmailSenderFacadeRemote {

    @Resource(name = "java:/mail/gmail")
//    @Resource(name = "mail/gmail")
    private Session mailSession;

    
    @Asynchronous
    @Override
    public void createAndSendEmail(String emailReceiver, String subject, String content, boolean isHtml) {
        try {
            Message msg = new MimeMessage(mailSession);
            
            msg.setSubject(subject);
            if (isHtml) {
                msg.setContent(content, "text/html");
            } else {
                msg.setText(content);
            }
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailReceiver));
            msg.setFrom();
            
            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailSenderFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
