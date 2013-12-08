package ca.ulaval.glo4003.acceptanceTests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class UserCanEditTheTicketQuantityInHisCart {

    private static final String A_TICKET_QUANTITY = "2";
    private static final String REDUCED_TICKET_QUANTITY = "1";
    private static final String RAISED_TICKET_QUANTITY = "3";
    private static final String ZERO_TICKET = "0";
    private static final String TOO_MANY_TICKETS = " 2147483647";

    private TestFixture fixture;

    @Before
    public void setup() {
        fixture = new TestFixture();
        fixture.init();
    }

    @After
    public void tearDown() {
        fixture.logOut();
        fixture.close();
    }

    @Test
    public void userCanLowerTheTicketQuantityInHisCart() {

        fixture.goOnHomePage();

        fixture.goOnLoginPage();

        fixture.logInWithRightCredentials();

        fixture.navigateToMatchDetails();

        fixture.chooseASectionInMatchDetails();

        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);

        fixture.addSelectedTicketsToCart();
        fixture.setNewTicketQuantityFromFirstTicketTypeFromCart(REDUCED_TICKET_QUANTITY);

        int ticketQuantity = fixture.getFirstTicketTypeQuantityInCart();
        assertEquals(Integer.parseInt(REDUCED_TICKET_QUANTITY), ticketQuantity);
    }

    @Test
    public void userCanRaiseTheTicketQuantityInHisCart() {

        fixture.goOnHomePage();

        fixture.goOnLoginPage();

        fixture.logInWithRightCredentials();

        fixture.navigateToMatchDetails();

        fixture.chooseASectionInMatchDetails();

        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);

        fixture.addSelectedTicketsToCart();
        fixture.setNewTicketQuantityFromFirstTicketTypeFromCart(RAISED_TICKET_QUANTITY);

        int ticketQuantity = fixture.getFirstTicketTypeQuantityInCart();
        assertEquals(Integer.parseInt(RAISED_TICKET_QUANTITY), ticketQuantity);
    }

    @Test
    public void settingTicketQuantityRemovesTheTicketsFromTheCart() {

        fixture.goOnHomePage();

        fixture.goOnLoginPage();

        fixture.logInWithRightCredentials();

        fixture.navigateToMatchDetails();

        fixture.chooseASectionInMatchDetails();

        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);

        fixture.addSelectedTicketsToCart();
        fixture.setNewTicketQuantityFromFirstTicketTypeFromCart(ZERO_TICKET);

        fixture.assertTheCartIsEmpty();
    }

    @Test
    public void anErrorIsShownIfTheUserTriesToAddMoreTicketsThanThereAreAvalaible() {

        fixture.goOnHomePage();

        fixture.goOnLoginPage();

        fixture.logInWithRightCredentials();

        fixture.navigateToMatchDetails();

        fixture.chooseASectionInMatchDetails();

        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);

        fixture.addSelectedTicketsToCart();
        fixture.setNewTicketQuantityFromFirstTicketTypeFromCart(TOO_MANY_TICKETS);

        fixture.assertNotEnoughTicketsAvalaibleErrorMessageIsDisplayed();
        fixture.emptyCart();
    }
}
