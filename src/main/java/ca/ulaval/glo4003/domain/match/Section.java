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

    public List<Ticket> reserveTickets(int quantity) {
        List<Ticket> tickets = new ArrayList<Ticket>();
        assertTicketQuantityIsPositive(quantity);
        assertEnoughTicketsAvailableFoAskedQuantity(quantity);

        for (int i = 0; i < quantity; i++) {
            tickets.add(reserveTicket());
        }
        return tickets;
    }

    private void assertTicketQuantityIsPositive(int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("The ticket quantity must be >= 0,");
        }
    }

    private void assertEnoughTicketsAvailableFoAskedQuantity(int quantity) {
        if (quantity > getNumberOfAvailableTickets()) {
            throw new NotEnoughAvailableTicketsException("The asked quantity of tickets exceed the available tickets for this section.");
        }
    }

    protected Ticket reserveTicket() {
        for (Ticket ticket : tickets) {
            if (ticket.isAvailable()) {
                ticket.reserve();
                return ticket;
            }
        }
        throw new NoAvailableTicketsException("There are no tickets available.");
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

}
