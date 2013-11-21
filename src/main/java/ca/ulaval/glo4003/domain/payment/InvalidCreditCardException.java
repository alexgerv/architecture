package ca.ulaval.glo4003.domain.payment;


@SuppressWarnings("serial") // Serial will never be used
public class InvalidCreditCardException extends Exception {
    public InvalidCreditCardException(String message) {
        super(message);
    }
}
