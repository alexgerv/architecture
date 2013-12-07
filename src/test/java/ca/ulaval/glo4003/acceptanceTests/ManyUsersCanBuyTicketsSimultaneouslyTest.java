package ca.ulaval.glo4003.acceptanceTests;

import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class ManyUsersCanBuyTicketsSimultaneouslyTest {

    private static final String A_TICKET_QUANTITY = "5";
    private static final String ANOTHER_TICKET_QUANTITY = "3";

    private TestFixture aUserFixture;
    private TestFixture anOtherUserFixture;

    @Test
    public void manyUsersCanBuyTicketsSimultaneously() {
        aUserFixture = new TestFixture();
        anOtherUserFixture = new TestFixture();

        aUserFixture.init();
        anOtherUserFixture.init();

        aUserFixture.goOnHomePage();
        anOtherUserFixture.goOnHomePage();

        aUserFixture.goOnLoginPage();
        anOtherUserFixture.goOnLoginPage();

        aUserFixture.logInWithRightCredentials();
        anOtherUserFixture.logInWithAnOtherRightCredentials();

        aUserFixture.navigateToMatchDetails();
        anOtherUserFixture.navigateToMatchDetails();

        aUserFixture.chooseASectionInMatchDetails();
        anOtherUserFixture.chooseASectionInMatchDetails();

        aUserFixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);
        anOtherUserFixture.selectATicketQuantityForCurrentSection(ANOTHER_TICKET_QUANTITY);

        aUserFixture.buySelectedTickets();
        anOtherUserFixture.buySelectedTickets();

        aUserFixture.payForTickets();
        anOtherUserFixture.payForTickets();

        aUserFixture.assertBuyWasSuccessful();
        anOtherUserFixture.assertBuyWasSuccessful();

        aUserFixture.logOut();
        anOtherUserFixture.logOut();

        aUserFixture.close();
        anOtherUserFixture.close();
    }
}
