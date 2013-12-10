package ca.ulaval.glo4003.domain.match;

@SuppressWarnings("serial")
// will never be used for serialisation
public class UnavailableTicketException extends RuntimeException {

    public UnavailableTicketException(String message) {
        super(message);
    }

}
