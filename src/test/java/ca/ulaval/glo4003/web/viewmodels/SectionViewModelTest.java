package ca.ulaval.glo4003.web.viewmodels;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.domain.match.AdmissionType;
import ca.ulaval.glo4003.domain.match.Sex;

public class SectionViewModelTest {

    private String A_SPORT = "aSport";
    private String A_VENUE = "aVenue";
    private String A_SECTION = "aSection";
    private String A_DATE = "aDate";
    private String A_HOME_TEAM = "aHomeTeam";
    private String A_VISITOR_TEAM = "aVisitorTeam";
    private Sex A_SEX = Sex.MEN;
    private AdmissionType AN_ADMISSION_TYPE = AdmissionType.GENERAL;
    private String A_NAME = "aName";
    private int AVAILABLE_TICKETS = 100;
    private float A_PRICE = 100;

    private SectionViewModel aSectionViewModel;

    @Before
    public void setUp() {
        aSectionViewModel = new SectionViewModel();
        aSectionViewModel.setSport(A_SPORT);
        aSectionViewModel.setVenue(A_VENUE);
        aSectionViewModel.setSection(A_SECTION);
        aSectionViewModel.setDate(A_DATE);
        aSectionViewModel.setHomeTeam(A_HOME_TEAM);
        aSectionViewModel.setVisitorTeam(A_VISITOR_TEAM);
        aSectionViewModel.setSex(A_SEX);
        aSectionViewModel.setName(A_NAME);
        aSectionViewModel.setAvailableTickets(AVAILABLE_TICKETS);
        aSectionViewModel.setPrice(A_PRICE);
        aSectionViewModel.setAdmissionType(AN_ADMISSION_TYPE);
    }

    @Test
    public void canGetTheSportOfASectionViewModel() {
        assertEquals(aSectionViewModel.getSport(), A_SPORT);
    }

    @Test
    public void canGetTheVenueOfASectionViewModel() {
        assertEquals(aSectionViewModel.getVenue(), A_VENUE);
    }

    @Test
    public void canGetTheSectionOfASectionViewModel() {
        assertEquals(aSectionViewModel.getSection(), A_SECTION);
    }

    @Test
    public void canGetTheDateOfASectionViewModel() {
        assertEquals(aSectionViewModel.getDate(), A_DATE);
    }

    @Test
    public void canGetTheHomeTeamOfASectionViewModel() {
        assertEquals(aSectionViewModel.getHomeTeam(), A_HOME_TEAM);
    }

    @Test
    public void canGetTheVisitorTeamOfASectionViewModel() {
        assertEquals(aSectionViewModel.getVisitorTeam(), A_VISITOR_TEAM);
    }

    @Test
    public void canGetTheSexOfASectionViewModel() {
        assertEquals(aSectionViewModel.getSex(), A_SEX);
    }

    @Test
    public void canGetTheNameOfASectionViewModel() {
        assertEquals(aSectionViewModel.getName(), A_NAME);
    }

    @Test
    public void canGetTheTotalNumberOfAvailableTicketsOfASectionViewModel() {
        assertEquals(aSectionViewModel.getAvailableTickets(), AVAILABLE_TICKETS);
    }

    @Test
    public void canGetThePriceOfASectionViewModel() {
        assertTrue(aSectionViewModel.getPrice() == A_PRICE);
    }

    @Test
    public void canGetAnAdmissionTypeOfASectionViewModel() {
        assertEquals(aSectionViewModel.getAdmissionType(), AN_ADMISSION_TYPE);
    }

}
