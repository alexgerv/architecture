package ca.ulaval.glo4003.model;

public class Ticket {

    private int id;
    private boolean available;

    private Doge doge;

    public Ticket(int id, boolean available, Doge doge) {
        this.id = id;
        this.available = available;
        this.doge = doge;
    }

    public boolean isAvailable() {
        return available;
    }

}
