package ca.ulaval.glo4003.domain.match;

@SuppressWarnings("serial")
// Will never be used
public class UnavailableTicketException extends RuntimeException {

    public UnavailableTicketException(String message) {
        super(message);
    }

}
