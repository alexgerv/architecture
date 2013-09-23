package ca.ulaval.glo4003.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Match {

    private String sport;
    private String venue;
    private Date date;
    private String homeTeam;
    private String visitorTeam;

    private Map<String, Integer> avalaibleTicketsBySection;

    public Match(String sport, String venue, Date date, String homeTeam, String visitorTeam,
                 Map<String, Integer> avalaibleTicketsBySection) {
        avalaibleTicketsBySection = new HashMap<String, Integer>();

        this.sport = sport;
        this.venue = venue;
        this.date = date;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.avalaibleTicketsBySection = avalaibleTicketsBySection;
    }

    public void addTicket(String sectionsName) {
        changeNumberOfTicketsBy(sectionsName, 1);
    }

    public void removeTicket(String sectionsName) {
        changeNumberOfTicketsBy(sectionsName, -1);
    }

    private void changeNumberOfTicketsBy(String sectionsName, int number) {
        int actualTicketCount = avalaibleTicketsBySection.get(sectionsName);
        avalaibleTicketsBySection.put(sectionsName, actualTicketCount + number);
    }

    public int getTotalNumberOfAvalaibleTickets() {
        int numberOfAvalaibleTickets = 0;
        for (Integer numberOfTickets : avalaibleTicketsBySection.values()) {
            numberOfAvalaibleTickets += numberOfTickets;
        }
        return numberOfAvalaibleTickets;
    }
    
    public String getSport() {
    	return sport;
    }
}
