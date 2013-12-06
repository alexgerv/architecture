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
import ca.ulaval.glo4003.domain.payment.CreditCardFactoryTest;
import ca.ulaval.glo4003.domain.payment.TransactionManagerTest;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCartTest;
import ca.ulaval.glo4003.domain.user.UserTest;
import ca.ulaval.glo4003.infrastructure.index.JSONMatchQueryFactoryTest;
import ca.ulaval.glo4003.infrastructure.match.JSONMatchRepositoryTest;
import ca.ulaval.glo4003.infrastructure.matchCatalog.JSONMatchCatalogTest;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchDateFilterTest;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchHomeTeamFilterTest;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchSportFilterTest;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchVenueFilterTest;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchVisitorTeamFilterTest;
import ca.ulaval.glo4003.infrastructure.persistence.FileAccessorTest;
import ca.ulaval.glo4003.infrastructure.user.JSONUserRepositoryTest;
import ca.ulaval.glo4003.service.AuthenticationServiceTest;
import ca.ulaval.glo4003.service.TransactionServiceStubTest;
import ca.ulaval.glo4003.web.CartControllerTest;
import ca.ulaval.glo4003.web.HomeControllerTest;
import ca.ulaval.glo4003.web.LoginLogoutControllerTest;
import ca.ulaval.glo4003.web.MatchListControllerTest;
import ca.ulaval.glo4003.web.SearchBarControllerTest;
import ca.ulaval.glo4003.web.SignupControllerTest;
import ca.ulaval.glo4003.web.TicketPurchaseControllerTest;
import ca.ulaval.glo4003.web.converters.MatchViewConverterTest;
import ca.ulaval.glo4003.web.converters.SectionViewConverterTest;
import ca.ulaval.glo4003.web.converters.TicketViewConverterTest;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModelTest;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModelTest;
import ca.ulaval.glo4003.web.viewmodels.TicketViewModelTest;
import ca.ulaval.glo4003.web.viewmodels.UserViewModelTest;

@RunWith(Suite.class)
@SuiteClasses({ MatchViewConverterTest.class, SectionViewConverterTest.class, UserTest.class, FileAccessorTest.class,
               FilterTest.class, ListIndexTest.class, QueryResolverTest.class, QueryTest.class, MatchTest.class,
               UserTest.class, JSONMatchCatalogTest.class, JSONMatchQueryFactoryTest.class,
               JSONMatchRepositoryTest.class, JSONUserRepositoryTest.class, AuthenticationServiceTest.class,
               HomeControllerTest.class, LoginLogoutControllerTest.class, MatchListControllerTest.class,
               SearchBarControllerTest.class, SignupControllerTest.class, MatchViewModelTest.class,
               UserViewModelTest.class, SectionViewModelTest.class, SectionTest.class, TicketTest.class,
               MatchInformationsTest.class, TicketPurchaseControllerTest.class, MatchDateFilterTest.class,
               MatchVenueFilterTest.class, MatchSportFilterTest.class, MatchHomeTeamFilterTest.class,
               MatchVisitorTeamFilterTest.class, TransactionServiceStubTest.class, TicketViewModelTest.class,
               TicketViewConverterTest.class, ShoppingCartTest.class, CartControllerTest.class,
               TransactionManagerTest.class, CreditCardFactoryTest.class })
public class UnitTestSuite {

}
