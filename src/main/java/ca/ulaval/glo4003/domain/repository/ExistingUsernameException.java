package ca.ulaval.glo4003.domain.repository;


public class ExistingUsernameException extends RuntimeException {
    private static final long serialVersionUID = 5600571496772643477L;

    public ExistingUsernameException(String message) {
        super(message);
    }
    
}
