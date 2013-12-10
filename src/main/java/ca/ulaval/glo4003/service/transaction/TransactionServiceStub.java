package ca.ulaval.glo4003.service.transaction;

import java.util.Random;

import ca.ulaval.glo4003.domain.payment.CreditCard;
import ca.ulaval.glo4003.domain.payment.TransactionProcessingException;
import ca.ulaval.glo4003.domain.payment.TransactionService;

public class TransactionServiceStub implements TransactionService {

    private static final int MAX_TRANSACTION_NUMBER = 99999999;

    @Override
    public long processPayment(CreditCard creditCard, double amount) throws TransactionProcessingException {

        return new Random().nextInt(MAX_TRANSACTION_NUMBER);
    }

}
