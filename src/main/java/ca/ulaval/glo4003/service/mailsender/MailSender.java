package ca.ulaval.glo4003.service.mailsender;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.ulaval.glo4003.domain.payment.IMailSender;

public class MailSender implements IMailSender {

    private static final String DEFAULT_SENDER = "userglo4003@gmail.com";
    private static final String PERSONAL_SENDER = "uTickets";
    private static final String DEFAULT_SUBJECT = "Transaction";
    private static final String DEFAULT_SIGNATURE_ID = "signatureLogo";
    private static final String DEFAULT_SIGNATURE_LOGO = "src/main/webapp/resources/logo.png";
    private static final String MESSAGE_TEMPLATE = "<h3>Thanks for buying!</h3><p>Your confirmation number is: %d</p><img src='cid:"
                                                   + DEFAULT_SIGNATURE_ID + "'>";

    @Inject
    JavaMailSenderImpl mailServer;

    Logger logger = LogManager.getLogger("errorLogger");

    MimeMessageBuilder mimeMessageBuilder;

    public MailSender(MimeMessageBuilder mimeMessageBuilder) {
        this.mimeMessageBuilder = mimeMessageBuilder;
    }

    public void sendPurchaseConfirmation(long confirmationNumber) {
        String emailAddress = SecurityContextHolder.getContext().getAuthentication().getName();

        MimeMessage transactionMessage = mimeMessageBuilder.setDefaultSender(DEFAULT_SENDER)
                                                           .setPersonalSender(PERSONAL_SENDER)
                                                           .setDestination(emailAddress)
                                                           .setSubject(DEFAULT_SUBJECT)
                                                           .setBody(String.format(MESSAGE_TEMPLATE, confirmationNumber))
                                                           .setSignatureID(DEFAULT_SIGNATURE_ID)
                                                           .setSignatureLogo(DEFAULT_SIGNATURE_LOGO)
                                                           .build(mailServer);

        sendEmail(transactionMessage);
    }

    private void sendEmail(MimeMessage message) {
        // Normally, we would create a thread here to send the mail
        try {
            mailServer.send(message);
        } catch (MailException ex) {
            logger.info(ex.getMessage());
        }
    }

    // For test purpose only
    protected MailSender(JavaMailSenderImpl mailServer, MimeMessageBuilder mimeMessageBuilder, Logger logger) {
        this.mailServer = mailServer;
        this.mimeMessageBuilder = mimeMessageBuilder;
        this.logger = logger;
    }
}
