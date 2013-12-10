package ca.ulaval.glo4003.acceptanceTests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class MatchListTest {

    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
    }

    @Test
    public void whenOpenningMatchListPageTheNumberOfAvalaibleTicketsIsDisplayedForAParticularSport() throws Exception {
        fixture.clickOnMatchListButton();
        fixture.SearchForAParticularSport();
        int totalNumberOfTicketsForAParticularMatch = fixture.getTotalNumberOfTicketsForAParticularMatch();
        fixture.clickOnAParticularMatch();
        int totalNumberOfTicketsForAParticularMatchFromDetailedPage = fixture.getTotalNumberOfTicketsFromSubSectionsForAParticularMatch();

        assertEquals(totalNumberOfTicketsForAParticularMatchFromDetailedPage, totalNumberOfTicketsForAParticularMatch);
    }

    @After
    public void tearDown() throws Exception {
        fixture.close();
    }

}
