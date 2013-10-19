package ca.ulaval.glo4003.web.views;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class MatchListTest {

    private static final String XPATH_FOR_MATCH_LIST_TICKETS_NUMBER = "//*[@id='matchList']//tr//td[8]//strong";
    private static final String XPATH_FOR_MATCH_LIST_LINK = "//*[@id='matchList']//tr//td[8]//a";
    private static final String CLASS_NAME_FOR_TICKET_BY_SECTION = "ticketsBySection";

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
        fixture.FilterSportsForAParticularSport();
        String totalNumberOfTicketsText =
                                          fixture.driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_FOR_MATCH_LIST_TICKETS_NUMBER)))
                                                            .getText();

        int totalNumberOfTickets = Integer.parseInt(totalNumberOfTicketsText);

        fixture.driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_MATCH_LIST_LINK))).click();
        fixture.driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className(CLASS_NAME_FOR_TICKET_BY_SECTION)));

        List<WebElement> list = fixture.driver.findElements(By.className("ticketsBySection"));
        int totalAvailableTickets = 0;
        for (WebElement element : list) {
            totalAvailableTickets += Integer.parseInt(element.getText());
        }

        assertEquals(totalAvailableTickets, totalNumberOfTickets);
    }

    @After
    public void tearDown() throws Exception {
        fixture.close();
    }

}
