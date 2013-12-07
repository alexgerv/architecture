package ca.ulaval.glo4003.acceptanceTests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class UserCanChangeSearchFilterToSeeOnlyTheMatchesHeIsInterestedInTest {

    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
    }

    @Test
    public void whenViewingASectionUserCanSeeThePrice() {
        fixture.goOnMatchListPage();
        fixture.SearchForAParticularSport();
        fixture.assertAllShownMatchesAreOfTheSelectedSport();
    }

    @Test
    public void testRemovingCriteraFromSearchExpandsDisplayedResults() {
        fixture.clickOnMatchListButton();
        fixture.SearchForAParticularSport();
        fixture.SearchForAParticularVenue();

        List<WebElement> firstSearchResults = fixture.getSearchResults();

        fixture.unFilterSearchForAParticularVenue();
        List<WebElement> secondSearchResults = fixture.getSearchResults();

        assertTrue(firstSearchResults.size() <= secondSearchResults.size());
    }

    @After
    public void tearDown() throws Exception {
        fixture.close();
    }

}