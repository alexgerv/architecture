package ca.ulaval.glo4003.domain.match;

import java.util.ArrayList;
import java.util.List;

public class Section {

    private List<Ticket> tickets = new ArrayList<Ticket>();
    private String name;
    private float price;
    private AdmissionType admissionType;
    private MatchInformation matchInformation;

    public Section(String sectionName, List<Ticket> tickets, MatchInformation matchInformation, float price,
                   AdmissionType admissionType) {
        this.tickets = tickets;
        this.name = sectionName;
        this.price = price;
        this.matchInformation = matchInformation;
        this.admissionType = admissionType;
    }

    public int getNumberOfAvailableTickets() {
        int numberOfAvailableTickets = 0;
        for (Ticket ticket : tickets)
            if (ticket.isAvailable()) {
                numberOfAvailableTickets++;
            }
        return numberOfAvailableTickets;
    }

    public String getName() {
        return name;
    }

    public boolean hasSameName(String sectionName) {
        return name.equals(sectionName);
    }

    public String getSport() {
        return matchInformation.getSport();
    }

    public String getDate() {
        return matchInformation.getFormatedDate();
    }

    public String getHomeTeam() {
        return matchInformation.getHomeTeam();
    }

    public String getVisitorTeam() {
        return matchInformation.getVisitorTeam();
    }

    public Sex getSex() {
        return matchInformation.getSex();
    }

    public float getPrice() {
        return price;
    }

    public String getVenue() {
        return matchInformation.getVenue();
    }

    public AdmissionType getAdmissionType() {
        return admissionType;
    }

    public List<Ticket> getAvailableTickets(int quantity) {
        assertTicketQuantityIsPositive(quantity);
        List<Ticket> availableTickets = new ArrayList<Ticket>();
        for (Ticket ticket : tickets) {
            if (availableTickets.size() < quantity) {
                if (ticket.isAvailable()) {
                    availableTickets.add(ticket);
                }
            } else {
                return availableTickets;
            }
        }
        if (availableTickets.size() == quantity) {
            return availableTickets;
        }
        throw new NoAvailableTicketsException("There are not enough available tickets.");
    }

    private void assertTicketQuantityIsPositive(int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("The ticket quantity must be >= 0,");
        }
    }

}
