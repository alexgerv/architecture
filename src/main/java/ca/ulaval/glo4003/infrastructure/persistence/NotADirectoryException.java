package ca.ulaval.glo4003.infrastructure.persistence;


@SuppressWarnings("serial") // will never be used
public class NotADirectoryException extends RuntimeException {

    public NotADirectoryException(String message) {
        super(message);
    }

}
