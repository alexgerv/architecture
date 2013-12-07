package ca.ulaval.glo4003.acceptanceTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class UserCanChooseTicketNumberForASectionAndProceedToPurchaseTest {

    private static final String A_TICKET_QUANTITY = "5";
    private TestFixture fixture;

    @Before
    public void setUp() {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
        fixture.goOnLoginPage();
        fixture.logInWithRightCredentials();
    }

    @After
    public void tearDown() {
        fixture.logOut();
        fixture.close();
    }

    @Test
    public void fromSectionDetailsUserCanProceedToThePurchaseScreen() {
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);
        fixture.buySelectedTickets();
        fixture.assertOnPurchasePage();
    }

    @Test
    public void whenEnteringATicketQuantityTheRightQuantityIsDisplayedOnThePurchaseScreen() {
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);
        fixture.buySelectedTickets();
        fixture.assertBuyingTheRightTicketQuantity(A_TICKET_QUANTITY);
    }

    @Test
    public void whenBuyingATicketQuantityFromAMatchTheNumberOfAvailableTicketsOfThatMatchIsDecreased() {

        int initialNumberOfTickets = fixture.navigateToMatchDetailsAndReturnTheNumberOfTicketsJustBefore();

        fixture.chooseASectionInMatchDetails();
        fixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);
        fixture.buySelectedTickets();
        fixture.payForTickets();
        fixture.goOnHomePage();

        int finalNumberOfTickets = fixture.navigateToMatchDetailsAndReturnTheNumberOfTicketsJustBefore();

        fixture.chooseASectionInMatchDetails();
        fixture.assertNumberOfAvailableTicketsDecreaseByTheNumberOfBoughtTickets(initialNumberOfTickets,
                                                                                 finalNumberOfTickets,
                                                                                 A_TICKET_QUANTITY);
    }
}
