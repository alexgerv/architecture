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
        fixture.goOnLoginPage();
        fixture.logInWithRightCredentials();
    }

    @After
    public void tearDown() {
        fixture.close();
    }

    @Test
    public void fromSectionDetailsUserCanProceedToThePurchaseScreen() {
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
        fixture.selectANumberOfTicketsForCurrentSection(A_NUMBER_OF_TICKETS);
        fixture.assertOnPurchasePage();
    }

    @Test
    public void whenEnteringANumberOfTicketsTheRightNumberIsDisplayedOnThePurchaseScreen() {
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
        fixture.selectANumberOfTicketsForCurrentSection(A_NUMBER_OF_TICKETS);
        fixture.assertBuyingTheRightNumberOfTickets(A_NUMBER_OF_TICKETS);
    }
    
    @Test
    public void whenBuyingANumberOfTicketsFromAMatchTheNumberOfAvailableTicketsOfThatMatchIsDecreased() {
        
        int initialNumberOfTickets = fixture.navigateToMatchDetailsAndReturnTheNumberOfTicketsJustBefore();
        
        fixture.chooseASectionInMatchDetails();
        fixture.selectANumberOfTicketsForCurrentSection(A_NUMBER_OF_TICKETS);
        fixture.buyATicket();
        fixture.goOnHomePage();
        
        int finalNumberOfTickets = fixture.navigateToMatchDetailsAndReturnTheNumberOfTicketsJustBefore();
        
        fixture.chooseASectionInMatchDetails();
        fixture.assertNumberOfAvailableTicketsDecreaseByTheNumberOfBoughtTickets(initialNumberOfTickets, finalNumberOfTickets, A_NUMBER_OF_TICKETS);
    }
}
