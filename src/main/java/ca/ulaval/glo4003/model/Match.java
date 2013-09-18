package ca.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match {
	private String sport;
	private String location;
	private Date date;
	private String homeTeam;
	private String visitorTeam;

	
	private List<Ticket> tickets;

	public Match() {
		tickets = new ArrayList<Ticket>();
	}
	
	public void addTicket(Ticket aTicket) {
		tickets.add(aTicket);
	}
	
	public void removeTicket(Ticket aTicket) {
		tickets.remove(aTicket);
	}
	public int totalTickets() {
		return tickets.size();
	}

	public boolean isTicketAvailable(Ticket aTicket) {
		return tickets.contains(aTicket);
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	
	public void setVisitorTeam(String visitorTeam) {
		this.visitorTeam = visitorTeam;
	}
	
}
