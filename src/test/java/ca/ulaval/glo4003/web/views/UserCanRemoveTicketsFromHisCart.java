package ca.ulaval.glo4003.web.views;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class UserCanRemoveTicketsFromHisCart {

    private static final String A_TICKET_QUANTITY = "2";
    private static final String ANOTHER_TICKET_QUANTITY = "1";

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
    public void userCanEmptyHisCart() {

        fixture.goOnHomePage();
        fixture.goOnLoginPage();
        fixture.logInWithRightCredentials();
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);
        fixture.addSelectedTicketsToCart();
        fixture.navigateToMatchDetails();
        fixture.chooseAnotherSectionInMatchDetails();
        fixture.selectATicketQuantityForCurrentSection(ANOTHER_TICKET_QUANTITY);
        fixture.addSelectedTicketsToCart();

        fixture.emptyCart();

        fixture.assertTheCartIsEmpty();
    }

    @Test
    public void userCanRemoveTicketsFromHisCart() {

        fixture.goOnHomePage();
        fixture.goOnLoginPage();
        fixture.logInWithRightCredentials();
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);
        fixture.addSelectedTicketsToCart();

        fixture.removeFirstTicketTypeFromCart();

        fixture.assertTheCartIsEmpty();
    }
}
