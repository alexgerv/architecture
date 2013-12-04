package ca.ulaval.glo4003.domain.payment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CreditCardFactoryTest {

    private static final String A_TYPE_OF_CREDIT_CARD = "VASI";
    private static final long A_CREDIT_CARD_NUMBER = 999999999;

    public void setUp() {

    }

    @Test
    public void whenAskingForACreditCardWithATypeTheRightCreditCardIsReturned() throws InvalidCreditCardException {
        CreditCardFactory crediCardFactory = new CreditCardFactory();
        CreditCard creditCard = crediCardFactory.create(A_TYPE_OF_CREDIT_CARD, A_CREDIT_CARD_NUMBER);

        assertTrue(creditCard instanceof VasiCreditCard);
    }
}
