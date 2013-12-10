package ca.ulaval.glo4003.domain.match;

@SuppressWarnings("serial")
// will never be used for serialisation
public class NoAvailableTicketsException extends RuntimeException {

    public NoAvailableTicketsException(String message) {
        super(message);
    }

}
