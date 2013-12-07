package ca.ulaval.glo4003.service.mailsender;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserRepository;

@Aspect
public class MailSenderAspect {

    final static String MESSAGE_SUBJECT = "Your payment has successfully completed";
    final static String LOGO_PATH = "src/main/webapp/resources/logo.png";
    final static String BODY_BEGIN = "<html><body>";
    final static String BODY_END = "</body></html>";
    final static String BODY_TITLE = "<h3>Congratulations!</h3>";
    final static String BODY_MESSAGE = "<p>Please print this message to receive your tickets.</p>";
    final static String BODY_SIGNATURE = "<br><br><img src='cid:logo'/>";

    @Inject
    JavaMailSenderImpl mailSender;

    @Inject
    UserRepository userRepository;

    @SuppressWarnings("unchecked")
    @AfterReturning(pointcut = "execution(* *..TransactionManager.processTransaction*(..))",
                    returning = "transactionID")
    public void sendPurchaseConfirmation(JoinPoint joinpoint, long transactionID) throws MessagingException {
        User currentUser = userRepository.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        String textMessage = this.messageTemplate((List<Ticket>) joinpoint.getArgs()[2], transactionID);

        sendEmailTo(currentUser.getEmailAddress(), textMessage);
    }

    private void sendEmailTo(final String emailAddress, final String textMessage) throws MessagingException {

        mailSender.send(new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws MessagingException {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                FileSystemResource file = new FileSystemResource(new File(LOGO_PATH));

                message.setTo(emailAddress);
                message.setSubject(MESSAGE_SUBJECT);
                message.setText(textMessage, true);
                message.addInline("logo", file);
            }
        });
    }

    private String messageTemplate(List<Ticket> tickets, long transaction) {

        String body = "";

        body += BODY_BEGIN;
        body += BODY_TITLE;
        body += BODY_MESSAGE;

        body += "<table border=" + "1" + "><tr><th>Ticket ID</th><th>Sport</th><th>Venue</th><th>Date</th></tr>";

        for (Ticket ticket : tickets) {
            body +=
                    "<tr><td>" + ticket.getID() + "</td><td>" + ticket.getSport() + "</td><td>" + ticket.getVenue()
                            + "</td><td>" + ticket.getDate() + "</td></tr>";
        }

        body += "</table>";
        body += BODY_SIGNATURE;
        body += BODY_END;

        return body;
    }
}