package ca.ulaval.glo4003.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.ulaval.glo4003.acceptanceTests.HomeTest;
import ca.ulaval.glo4003.acceptanceTests.ManyUsersCanBuyTicketsSimultaneouslyTest;
import ca.ulaval.glo4003.acceptanceTests.TransactionsAreLoggedTest;
import ca.ulaval.glo4003.acceptanceTests.UserCanBuyEveryTicketsInHisCart;
import ca.ulaval.glo4003.acceptanceTests.UserCanChangeSearchFilterToSeeOnlyTheMatchesHeIsInterestedInTest;
import ca.ulaval.glo4003.acceptanceTests.UserCanChooseTicketNumberForASectionAndProceedToPurchaseTest;
import ca.ulaval.glo4003.acceptanceTests.UserCanCreateANewAccountTest;
import ca.ulaval.glo4003.acceptanceTests.UserCanEditTheTicketQuantityInHisCart;
import ca.ulaval.glo4003.acceptanceTests.UserCanHaveAShoppingCartTest;
import ca.ulaval.glo4003.acceptanceTests.UserCanLogInTest;
import ca.ulaval.glo4003.acceptanceTests.UserCanRemoveTicketsFromHisCart;
import ca.ulaval.glo4003.acceptanceTests.UserCanSeeInformationsForASectionTest;
import ca.ulaval.glo4003.acceptanceTests.UserCanSeeNumberOfAvailableTicketsPerSectionTest;

@RunWith(Suite.class)
@SuiteClasses({ UserCanHaveAShoppingCartTest.class, UserCanRemoveTicketsFromHisCart.class, HomeTest.class,
               UserCanChangeSearchFilterToSeeOnlyTheMatchesHeIsInterestedInTest.class,
               UserCanCreateANewAccountTest.class, UserCanLogInTest.class,
               UserCanSeeNumberOfAvailableTicketsPerSectionTest.class, TransactionsAreLoggedTest.class,
               UserCanChooseTicketNumberForASectionAndProceedToPurchaseTest.class,
               UserCanSeeInformationsForASectionTest.class, ManyUsersCanBuyTicketsSimultaneouslyTest.class,
               UserCanBuyEveryTicketsInHisCart.class, UserCanEditTheTicketQuantityInHisCart.class })
public class IntegrationTestSuite {

}