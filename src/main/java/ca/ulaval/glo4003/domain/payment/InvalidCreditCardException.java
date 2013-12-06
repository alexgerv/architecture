package ca.ulaval.glo4003.domain.payment;


@SuppressWarnings("serial") //will never be used for serialisation
public class InvalidCreditCardException extends Exception {
    public InvalidCreditCardException(String message) {
        super(message);
    }
}
