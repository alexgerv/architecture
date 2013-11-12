package ca.ulaval.glo4003.domain.match;

public class NoAvailableTicketsException extends RuntimeException {

    private static final long serialVersionUID = -4233365570079966109L;

    public NoAvailableTicketsException(String message) {
        super(message);
    }

}
