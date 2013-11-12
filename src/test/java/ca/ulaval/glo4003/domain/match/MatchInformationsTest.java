package ca.ulaval.glo4003.domain.match;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

public class MatchInformationsTest {

    private static final String DATE_FORMAT_TEMPLATE = "yyyy-MM-dd HH'h'mm";

    private static final String A_SPORT = "Basketball";
    private static final String A_VENUE = "ULaval";
    private static final Date A_DATE = new Date();
    private static final String A_FORMATED_DATE = DateFormatUtils.format(A_DATE, DATE_FORMAT_TEMPLATE);
    private static final String HOME_TEAM = "HOME_TEAM";
    private static final String VISITOR_TEAM = "VISITOR_TEAM";
    private static final Sex A_SEX = Sex.MEN;

    private MatchInformation match = new MatchInformation(A_SPORT, A_VENUE, A_DATE, HOME_TEAM, VISITOR_TEAM, A_SEX);

    @Test
    public void canGetHomeTeam() {
        String homeTeam = match.getHomeTeam();
        assertEquals(HOME_TEAM, homeTeam);
    }

    @Test
    public void canGetVisitorTeam() {
        String visitorTeam = match.getVisitorTeam();
        assertEquals(VISITOR_TEAM, visitorTeam);
    }

    @Test
    public void canGetSport() {
        String sport = match.getSport();
        assertEquals(A_SPORT, sport);
    }

    @Test
    public void canGetVenue() {
        String venue = match.getVenue();
        assertEquals(A_VENUE, venue);
    }

    @Test
    public void canGetDate() {
        String date = match.getFormatedDate();
        assertEquals(A_FORMATED_DATE, date);
    }

    @Test
    public void canGetSex() {
        Sex sex = match.getSex();
        assertEquals(A_SEX, sex);
    }

    @Test
    public void canSetHomeTeam() {
        String newHomeTeam = "team";
        match.setHomeTeam(newHomeTeam);
        String homeTeam = match.getHomeTeam();
        assertEquals(newHomeTeam, homeTeam);
    }

    @Test
    public void canSetVisitorTeam() {
        String newVisitorTeam = "team";
        match.setVisitorTeam(newVisitorTeam);
        String visitorTeam = match.getVisitorTeam();
        assertEquals(newVisitorTeam, visitorTeam);
    }

    @Test
    public void canSetSport() {
        String newSport = "sport";
        match.setSport(newSport);
        String sport = match.getSport();
        assertEquals(newSport, sport);
    }

    @Test
    public void canSetVenue() {
        String newVenue = "venue";
        match.setVenue(newVenue);
        String venue = match.getVenue();
        assertEquals(newVenue, venue);
    }

    @Test
    public void canSetDate() {
        Date newDate = new Date(10000);
        String formatedNewDate = DateFormatUtils.format(newDate, DATE_FORMAT_TEMPLATE);

        match.setDate(newDate);

        String date = match.getFormatedDate();
        assertEquals(formatedNewDate, date);
    }

    @Test
    public void canSetSex() {
        Sex newSex = Sex.WOMEN;
        match.setSex(newSex);
        Sex sex = match.getSex();
        assertEquals(newSex, sex);
    }

}
