package ca.ulaval.glo4003.acceptanceTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class UserCanSeeInformationsForASectionTest {

    private TestFixture fixture;

    @Before
    public void setUp() {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
    }

    @After
    public void tearDown() {
        fixture.close();
    }

    @Test
    public void whenLookingAtASectionDetailsTheUserCanSeeTheInformationsForThisSection() {
        fixture.assertPriceOpposingTeamsDateAdmissionTypeAndSectionAreDisplayed();
    }

}
