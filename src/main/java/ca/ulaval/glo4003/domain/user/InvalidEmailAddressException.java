package ca.ulaval.glo4003.domain.user;

@SuppressWarnings("serial")
public class InvalidEmailAddressException extends RuntimeException {

    public InvalidEmailAddressException(String message) {
        super(message);
    }
}
