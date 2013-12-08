package ca.ulaval.glo4003.service.mailsender;

import javax.inject.Inject;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserRepository;

public class MailSender {

    @Inject
    JavaMailSenderImpl mailServer;

    @Inject
    UserRepository userRepository;

    public void sendPurchaseConfirmation() {
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
            mailServer.send(msg);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
