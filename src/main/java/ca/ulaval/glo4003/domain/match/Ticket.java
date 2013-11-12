package ca.ulaval.glo4003.domain.match;

public class Ticket {

    private int id;
    private boolean available;

    private MatchInformation matchInformation;

    public Ticket(int id, boolean available, MatchInformation matchInformation) {
        this.id = id;
        this.available = available;
        this.matchInformation = matchInformation;
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
