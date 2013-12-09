package ca.ulaval.glo4003.service.mailsender;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MimeMessageBuilderTest {

    private static final String A_SENDER = "userglo4003@gmail.com";
    private static final String A_DESTINATION = "dest@gmail.com";
    private static final String A_SUBJECT = "Testing";
    private static final String A_BODY = "Message body";

    private static final String OTHER_SENDER = "new@gmail.com";
    private static final String OTHER_DESTINATION = "dest2@gmail.com";
    private static final String OTHER_SUBJECT = "Testing seccond message";
    private static final String OTHER_BODY = "Message body for other message";
    
    @Mock
    private JavaMailSenderImpl mailServer;

    private MimeMessageBuilder mimeMessageBuilder;

    @Before
    public void setUp() {
        mimeMessageBuilder = new MimeMessageBuilder();

        mimeMessageBuilder.setPersonalSender(A_SENDER);
        mimeMessageBuilder.setDestination(A_DESTINATION);
        mimeMessageBuilder.setSubject(A_SUBJECT);
        mimeMessageBuilder.setBody(A_BODY);
    }

    @Ignore
    @Test
    public void canBuildMailWithExpectedSender() {
        MimeMessage message = mimeMessageBuilder.build(mailServer);

//        assertTrue(message.getFrom() == A_SENDER);
    }

    @Ignore
    @Test
    public void canBuildMailWithExpectedDestination() {
        MimeMessage message = mimeMessageBuilder.build(mailServer);

//        assertTrue(message.getTo()[0] == A_DESTINATION);
    }

    @Ignore
    @Test
    public void canBuildMailWithExpectedSubject() {
        MimeMessage message = mimeMessageBuilder.build(mailServer);

//        assertTrue(message.getSubject() == A_SUBJECT);
    }

    @Ignore
    @Test
    public void canBuildMailWithExpectedBody() {
        MimeMessage message = mimeMessageBuilder.build(mailServer);

//        assertTrue(message.getText() == A_BODY);
    }

    @Test
    public void canBuildTwoDifferentSimpleMessage() {
        MimeMessage firstMessage = mimeMessageBuilder.build(mailServer);

        mimeMessageBuilder.setPersonalSender(OTHER_SENDER);
        mimeMessageBuilder.setDestination(OTHER_DESTINATION);
        mimeMessageBuilder.setSubject(OTHER_SUBJECT);
        mimeMessageBuilder.setBody(OTHER_BODY);

        MimeMessage secondMessage = mimeMessageBuilder.build(mailServer);

        assertNotSame(firstMessage, secondMessage);
    }
}
