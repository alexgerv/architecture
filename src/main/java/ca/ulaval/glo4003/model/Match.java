package ca.ulaval.glo4003.model;

import java.util.Date;
import java.util.Map;

public class Match {

    private String sport;
    private String venue;
    private Date date;
    private String homeTeam;
    private String visitorTeam;
    private String sex;

    private Map<String, Integer> availableTicketsBySection;

    public Match(String sport, String venue, Date date, String homeTeam, String visitorTeam, String sex,
                 Map<String, Integer> avalaibleTicketsBySection) {
        this.sport = sport;
        this.venue = venue;
        this.date = date;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.sex = sex;
        this.availableTicketsBySection = avalaibleTicketsBySection;
    }

    public int getTotatNumberOfAvailableTickets() {
        return calculateTotalNumberOfAvailableTickets();
    }

    private int calculateTotalNumberOfAvailableTickets() {
        int numberOfAvailableTickets = 0;
        for (int numberOfTickets : availableTicketsBySection.values()) {
            numberOfAvailableTickets += numberOfTickets;
        }
        return numberOfAvailableTickets;
    }

    public String getSport() {
        return sport;
    }

    public String getVenue() {
        return venue;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }
    
    public String getSex() {
        return sex;
    }

    public Date getDate() {
        return date;
    }

    public Map<String, Integer> getAvailableTicketsBySection() {
        return availableTicketsBySection;
    }
}
