package ca.ulaval.glo4003.domain.match;

public class Ticket {

    private int id;
    private boolean available;

    private MatchInformations matchInformations;

    public Ticket(int id, boolean available, MatchInformations matchInformations) {
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
