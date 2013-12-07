package ca.ulaval.glo4003.domain.payment;

public interface TransactionService {

    public long processPayment(CreditCard creditCard, double amount) throws TransactionProcessingException;
}
