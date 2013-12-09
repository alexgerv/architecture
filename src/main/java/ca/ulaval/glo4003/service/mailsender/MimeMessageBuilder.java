package ca.ulaval.glo4003.service.mailsender;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MimeMessageBuilder {

    private String defaultSender;
    private String personalSender;
    private String destination;
    private String subject;
    private String body;
    private String signatureID;
    private String signatureLogo;

    public MimeMessageBuilder setDefaultSender(String defaultSender) {
        this.defaultSender = defaultSender;
        return this;
    }

    public MimeMessageBuilder setPersonalSender(String personalSender) {
        this.personalSender = personalSender;
        return this;
    }

    public MimeMessageBuilder setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public MimeMessageBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MimeMessageBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public MimeMessageBuilder setSignatureLogo(String signatureLogo) {
        this.signatureLogo = signatureLogo;
        return this;
    }
    
    public MimeMessageBuilder setSignatureID(String signatureID) {
        this.signatureID = signatureID;
        return this;
    }

    public MimeMessage build(JavaMailSenderImpl mailServer) {
        MimeMessage message = mailServer.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(defaultSender, personalSender);
            helper.setTo(destination);
            helper.setSubject(subject);
            helper.setText(body, true);

            FileSystemResource file = new FileSystemResource(signatureLogo);
            helper.addInline(signatureID, file);

        } catch (MessagingException e) {
            throw new MailParseException(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return message;
    }
}
