package ca.ulaval.glo4003.web.viewmodels;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.domain.match.AdmissionType;
import ca.ulaval.glo4003.domain.match.Sex;

public class TicketViewModelTest {

    private static final int[] avalaibleTicketsInFirstSection = { 1, 2, 3, 4 };
    private static final int[] avalaibleTicketsInSecondSection = { 5, 6, 7 };
    private static final int INITIAL_NUMBER_OF_AVALAIBLE_TICKETS = avalaibleTicketsInFirstSection.length
                                                                   + avalaibleTicketsInSecondSection.length;
    private Sex A_SEX = Sex.MEN;
    private int AN_ID = 0;
    private String A_SPORT = "aSport";
    private String A_VENUE = "Venue";
    private String A_DATE = "2013/10/31";
    private String A_HOME_TEAM = "HomeTeam";
    private String A_VISITOR_TEAM = "VisitorTeam";
    private static final float SECTION_PRICE = 10.5f;
    private static final AdmissionType AN_ADMISSION_TYPE = AdmissionType.GENERAL;

    private TicketViewModel aTicketViewModel;

    @Before
    public void setup() {
        aTicketViewModel = new TicketViewModel();
        aTicketViewModel.setID(AN_ID);
        aTicketViewModel.setSport(A_SPORT);
        aTicketViewModel.setVenue(A_VENUE);
        aTicketViewModel.setDate(A_DATE);
        aTicketViewModel.setHomeTeam(A_HOME_TEAM);
        aTicketViewModel.setVisitorTeam(A_VISITOR_TEAM);
        aTicketViewModel.setSex(A_SEX);
        aTicketViewModel.setAdmissionType(AN_ADMISSION_TYPE);
        aTicketViewModel.setPrice(SECTION_PRICE);
    }

    @Test
    public void canGetTheIDOfATicketViewModel() {
        assertEquals(aTicketViewModel.getID(), AN_ID);
    }

    @Test
    public void canGetTheSportOfATicketViewModel() {
        assertEquals(aTicketViewModel.getSport(), A_SPORT);
    }

    @Test
    public void canGetTheVenueOfATicketViewModel() {
        assertEquals(aTicketViewModel.getVenue(), A_VENUE);
    }

    @Test
    public void canGetTheDateOfATicketViewModel() {
        assertEquals(aTicketViewModel.getDate(), A_DATE);
    }

    @Test
    public void canGetTheHomeTeamOfATicketViewModel() {
        assertEquals(aTicketViewModel.getHomeTeam(), A_HOME_TEAM);
    }

    @Test
    public void canGetTheVisitorTeamOfATicketViewModel() {
        assertEquals(aTicketViewModel.getVisitorTeam(), A_VISITOR_TEAM);
    }

    @Test
    public void canGetTheSexOfATicketViewModel() {
        assertEquals(aTicketViewModel.getSex(), A_SEX);
    }

    @Test
    public void canGetAdmissionType() {
        assertEquals(aTicketViewModel.getAdmissionType(), AN_ADMISSION_TYPE);
    }

    @Test
    public void canGetTicketPrice() {
        assertEquals(aTicketViewModel.getPrice(), SECTION_PRICE, 0.01);
    }

}
