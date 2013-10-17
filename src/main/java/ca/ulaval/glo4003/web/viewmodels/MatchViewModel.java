package ca.ulaval.glo4003.web.viewmodels;

import java.util.Date;
import java.util.Map;

public class MatchViewModel {
	public String matchIdentifier;
    public String sport;
    public String venue;
    public String date;
	public String homeTeam;
	public String visitorTeam;
	public Map<String, Integer> availableTicketsBySection;
	public int totalNumberOfAvailableTickets;
	
	public String getMatchIdentifier() {
		return matchIdentifier;
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
    
    public String getDate() {
		return date;
	}
    
	public void setDate(String date) {
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
	
	public Map<String, Integer> getAvailableTicketsBySection() {
		return availableTicketsBySection;
	}

	public void setAvailableTicketsBySection(
			Map<String, Integer> availableTicketsBySection) {
		this.availableTicketsBySection = availableTicketsBySection;
	}
	
	public int getTotalNumberOfAvailableTickets() {
		return totalNumberOfAvailableTickets;
	}

	public void setTotalNumberOfAvailableTickets(int totalNumberOfAvailableTickets) {
		this.totalNumberOfAvailableTickets = totalNumberOfAvailableTickets;
	}
}

