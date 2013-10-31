package ca.ulaval.glo4003.web.viewmodels;

public class SectionViewModel {

    private String name;
    private int availableTickets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

}
