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
    private static final String A_TEXT = "aText";

    private static final long A_TRANSACTION_NUMBER = 999999;

    private static final String MAIL_MESSAGE_WITH_TRANSACTION_NUMBER = String.format("Thanks for buying!\nYour confirmation number is: %d",
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
        
        Mockito.when(mimeMessageBuilder.setDefaultSender(EMAIL_ADDRESS)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setPersonalSender(EMAIL_ADDRESS)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setDestination(EMAIL_ADDRESS)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setSubject(A_TEXT)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setBody(A_TEXT)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setSignatureID(A_TEXT)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setSignatureLogo(A_TEXT)).thenReturn(mimeMessageBuilder);
    }

    @Test
    public void whenSendingAnEmailTheMailServerIsAskedWithRightMimeMessage() {
        Mockito.when(mimeMessageBuilder.build(mailServer)).thenReturn(mimeMessage);

        mailSender.sendPurchaseConfirmation(A_TRANSACTION_NUMBER);

        verify(mailServer).send(mimeMessage);
    }

    @Test
    public void whenSendingAnEmailTheRightBodyIsAdded() {
        mailSender.sendPurchaseConfirmation(A_TRANSACTION_NUMBER);

        verify(mimeMessageBuilder).setBody(MAIL_MESSAGE_WITH_TRANSACTION_NUMBER);
    }
}
