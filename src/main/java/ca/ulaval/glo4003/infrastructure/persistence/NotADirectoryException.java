package ca.ulaval.glo4003.infrastructure.persistence;


@SuppressWarnings("serial") //will never be used for serialisation
public class NotADirectoryException extends RuntimeException {

    public NotADirectoryException(String message) {
        super(message);
    }

}
