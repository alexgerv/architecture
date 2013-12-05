package ca.ulaval.glo4003.domain.match;

@SuppressWarnings("serial") //will never be used for serialisation
public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException(String message) {
        super(message);
    }

}
