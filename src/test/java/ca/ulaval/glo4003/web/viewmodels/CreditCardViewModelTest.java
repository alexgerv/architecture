package ca.ulaval.glo4003.web.viewmodels;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CreditCardViewModelTest {

    private static final String ANOTHER_CARD_NUMBER = "2";
    private static final String A_CARD_NUMBER = "0";
    private static final String A_CARD_TYPE = "A_CARD_TYPE";
    private static final String ANOTHER_CARD_TYPE = "ANOTHER_CARD_TYPE";
    private CreditCardViewModel aCreditCardViewModel;

    @Before
    public void setup() {
        aCreditCardViewModel = new CreditCardViewModel();
        aCreditCardViewModel.setNumber(A_CARD_NUMBER);
        aCreditCardViewModel.setType(A_CARD_TYPE);
    }

    @Test
    public void canGetCardNumber() {
        long cardNumber = aCreditCardViewModel.getNumber();
        assertEquals(Long.parseLong(A_CARD_NUMBER), cardNumber);
    }

    @Test
    public void canGetCardType() {
        String cardType = aCreditCardViewModel.getType();
        assertEquals(A_CARD_TYPE, cardType);
    }

    @Test
    public void canSetCardNumber() {
        aCreditCardViewModel.setNumber(ANOTHER_CARD_NUMBER);
        long cardNumber = aCreditCardViewModel.getNumber();
        assertEquals(Long.parseLong(ANOTHER_CARD_NUMBER), cardNumber);
    }

    @Test
    public void canSetCardType() {
        aCreditCardViewModel.setType(ANOTHER_CARD_TYPE);
        String cardType = aCreditCardViewModel.getType();
        assertEquals(ANOTHER_CARD_TYPE, cardType);
    }
}
