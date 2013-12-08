package ca.ulaval.glo4003.service.mailsender;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class MailSenderTest {

    private static final String EMAIL_ADDRESS = "userglo4003@gmail.com";

    private static final long A_TRANSACTION_NUMBER = 999999;

    private static final String MAIL_MESSAGE_WITH_TRANSACTION_NUMBER = String.format("Thanks for buying!\nYour confirmation number is: %d",
                                                                                     A_TRANSACTION_NUMBER);
    @Mock
    SecurityContext securityContext;

    @Mock
    private JavaMailSenderImpl mailServer;
    @Mock
    private SimpleMailMessageBuilder simpleMailMessageBuilder;
    @Mock
    private SimpleMailMessage simpleMailMessage;
    @Mock
    private Authentication authentication;

    private MailSender mailSender;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mailSender = new MailSender(mailServer, simpleMailMessageBuilder);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getName()).thenReturn(EMAIL_ADDRESS);
    }

    @Test
    public void whenSendingAnEmailTheMailServerIsAskedWithRightSimpleMessage() {
        Mockito.when(simpleMailMessageBuilder.build()).thenReturn(simpleMailMessage);
        mailSender.sendPurchaseConfirmation(A_TRANSACTION_NUMBER);

        verify(mailServer).send(simpleMailMessage);
    }

    @Test
    public void whenSendingAnEmailTheRightBodyIsAdded() {
        mailSender.sendPurchaseConfirmation(A_TRANSACTION_NUMBER);

        verify(simpleMailMessageBuilder).createBody(MAIL_MESSAGE_WITH_TRANSACTION_NUMBER);
    }
}
