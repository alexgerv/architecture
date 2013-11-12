package ca.ulaval.glo4003.model;

public class Ticket {

    private int id;
    private boolean available;

    private MatchInformation matchInformations;

    public Ticket(int id, boolean available, MatchInformation matchInformations) {
        this.id = id;
        this.available = available;
        this.matchInformations = matchInformations;
    }

    public boolean isAvailable() {
        return available;
    }

    public void buy() {
        if (!isAvailable()) {
            throw new UnavailableTicketException("The ticket is unavailable.");
        }
        available = false;
    }

}
