package ca.ulaval.glo4003.domain.payment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CreditCardFactoryTest {

    private static final String VASI = "VASI";
    private static final String MISTERCARD = "MISTERCARD";
    private static final String AMERICANEXPRESSO = "AMERICANEXPRESSO";

    private static final long A_CREDIT_CARD_NUMBER = 999999999;
    private static final String INVALID_CARD_TYPE = "INVALID_CARD_TYPE";

    public void setUp() {

    }

    @Test
    public void whenAskingForAVASICreditCardWithTheRightCreditCardIsReturned() throws InvalidCreditCardException {
        CreditCardFactory crediCardFactory = new CreditCardFactory();
        CreditCard creditCard = crediCardFactory.create(VASI, A_CREDIT_CARD_NUMBER);

        assertTrue(creditCard instanceof VasiCreditCard);
    }

    @Test
    public void whenAskingForAMISTERCARDCreditCardTheRightCreditCardIsReturned() throws InvalidCreditCardException {
        CreditCardFactory crediCardFactory = new CreditCardFactory();
        CreditCard creditCard = crediCardFactory.create(MISTERCARD, A_CREDIT_CARD_NUMBER);

        assertTrue(creditCard instanceof MistercardCreditCard);
    }

    @Test
    public void whenAskingForAnAMERICANEXPRESSOCreditCardTheRightCreditCardIsReturned() throws InvalidCreditCardException {
        CreditCardFactory crediCardFactory = new CreditCardFactory();
        CreditCard creditCard = crediCardFactory.create(AMERICANEXPRESSO, A_CREDIT_CARD_NUMBER);

        assertTrue(creditCard instanceof AmericanExpressoCreditCard);
    }

    @Test(expected = InvalidCreditCardException.class)
    public void whenAskingForACreditCardWithAnInvalidTypeAnExceptionIsThrown() throws InvalidCreditCardException {
        CreditCardFactory crediCardFactory = new CreditCardFactory();
        crediCardFactory.create(INVALID_CARD_TYPE, A_CREDIT_CARD_NUMBER);
    }
}
