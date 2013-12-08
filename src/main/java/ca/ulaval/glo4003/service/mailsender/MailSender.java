package ca.ulaval.glo4003.service.mailsender;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class MailSender {

    private static final String DEFAULT_SENDER = "userglo4003@gmail.com";
    private static final String DEFAULT_SUBJECT = "Transaction";
    private static final String MESSAGE_TEMPLATE = "Thanks for buying!\nYour confirmation number is: %d";

    @Inject
    JavaMailSenderImpl mailServer;

    Logger logger = LogManager.getLogger("errorLogger");

    SimpleMailMessageBuilder simpleMailMessageBuilder;

    public MailSender(SimpleMailMessageBuilder simpleMailMessageBuilder) {
        this.simpleMailMessageBuilder = simpleMailMessageBuilder;
    }

    public void sendPurchaseConfirmation(long confirmationNumber) {
        String emailAddress = SecurityContextHolder.getContext().getAuthentication().getName();

        simpleMailMessageBuilder.createSender(DEFAULT_SENDER);
        simpleMailMessageBuilder.createDestination(emailAddress);
        simpleMailMessageBuilder.createSubject(DEFAULT_SUBJECT);
        simpleMailMessageBuilder.createBody(String.format(MESSAGE_TEMPLATE, confirmationNumber));
        SimpleMailMessage transactionMessage = simpleMailMessageBuilder.build();

        sendEmail(transactionMessage);
    }

    private void sendEmail(SimpleMailMessage message) {
        try {
            mailServer.send(message);
        } catch (MailException ex) {
            logger.info(ex.getMessage());
        }
    }

    // For test purpose only
    protected MailSender(JavaMailSenderImpl mailServer, SimpleMailMessageBuilder simpleMailMessageBuilder, Logger logger) {
        this.mailServer = mailServer;
        this.simpleMailMessageBuilder = simpleMailMessageBuilder;
        this.logger = logger;
    }
}
