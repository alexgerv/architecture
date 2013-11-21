package ca.ulaval.glo4003.service;

import java.util.Random;

import ca.ulaval.glo4003.domain.payment.CreditCard;


public class TransactionServiceStub implements TransactionService {
    
    private static final int MAX_TRANSACTION_NUMBER = 99999999;

    @Override
    public long processPayment(CreditCard creditCard, double amount) throws TransactionProcessingError {
        
        return new Random().nextInt(MAX_TRANSACTION_NUMBER);
    }

}
