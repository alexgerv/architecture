package ca.ulaval.glo4003.service.mailsender;

import static org.mockito.Mockito.verify;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class MailSenderTest {

    private static final String EMAIL_ADDRESS = "userglo4003@gmail.com";
    private static final String EMPTY_STRING = "";

    private static final long A_TRANSACTION_NUMBER = 999999;

    private static final String MAIL_MESSAGE_WITH_TRANSACTION_NUMBER =
                                                                       String.format("Thanks for buying!\nYour confirmation number is: %d",
                                                                                     A_TRANSACTION_NUMBER);
    @Mock
    SecurityContext securityContext;

    @Mock
    private JavaMailSenderImpl mailServer;
    @Mock
    private MimeMessageBuilder mimeMessageBuilder;
    @Mock
    private MimeMessage mimeMessage;
    @Mock
    private Authentication authentication;
    @Mock
    private Logger logger;

    private MailSender mailSender;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mailSender = new MailSender(mailServer, mimeMessageBuilder, logger);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getName()).thenReturn(EMAIL_ADDRESS);
    }

    @Test
    public void whenSendingAnEmailTheMailServerIsAskedWithRightMimeMessage() {
        Mockito.when(mimeMessageBuilder.setDefaultSender(EMAIL_ADDRESS).setPersonalSender(EMAIL_ADDRESS)
                                       .setDestination(EMAIL_ADDRESS).setSubject(EMPTY_STRING)
                                       .setBody(EMPTY_STRING).setSignatureID(EMPTY_STRING)
                                       .setSignatureLogo(EMPTY_STRING).build(mailServer)).thenReturn(mimeMessage);

        mailSender.sendPurchaseConfirmation(A_TRANSACTION_NUMBER);

        verify(mailServer).send(mimeMessage);
    }

    @Test
    public void whenSendingAnEmailTheRightBodyIsAdded() {

        mailSender.sendPurchaseConfirmation(A_TRANSACTION_NUMBER);

        verify(mimeMessageBuilder).setBody(MAIL_MESSAGE_WITH_TRANSACTION_NUMBER);
    }
}
