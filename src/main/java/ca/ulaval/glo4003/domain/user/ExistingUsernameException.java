package ca.ulaval.glo4003.domain.user;

@SuppressWarnings("serial")
// The serial is not used
public class ExistingUsernameException extends RuntimeException {

    public ExistingUsernameException(String message) {
        super(message);
    }

}
