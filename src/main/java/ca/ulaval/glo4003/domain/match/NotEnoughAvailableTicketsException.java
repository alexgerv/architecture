package ca.ulaval.glo4003.domain.match;

@SuppressWarnings("serial")
// will never be used for serialisation
public class NotEnoughAvailableTicketsException extends RuntimeException {

    public NotEnoughAvailableTicketsException(String message) {
        super(message);
    }

}