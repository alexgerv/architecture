package ca.ulaval.glo4003.model;

import java.util.Map;

public class Section {

    private Map<Integer, Boolean> tickets;
    private String name;

    public Section(String sectionName, Map<Integer, Boolean> tickets) {
        this.tickets = tickets;
        this.name = sectionName;
    }

    public int getNumberOfAvailableTickets() {
        int numberOfAvailableTickets = 0;
        for (Boolean ticketIsAvailable : tickets.values()) {
            if (ticketIsAvailable) {
                numberOfAvailableTickets++;
            }
        }
        return numberOfAvailableTickets;
    }

    public String getName() {
        return name;
    }

}
