package ca.ulaval.glo4003.web.views;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class HomeTest {

    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
    }

    @Test
    public void whenOnHomePageclickingOnViewMatchListButtonNavigatesToMatchListPage() throws Exception {
        fixture.clickOnMatchListButton();
        fixture.assertUserIsOnMatchListPage();
    }

    @Test
    public void whenOnHomePageclickingOnMatchesButtonNavigatesToMatchListPage() throws Exception {
        fixture.goOnMatchListPage();
        fixture.assertUserIsOnMatchListPage();
    }

    @After
    public void tearDown() throws Exception {
        fixture.close();
    }

}
