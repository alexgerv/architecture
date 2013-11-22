package ca.ulaval.glo4003.domain.user;


@SuppressWarnings("serial") // will never be used
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
