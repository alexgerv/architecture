package ca.ulaval.glo4003.testsuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.ulaval.glo4003.domain.index.FilterTest;
import ca.ulaval.glo4003.domain.index.ListIndexTest;
import ca.ulaval.glo4003.domain.index.QueryResolverTest;
import ca.ulaval.glo4003.domain.index.QueryTest;
import ca.ulaval.glo4003.domain.match.MatchInformationsTest;
import ca.ulaval.glo4003.domain.match.MatchTest;
import ca.ulaval.glo4003.domain.match.SectionTest;
import ca.ulaval.glo4003.domain.match.TicketTest;
import ca.ulaval.glo4003.domain.user.UserTest;
import ca.ulaval.glo4003.infrastructure.index.JSONMatchQueryFactoryTest;
import ca.ulaval.glo4003.infrastructure.matchCatalog.JSONMatchCatalogTest;
import ca.ulaval.glo4003.infrastructure.repository.JSONMatchRepositoryTest;
import ca.ulaval.glo4003.infrastructure.repository.JSONUserRepositoryTest;
import ca.ulaval.glo4003.service.AuthenticationServiceTest;
import ca.ulaval.glo4003.web.HomeControllerTest;
import ca.ulaval.glo4003.web.LoginLogoutControllerTest;
import ca.ulaval.glo4003.web.MatchListControllerTest;
import ca.ulaval.glo4003.web.SearchBarControllerTest;
import ca.ulaval.glo4003.web.SignupControllerTest;
import ca.ulaval.glo4003.web.TicketPurchaseControllerTest;
import ca.ulaval.glo4003.web.converters.MatchViewConverterTest;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModelTest;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModelTest;
import ca.ulaval.glo4003.web.viewmodels.UserViewModelTest;

@RunWith(Suite.class)
@SuiteClasses({ MatchViewConverterTest.class, UserTest.class, FilterTest.class, ListIndexTest.class,
               QueryResolverTest.class, QueryTest.class, MatchTest.class, UserTest.class, JSONMatchCatalogTest.class,
               JSONMatchQueryFactoryTest.class, JSONMatchRepositoryTest.class, JSONUserRepositoryTest.class,
               AuthenticationServiceTest.class, HomeControllerTest.class, LoginLogoutControllerTest.class,
               MatchListControllerTest.class, SearchBarControllerTest.class, SignupControllerTest.class,
               MatchViewModelTest.class, UserViewModelTest.class, SectionViewModelTest.class, SectionTest.class,
               TicketTest.class, MatchInformationsTest.class, TicketPurchaseControllerTest.class })
public class UnitTestSuite {

}
