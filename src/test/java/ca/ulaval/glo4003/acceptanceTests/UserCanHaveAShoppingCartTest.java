package ca.ulaval.glo4003.acceptanceTests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class UserCanHaveAShoppingCartTest {

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
    public void userCanAddTicketsToItsCart() {

        fixture.goOnHomePage();
        fixture.goOnLoginPage();
        fixture.logInWithRightCredentials();

        fixture.navigateToMatchDetails();

        fixture.chooseASectionInMatchDetails();

        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);

        fixture.addSelectedTicketsToCart();

        int ticketQuantity = fixture.getFirstTicketTypeQuantityInCart();
        assertEquals(Integer.parseInt(A_TICKET_QUANTITY), ticketQuantity);
    }

    @Test
    public void usersCartIsEmptiedWhenHeLogsOut() {

        fixture.goOnHomePage();
        fixture.goOnLoginPage();
        fixture.logInWithRightCredentials();
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);
        fixture.addSelectedTicketsToCart();

        fixture.logOut();
        fixture.logInWithRightCredentials();

        fixture.goOnCartPage();

        fixture.assertTheCartIsEmpty();
    }

    @Test
    public void userCanAddMultipleTypesOfTicketsToItsCart() {

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

        fixture.assertTwoTypesOfTicketsInCart();
    }
}
