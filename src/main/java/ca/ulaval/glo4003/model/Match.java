package ca.ulaval.glo4003.model;

import java.util.Date;
import java.util.Map;

public class Match {

    private String sport;
    private String venue;
    private Date date;
    private String homeTeam;
    private String visitorTeam;
    
    private Map<String, Integer> availableTicketsBySection;

    public Match(String sport, String venue, Date date, String homeTeam, String visitorTeam,
                 Map<String, Integer> avalaibleTicketsBySection) {
        this.sport = sport;
        this.venue = venue;
        this.date = date;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.availableTicketsBySection = avalaibleTicketsBySection;
    }

    public void addTicket(String sectionsName) {
        changeNumberOfTicketsBy(sectionsName, 1);
    }

    public void removeTicket(String sectionsName) {
        changeNumberOfTicketsBy(sectionsName, -1);
    }

    private void changeNumberOfTicketsBy(String sectionsName, int number) {
        int actualTicketCount = availableTicketsBySection.get(sectionsName);
        availableTicketsBySection.put(sectionsName, actualTicketCount + number);
    }
    
    public int getTotatNumberOfAvailableTickets(){
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
    
    public Date getDate() {
    	return date;
    }
    
    public Map<String, Integer> getAvailableTicketsBySection() {
    	return availableTicketsBySection;
    }
}
