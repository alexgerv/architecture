package ca.ulaval.glo4003.domain.match;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

public class MatchInformation {

    private static final String DATE_FORMAT_TEMPLATE = "yyyy-MM-dd HH'h'mm";

    private String sport;
    private String venue;
    private Date date;
    private String homeTeam;
    private String visitorTeam;
    private Sex sex;

    public MatchInformation(String sport, String venue, Date date, String homeTeam, String visitorTeam, Sex sex) {
        this.sport = sport;
        this.venue = venue;
        this.date = date;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.sex = sex;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getFormatedDate() {
        return DateFormatUtils.format(date, DATE_FORMAT_TEMPLATE);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
