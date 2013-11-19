package ca.ulaval.glo4003.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.ulaval.glo4003.service.TransactionLoggerIntegrationTest;
import ca.ulaval.glo4003.web.views.HomeTest;
import ca.ulaval.glo4003.web.views.UserCanChangeSearchFilterToSeeOnlyTheMatchesHeIsInterestedInTest;
import ca.ulaval.glo4003.web.views.UserCanChooseTicketNumberForASectionAndProceedToPurchaseTest;
import ca.ulaval.glo4003.web.views.UserCanSeeInformationsForASectionTest;
import ca.ulaval.glo4003.web.views.UserCanCreateANewAccountTest;
import ca.ulaval.glo4003.web.views.UserCanLogInTest;
import ca.ulaval.glo4003.web.views.UserCanSeeNumberOfAvailableTicketsPerSectionTest;

@RunWith(Suite.class)
@SuiteClasses({ HomeTest.class, UserCanChangeSearchFilterToSeeOnlyTheMatchesHeIsInterestedInTest.class,
               UserCanCreateANewAccountTest.class, UserCanLogInTest.class,
               UserCanSeeNumberOfAvailableTicketsPerSectionTest.class, TransactionLoggerIntegrationTest.class,
               UserCanChooseTicketNumberForASectionAndProceedToPurchaseTest.class,
               UserCanSeeInformationsForASectionTest.class })
public class IntegrationTestSuite {

}