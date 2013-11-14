package ca.ulaval.glo4003.web.views;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class UserCanChooseTicketNumberForASectionAndProceedToPurchaseTest {

    private static final String A_NUMBER_OF_TICKETS = "5";
    private TestFixture fixture;

    @Before
    public void setUp() {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
    }

    @After
    public void tearDown() {
        fixture.close();
    }

    @Test
    public void fromSectionDetailsUserCanProceedToThePurchaseScreen() {
        fixture.selectANumberOfTicketsForCurrentSection(A_NUMBER_OF_TICKETS);
        fixture.assertOnPurchasePage();
    }

    @Test
    public void whenEnteringANumberOfTicketsTheRightNumberIsDisplayedOnThePurchaseScreen() {
        fixture.selectANumberOfTicketsForCurrentSection(A_NUMBER_OF_TICKETS);
        fixture.assertBuyingTheRightNumberOfTickets(A_NUMBER_OF_TICKETS);
    }
}
