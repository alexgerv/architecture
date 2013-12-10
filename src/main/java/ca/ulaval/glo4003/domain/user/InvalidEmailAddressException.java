package ca.ulaval.glo4003.domain.user;

@SuppressWarnings("serial")
// will never be used for serialisation
public class InvalidEmailAddressException extends RuntimeException {

    public InvalidEmailAddressException(String message) {
        super(message);
    }
}
