package ca.ulaval.glo4003.service.mailsender;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;

public class SimpleMailMessageBuilderTest {

    private static final String A_SENDER = "userglo4003@gmail.com";
    private static final String A_DESTINATION = "dest@gmail.com";
    private static final String A_SUBJECT = "Testing";
    private static final String A_BODY = "Message body";

    private static final String OTHER_SENDER = "new@gmail.com";
    private static final String OTHER_DESTINATION = "dest2@gmail.com";
    private static final String OTHER_SUBJECT = "Testing seccond message";
    private static final String OTHER_BODY = "Message body for other message";

    private SimpleMailMessageBuilder simpleMailMessageBuilder;

    @Before
    public void setUp() {
        simpleMailMessageBuilder = new SimpleMailMessageBuilder();

        simpleMailMessageBuilder.createSender(A_SENDER);
        simpleMailMessageBuilder.createDestination(A_DESTINATION);
        simpleMailMessageBuilder.createSubject(A_SUBJECT);
        simpleMailMessageBuilder.createBody(A_BODY);
    }

    @Test
    public void canBuildMailWithExpectedSender() {
        SimpleMailMessage message = simpleMailMessageBuilder.build();

        assertTrue(message.getFrom() == A_SENDER);
    }

    @Test
    public void canBuildMailWithExpectedDestination() {
        SimpleMailMessage message = simpleMailMessageBuilder.build();

        assertTrue(message.getTo()[0] == A_DESTINATION);
    }

    @Test
    public void canBuildMailWithExpectedSubject() {
        SimpleMailMessage message = simpleMailMessageBuilder.build();

        assertTrue(message.getSubject() == A_SUBJECT);
    }

    @Test
    public void canBuildMailWithExpectedBody() {
        SimpleMailMessage message = simpleMailMessageBuilder.build();

        assertTrue(message.getText() == A_BODY);
    }

    @Test
    public void canBuildTwoDifferentSimpleMessage() {
        SimpleMailMessage firstMessage = simpleMailMessageBuilder.build();

        simpleMailMessageBuilder.createSender(OTHER_SENDER);
        simpleMailMessageBuilder.createDestination(OTHER_DESTINATION);
        simpleMailMessageBuilder.createSubject(OTHER_SUBJECT);
        simpleMailMessageBuilder.createBody(OTHER_BODY);

        SimpleMailMessage secondMessage = simpleMailMessageBuilder.build();

        assertNotSame(firstMessage, secondMessage);
    }
}
