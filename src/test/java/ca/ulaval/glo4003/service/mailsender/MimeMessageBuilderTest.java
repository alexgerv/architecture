package ca.ulaval.glo4003.service.mailsender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doThrow;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MimeMessageBuilderTest {

    private static final String A_SIGNATURE_LOGO = "";
    private static final String A_SIGNATURE_ID = "";
    
    private static final String INVALID_DESTINATION = "é";
    
    private static final String A_DEFAULT_SENDER = "userglo4003@gmail.com";
    private static final String A_PERSONAL_SENDER = "userglo4003";
    private static final String A_DESTINATION = "dest@gmail.com";
    private static final String A_SUBJECT = "Testing";
    private static final String A_BODY = "Message body";

    private static final String OTHER_DEFAULT_SENDER = "new@gmail.com";
    private static final String OTHER_PERSONAL_SENDER = "new";
    private static final String OTHER_DESTINATION = "dest2@gmail.com";
    private static final String OTHER_SUBJECT = "Testing seccond message";
    private static final String OTHER_BODY = "Message body for other message";
    
    private JavaMailSenderImpl mailServer = new JavaMailSenderImpl();

    private MimeMessageBuilder mimeMessageBuilder;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        mimeMessageBuilder = new MimeMessageBuilder();

        mimeMessageBuilder.setDefaultSender(A_DEFAULT_SENDER);
        mimeMessageBuilder.setPersonalSender(A_PERSONAL_SENDER);
        mimeMessageBuilder.setDestination(A_DESTINATION);
        mimeMessageBuilder.setSubject(A_SUBJECT);
        mimeMessageBuilder.setBody(A_BODY);
        mimeMessageBuilder.setSignatureID(A_SIGNATURE_ID);
        mimeMessageBuilder.setSignatureLogo(A_SIGNATURE_LOGO);
    }

    @Test(expected = MessageBuilderException.class)
    public void aMessageBuilderExceptionIsThrownWhenAnEmailAddressIsInvalid() {
        mimeMessageBuilder.setDestination(INVALID_DESTINATION);
        
        mimeMessageBuilder.build(mailServer);
    }
    
    @Test
    public void canBuildTwoDifferentMimeMessage() throws MessagingException {
        
        MimeMessage firstMessage = mimeMessageBuilder.build(mailServer);

        mimeMessageBuilder.setDefaultSender(OTHER_DEFAULT_SENDER);
        mimeMessageBuilder.setPersonalSender(OTHER_PERSONAL_SENDER);
        mimeMessageBuilder.setDestination(OTHER_DESTINATION);
        mimeMessageBuilder.setSubject(OTHER_SUBJECT);
        mimeMessageBuilder.setBody(OTHER_BODY);
        mimeMessageBuilder.setSignatureID(A_SIGNATURE_ID);
        mimeMessageBuilder.setSignatureLogo(A_SIGNATURE_LOGO);

        MimeMessage secondMessage = mimeMessageBuilder.build(mailServer);

        assertNotSame(firstMessage, secondMessage);
    }
}
