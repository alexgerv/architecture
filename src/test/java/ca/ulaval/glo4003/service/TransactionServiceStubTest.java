package ca.ulaval.glo4003.service;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import ca.ulaval.glo4003.domain.payment.CreditCard;



public class TransactionServiceStubTest {
    private static final int MAX_TRANSACTION_ID = 99999999;
    private static final double AN_AMOUNT = 10.0;
    
    private CreditCard creditCard = mock(CreditCard.class);
    
    @Test
    public void whenProcessingATransactionConfirmationOf8DigitsIsRetured() {
        TransactionServiceStub transactionServiceStub = new TransactionServiceStub();
        
        long transactionID = transactionServiceStub.processPayment(creditCard, AN_AMOUNT);
        
        assertTrue(transactionID <= MAX_TRANSACTION_ID);
    }
    
    @Test
    public void everyTransactionHasAUniqueConfirmationNumber() {
        TransactionServiceStub transactionServiceStub = new TransactionServiceStub();
        
        long firstTransactionID = transactionServiceStub.processPayment(creditCard, AN_AMOUNT);
        long secondTransactionID = transactionServiceStub.processPayment(creditCard, AN_AMOUNT);
        
        assertTrue(firstTransactionID != secondTransactionID);
    }
}
