package ca.ulaval.glo4003.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.ulaval.glo4003.web.views.DynamicSearchTest;
import ca.ulaval.glo4003.web.views.HomeTest;
import ca.ulaval.glo4003.web.views.UserCanChangeSearchFilterToSeeOnlyTheMatchesHeIsInterestedIn;
import ca.ulaval.glo4003.web.views.UsersCanCreateANewAccount;
import ca.ulaval.glo4003.web.views.UsersCanLogInTest;
import ca.ulaval.glo4003.web.views.UsersCanSeeNumberOfAvailableTicketsPerSectionTest;

@RunWith(Suite.class)
@SuiteClasses({ DynamicSearchTest.class, HomeTest.class,
               UserCanChangeSearchFilterToSeeOnlyTheMatchesHeIsInterestedIn.class, UsersCanCreateANewAccount.class,
               UsersCanLogInTest.class, UsersCanSeeNumberOfAvailableTicketsPerSectionTest.class })
public class IntegrationTestSuite {

}