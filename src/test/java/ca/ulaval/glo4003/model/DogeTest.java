package ca.ulaval.glo4003.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class DogeTest {

    private static final String A_SPORT = "Basketball";
    private static final String A_VENUE = "ULaval";
    private static final Date A_DATE = new Date();
    private static final String HOME_TEAM = "HOME_TEAM";
    private static final String VISITOR_TEAM = "VISITOR_TEAM";
    private static final Sex A_SEX = Sex.MEN;

    private Doge doge = new Doge(A_SPORT, A_VENUE, A_DATE, HOME_TEAM, VISITOR_TEAM, A_SEX);

    @Test
    public void canGetHomeTeam() {
        String homeTeam = doge.getHomeTeam();
        assertEquals(HOME_TEAM, homeTeam);
    }

    @Test
    public void canGetVisitorTeam() {
        String visitorTeam = doge.getVisitorTeam();
        assertEquals(VISITOR_TEAM, visitorTeam);
    }

    @Test
    public void canGetSport() {
        String sport = doge.getSport();
        assertEquals(A_SPORT, sport);
    }

    @Test
    public void canGetVenue() {
        String venue = doge.getVenue();
        assertEquals(A_VENUE, venue);
    }

    @Test
    public void canGetDate() {
        Date date = doge.getDate();
        assertEquals(A_DATE, date);
    }

    @Test
    public void canGetSex() {
        Sex sex = doge.getSex();
        assertEquals(A_SEX, sex);
    }

    @Test
    public void canSetHomeTeam() {
        String newHomeTeam = "team";
        doge.setHomeTeam(newHomeTeam);
        String homeTeam = doge.getHomeTeam();
        assertEquals(newHomeTeam, homeTeam);
    }

    @Test
    public void canSetVisitorTeam() {
        String newVisitorTeam = "team";
        doge.setVisitorTeam(newVisitorTeam);
        String visitorTeam = doge.getVisitorTeam();
        assertEquals(newVisitorTeam, visitorTeam);
    }

    @Test
    public void canSetSport() {
        String newSport = "sport";
        doge.setSport(newSport);
        String sport = doge.getSport();
        assertEquals(newSport, sport);
    }

    @Test
    public void canSetVenue() {
        String newVenue = "venue";
        doge.setVenue(newVenue);
        String venue = doge.getVenue();
        assertEquals(newVenue, venue);
    }

    @Test
    public void canSetDate() {
        Date newDate = new Date(0, 0, 0);
        doge.setDate(newDate);
        Date date = doge.getDate();
        assertEquals(newDate, date);
    }

    @Test
    public void canSetSex() {
        Sex newSex = Sex.WOMEN;
        doge.setSex(newSex);
        Sex sex = doge.getSex();
        assertEquals(newSex, sex);
    }

}
