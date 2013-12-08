package ca.ulaval.glo4003.service.mailsender;

import org.springframework.mail.SimpleMailMessage;

public class SimpleMailMessageBuilder {

    private SimpleMailMessage currentMessage = new SimpleMailMessage();

    public void createSender(String sender) {
        currentMessage.setFrom(sender);
    }

    public void createDestination(String destination) {
        currentMessage.setTo(destination);
    }

    public void createSubject(String subject) {
        currentMessage.setSubject(subject);
    }

    public void createBody(String body) {
        currentMessage.setText(body);

    }

    public SimpleMailMessage build() {
        SimpleMailMessage messageToSend = currentMessage;
        currentMessage = new SimpleMailMessage();
        return messageToSend;
    }

}
