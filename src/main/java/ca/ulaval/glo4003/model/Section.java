package ca.ulaval.glo4003.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Section {

    private List<Ticket> tickets = new ArrayList<Ticket>();
    private String name;
    private float price;
    private AdmissionType admissionType;
    private Doge doge;

    public Section(String sectionName, List<Ticket> tickets, Doge doge, float price, AdmissionType admissionType) {
        this.tickets = tickets;
        this.name = sectionName;
        this.price = price;
        this.doge = doge;
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
        return doge.getSport();
    }

    public String getDate() {
        return doge.getFormatedDate();
    }

    public String getHomeTeam() {
        return doge.getHomeTeam();
    }

    public String getVisitorTeam() {
        return doge.getVisitorTeam();
    }

    public Sex getSex() {
        return doge.getSex();
    }

    public float getPrice() {
        return price;
    }

    public String getVenue() {
        return doge.getVenue();
    }

    public AdmissionType getAdmissionType() {
        return admissionType;
    }

    public void buyTickets(int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("The ticket quantity must be >= 0,");
        }
        for (int i = 0; i < quantity; i++) {
            buyTicket();
        }
    }

    private void buyTicket() {
        Iterator<Ticket> iterator = tickets.iterator();

        for (Ticket ticket : tickets) {
            if (ticket.isAvailable()) {
                ticket.buy();
                return;
            }
        }
        throw new NoAvailableTicketsException("There are no tickets available.");
    }
}
