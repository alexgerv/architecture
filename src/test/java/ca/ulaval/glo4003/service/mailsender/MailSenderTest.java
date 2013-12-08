package ca.ulaval.glo4003.service.mailsender;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import ca.ulaval.glo4003.domain.user.UserRepository;

public class MailSenderTest {

    private static final long A_TRANSACTION_NUMBER = 999999;

    private static final String MAIL_MESSAGE_WITH_TRANSACTION_NUMBER = String.format("Thanks for buying!\nYour confirmation number is: %d",
                                                                                     A_TRANSACTION_NUMBER);

    @Mock
    private JavaMailSenderImpl mailServer;
    @Mock
    private SimpleMailMessageBuilder simpleMailMessageBuilder;
    @Mock
    private SimpleMailMessage simpleMailMessage;
    @Mock
    private UserRepository userRepository;

    private MailSender mailSender;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mailSender = new MailSender(mailServer, userRepository, simpleMailMessageBuilder);
    }

    @Test
    @Ignore
    public void whenSendingAnEmailTheMailServerIsAskedWithRightSimpleMessage() {
        Mockito.when(simpleMailMessageBuilder.build()).thenReturn(simpleMailMessage);
        mailSender.sendPurchaseConfirmation(A_TRANSACTION_NUMBER);

        verify(mailServer).send(simpleMailMessage);
    }

    @Test
    @Ignore
    public void whenSendingAnEmailTheRightBodyIsAdded() {
        mailSender.sendPurchaseConfirmation(A_TRANSACTION_NUMBER);

        verify(simpleMailMessageBuilder).createBody(MAIL_MESSAGE_WITH_TRANSACTION_NUMBER);
    }
}
