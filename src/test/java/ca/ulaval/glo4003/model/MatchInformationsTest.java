package ca.ulaval.glo4003.model;

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

    private MatchInformation matchInformations = new MatchInformation(A_SPORT, A_VENUE, A_DATE, HOME_TEAM, VISITOR_TEAM, A_SEX);

    @Test
    public void canGetHomeTeam() {
        String homeTeam = matchInformations.getHomeTeam();
        assertEquals(HOME_TEAM, homeTeam);
    }

    @Test
    public void canGetVisitorTeam() {
        String visitorTeam = matchInformations.getVisitorTeam();
        assertEquals(VISITOR_TEAM, visitorTeam);
    }

    @Test
    public void canGetSport() {
        String sport = matchInformations.getSport();
        assertEquals(A_SPORT, sport);
    }

    @Test
    public void canGetVenue() {
        String venue = matchInformations.getVenue();
        assertEquals(A_VENUE, venue);
    }

    @Test
    public void canGetDate() {
        String date = matchInformations.getFormatedDate();
        assertEquals(A_FORMATED_DATE, date);
    }

    @Test
    public void canGetSex() {
        Sex sex = matchInformations.getSex();
        assertEquals(A_SEX, sex);
    }

    @Test
    public void canSetHomeTeam() {
        String newHomeTeam = "team";
        matchInformations.setHomeTeam(newHomeTeam);
        String homeTeam = matchInformations.getHomeTeam();
        assertEquals(newHomeTeam, homeTeam);
    }

    @Test
    public void canSetVisitorTeam() {
        String newVisitorTeam = "team";
        matchInformations.setVisitorTeam(newVisitorTeam);
        String visitorTeam = matchInformations.getVisitorTeam();
        assertEquals(newVisitorTeam, visitorTeam);
    }

    @Test
    public void canSetSport() {
        String newSport = "sport";
        matchInformations.setSport(newSport);
        String sport = matchInformations.getSport();
        assertEquals(newSport, sport);
    }

    @Test
    public void canSetVenue() {
        String newVenue = "venue";
        matchInformations.setVenue(newVenue);
        String venue = matchInformations.getVenue();
        assertEquals(newVenue, venue);
    }

    @Test
    public void canSetDate() {
        Date newDate = new Date(10000);
        String formatedNewDate = DateFormatUtils.format(newDate, DATE_FORMAT_TEMPLATE);
        
        matchInformations.setDate(newDate);
        
        String date = matchInformations.getFormatedDate();
        assertEquals(formatedNewDate, date);
    }

    @Test
    public void canSetSex() {
        Sex newSex = Sex.WOMEN;
        matchInformations.setSex(newSex);
        Sex sex = matchInformations.getSex();
        assertEquals(newSex, sex);
    }

}
