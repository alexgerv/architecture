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
    private static final long A_TRANSACTION_NUMBER = 999999;

    private static final String A_DESTINATION = "userglo4003@gmail.com";
    private static final String A_DEFAULT_SENDER = "userglo4003@gmail.com";
    private static final String A_PERSONAL_SENDER = "uTickets";
    private static final String A_SUBJECT = "Transaction";
    private static final String A_SIGNATURE_ID = "signatureLogo";
    private static final String A_SIGNATURE_LOGO = "src/main/webapp/resources/logo.png";
    private static final String A_MESSAGE_TEMPLATE =
                                                     "<h3>Thanks for buying!</h3><p>Your confirmation number is: %d</p><img src='cid:"
                                                             + A_SIGNATURE_ID + "'>";
    private static final String MAIL_MESSAGE_WITH_TRANSACTION_NUMBER =
                                                                       String.format("<h3>Thanks for buying!</h3><p>Your confirmation number is: %d</p><img src='cid:"
                                                                                             + A_SIGNATURE_ID + "'>",
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
        Mockito.when(authentication.getName()).thenReturn(A_DESTINATION);

        Mockito.when(mimeMessageBuilder.setDefaultSender(A_DEFAULT_SENDER)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setPersonalSender(A_PERSONAL_SENDER)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setDestination(A_DESTINATION)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setSubject(A_SUBJECT)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setBody(String.format(A_MESSAGE_TEMPLATE, A_TRANSACTION_NUMBER)))
               .thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setSignatureID(A_SIGNATURE_ID)).thenReturn(mimeMessageBuilder);
        Mockito.when(mimeMessageBuilder.setSignatureLogo(A_SIGNATURE_LOGO)).thenReturn(mimeMessageBuilder);
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
