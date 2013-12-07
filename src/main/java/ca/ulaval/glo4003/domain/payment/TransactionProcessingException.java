package ca.ulaval.glo4003.domain.payment;


@SuppressWarnings("serial") //Serial number will never be used
public class TransactionProcessingException extends RuntimeException {
    public TransactionProcessingException(String message) {
        super(message);
    }
}
