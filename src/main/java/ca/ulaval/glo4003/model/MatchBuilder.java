package ca.ulaval.glo4003.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;

public class MatchBuilder {

    private String sport;
    private Date date;
    private String venue;
    private String homeTeam;
    private String visitorTeam;
    private HashMap<String, Integer> avalaibleTicketsBySection;

    public Match build() {
        validateState();
        return new Match(sport, venue, date, homeTeam, visitorTeam, avalaibleTicketsBySection);
    }

    private void validateState() {
        for (Field field : MatchBuilder.class.getDeclaredFields()) {
            try {
                if (field.get(this) == null) {
                    throw new MatchBuilderException(field.getName());
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace(); // Decider de ce qu'on fait avec ça
            }
        }

    }

    public void createSection(String sectionsName, int numberOfTickets) {
        avalaibleTicketsBySection.put(sectionsName, numberOfTickets);
    }

    public MatchBuilder setSport(String sport) {
        this.sport = sport;
        return this;
    }

    public MatchBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public MatchBuilder setVenue(String venue) {
        this.venue = venue;
        return this;
    }

    public MatchBuilder setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
        return this;
    }

    public MatchBuilder setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
        return this;
    }
}
