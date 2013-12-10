package ca.ulaval.glo4003.domain.match;

@SuppressWarnings("serial")
// will never be used for serialisation
public class SectionNotFoundException extends RuntimeException {

    public SectionNotFoundException(String message) {
        super(message);
    }
}
