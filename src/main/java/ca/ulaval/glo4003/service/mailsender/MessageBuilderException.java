package ca.ulaval.glo4003.service.mailsender;


@SuppressWarnings("serial") //will never be used for serialisation
public class MessageBuilderException extends RuntimeException {
    
    public MessageBuilderException(String message){
        super(message);
    }
}
