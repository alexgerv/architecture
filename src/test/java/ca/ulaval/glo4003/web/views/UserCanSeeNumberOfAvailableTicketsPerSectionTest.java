package ca.ulaval.glo4003.web.views;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class UserCanSeeNumberOfAvailableTicketsPerSectionTest {

    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
    }

    @Test
    public void whenLookingAtAMatchsDetailsTheUserCanSeeTheNumberOfTicketsAvailableIsDisplayedForEachSection() throws Exception {
        fixture.clickOnMatchesButtonInNavigationMenu();
        fixture.FilterSportsForAParticularSport();
        fixture.assertNumberOfAvailableTicketsForAMatchIsDisplayedForEachSection();

    }

    @After
    public void tearDown() throws Exception {
        fixture.close();
    }

}