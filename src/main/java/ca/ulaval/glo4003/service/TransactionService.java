package ca.ulaval.glo4003.service;

import ca.ulaval.glo4003.domain.payment.CreditCard;


public interface TransactionService {
    public long processPayment(CreditCard creditCard, double amount) throws TransactionProcessingError;
}
