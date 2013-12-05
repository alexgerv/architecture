package ca.ulaval.glo4003.domain.user;

@SuppressWarnings("serial") //will never be used for serialisation
public class ExistingUsernameException extends RuntimeException {

    public ExistingUsernameException(String message) {
        super(message);
    }

}
