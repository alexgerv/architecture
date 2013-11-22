package ca.ulaval.glo4003.service;

import javax.inject.Inject;
import javax.management.Notification;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.repository.UserRepository;
import ca.ulaval.glo4003.domain.user.User;

public aspect SendEmailOnTransactionAspect {
    
    @Inject
    JavaMailSenderImpl mailSender;
    
    @Inject
    UserRepository userRepository;

    protected pointcut ticketPurchase() :
        execution(* *..Match.buy*(..));
    
    after() returning : ticketPurchase() {
         User currentUser = userRepository.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
         sendEmailTo(currentUser.getEmailAddress());
    }
    
    private void sendEmailTo(String emailAddress) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("userglo4003@gmail.com");
        msg.setTo(emailAddress);
        msg.setSubject("Transaction");
        msg.setText("wow such email very match so working many transactions wow");
        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
	
}
