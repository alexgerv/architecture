package ca.ulaval.glo4003.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.ulaval.glo4003.converters.MatchViewConverterTest;
import ca.ulaval.glo4003.index.FilterTest;
import ca.ulaval.glo4003.index.ListIndexTest;
import ca.ulaval.glo4003.index.QueryResolverTest;
import ca.ulaval.glo4003.index.QueryTest;
import ca.ulaval.glo4003.model.DogeTest;
import ca.ulaval.glo4003.model.MatchTest;
import ca.ulaval.glo4003.model.SectionTest;
import ca.ulaval.glo4003.model.TicketTest;
import ca.ulaval.glo4003.model.UserTest;
import ca.ulaval.glo4003.persistence.json.JSONMatchCatalogTest;
import ca.ulaval.glo4003.persistence.json.JSONMatchQueryFactoryTest;
import ca.ulaval.glo4003.persistence.json.JSONMatchRepositoryTest;
import ca.ulaval.glo4003.persistence.json.JSONUserRepositoryTest;
import ca.ulaval.glo4003.service.AuthenticationServiceTest;
import ca.ulaval.glo4003.web.HomeControllerTest;
import ca.ulaval.glo4003.web.LoginLogoutControllerTest;
import ca.ulaval.glo4003.web.SearchBarControllerTest;
import ca.ulaval.glo4003.web.SignupControllerTest;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModelTest;

@RunWith(Suite.class)
@SuiteClasses({ MatchViewConverterTest.class, UserTest.class, FilterTest.class, ListIndexTest.class,
               QueryResolverTest.class, QueryTest.class, MatchTest.class, UserTest.class, JSONMatchCatalogTest.class,
               JSONMatchQueryFactoryTest.class, JSONMatchRepositoryTest.class, JSONUserRepositoryTest.class,
               AuthenticationServiceTest.class, HomeControllerTest.class, LoginLogoutControllerTest.class,
               SearchBarControllerTest.class, SignupControllerTest.class, MatchViewModelTest.class, SectionTest.class,
               TicketTest.class, DogeTest.class })
public class UnitTestSuite {

}
