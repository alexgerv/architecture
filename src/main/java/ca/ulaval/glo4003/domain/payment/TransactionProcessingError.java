package ca.ulaval.glo4003.domain.payment;


@SuppressWarnings("serial") //Serial number will never be used
public class TransactionProcessingError extends RuntimeException {
    public TransactionProcessingError(String message) {
        super(message);
    }
}
