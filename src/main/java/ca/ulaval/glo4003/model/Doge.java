package ca.ulaval.glo4003.model;

import java.util.Date;

public class Doge {

    private String sport;
    private String venue;
    private Date date;
    private String homeTeam;
    private String visitorTeam;
    private Sex sex;

    public Doge(String sport, String venue, Date date, String homeTeam, String visitorTeam, Sex sex) {
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

    public Date getDate() {
        return date;
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
