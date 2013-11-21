package ca.ulaval.glo4003.service;

import javax.inject.Inject;
import javax.management.Notification;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import ca.ulaval.glo4003.domain.match.Match;

public aspect SendEmailOnTransactionAspect {
    
    @Inject
    JavaMailSenderImpl mailSender;

    protected pointcut ticketPurchase() :
        execution(* *..Match.buy*(..));
    
    after() returning : ticketPurchase() {
        sendEmailTo("userglo4003@gmail.com");
        
    }
    
    private void sendEmailTo(String emailAddress) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("userglo4003@gmail.com");
        msg.setTo(emailAddress);
        msg.setSubject("Transaction");
        msg.setText("wow such email very match so working many transactions wow");
        try {
            mailSender.send(msg);
            System.out.println("send email");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
	
}
