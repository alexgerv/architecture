package ca.ulaval.glo4003.web.views;

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

        anOtherUserFixture.goOnSignUpPage();
        anOtherUserFixture.signUp();
        anOtherUserFixture.goOnLoginPage();

        aUserFixture.logInWithRightCredentials();
        anOtherUserFixture.logInWithNewUserName();

        aUserFixture.navigateToMatchDetails();
        anOtherUserFixture.navigateToMatchDetails();

        aUserFixture.chooseASectionInMatchDetails();
        anOtherUserFixture.chooseASectionInMatchDetails();

        aUserFixture.selectATicketQuantityForCurrentSection(A_TICKET_QUANTITY);
        anOtherUserFixture.selectATicketQuantityForCurrentSection(ANOTHER_TICKET_QUANTITY);

        aUserFixture.buyATicket();
        anOtherUserFixture.buyATicket();

        aUserFixture.assertBuyWasSuccessful();
        anOtherUserFixture.assertBuyWasSuccessful();

        aUserFixture.close();
        anOtherUserFixture.close();
    }
}
