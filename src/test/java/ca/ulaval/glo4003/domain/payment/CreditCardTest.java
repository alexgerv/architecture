package ca.ulaval.glo4003.domain.payment;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CreditCardTest {

    private static final long A_CARD_NUMBER = 12345;

    public class CreditCardStub extends CreditCard {

        CreditCardStub(long cardNumber) {
            super(cardNumber);
        }
    }

    @Test
    public void canGetCardNumber() {
        CreditCard card = new CreditCardStub(A_CARD_NUMBER);
        long cardNumber = card.getCardNumber();
        assertEquals(A_CARD_NUMBER, cardNumber);
    }
}
