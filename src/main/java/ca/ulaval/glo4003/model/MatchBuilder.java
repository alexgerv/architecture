package ca.ulaval.glo4003.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4003.searchEngine.Sex;

public class MatchBuilder {

    private static final String SPORT_KEY = "Sport";
    private static final String DATE_KEY = "Date";
    private static final String VENUE_KEY = "Venue";
    private static final String HOME_TEAM_KEY = "HomeTeam";
    private static final String VISITOR_TEAM_KEY = "VisitorTeam";
    private static final String SEX_KEY = "Sex";
    private static final String AVAILABLE_SECTIONS_KEY = "AvailableSections";

    private Map<String, Object> matchAttributes = new HashMap<String, Object>();

    public MatchBuilder() {
        this.setupMatchAttributes();
    }

    private void setupMatchAttributes() {
        this.matchAttributes.put(SPORT_KEY, null);
        this.matchAttributes.put(DATE_KEY, null);
        this.matchAttributes.put(VENUE_KEY, null);
        this.matchAttributes.put(HOME_TEAM_KEY, null);
        this.matchAttributes.put(VISITOR_TEAM_KEY, null);
        this.matchAttributes.put(SEX_KEY, null);
        this.matchAttributes.put(AVAILABLE_SECTIONS_KEY, null);
    }

    public Match build() {
        this.validateAttributes();
        Match newMatch = this.buildMatch();
        this.setupMatchAttributes();
        return newMatch;
    }

    private void validateAttributes() {
        for (String attributeName : this.matchAttributes.keySet()) {
            if (this.matchAttributes.get(attributeName) == null) {
                throw new MatchBuilderException(attributeName);
            }
        }
    }

    private Match buildMatch() {
        Match newMatch =
                         new Match((String) (matchAttributes.get(SPORT_KEY)),
                                   (String) (matchAttributes.get(VENUE_KEY)), (Date) (matchAttributes.get(DATE_KEY)),
                                   (String) (matchAttributes.get(HOME_TEAM_KEY)),
                                   (String) (matchAttributes.get(VISITOR_TEAM_KEY)),
                                   (Sex) (matchAttributes.get(SEX_KEY)),
                                   (Map<String, Integer>) (matchAttributes.get(AVAILABLE_SECTIONS_KEY)));
        return newMatch;
    }

    public MatchBuilder createSection(String sectionsName, int numberOfTickets) {
        if (this.matchAttributes.get(AVAILABLE_SECTIONS_KEY) == null) {
            Map<String, Integer> section = new HashMap<String, Integer>();
            section.put(sectionsName, numberOfTickets);
            this.matchAttributes.put(AVAILABLE_SECTIONS_KEY, section);
        } else {
            ((Map<String, Integer>) this.matchAttributes.get(AVAILABLE_SECTIONS_KEY)).put(sectionsName, numberOfTickets);
        }
        return this;
    }

    public MatchBuilder setSport(String sport) {
        this.matchAttributes.put(SPORT_KEY, sport);
        return this;
    }

    public MatchBuilder setDate(Date date) {
        this.matchAttributes.put(DATE_KEY, date);
        return this;
    }

    public MatchBuilder setVenue(String venue) {
        this.matchAttributes.put(VENUE_KEY, venue);
        return this;
    }

    public MatchBuilder setHomeTeam(String homeTeam) {
        this.matchAttributes.put(HOME_TEAM_KEY, homeTeam);
        return this;
    }

    public MatchBuilder setVisitorTeam(String visitorTeam) {
        this.matchAttributes.put(VISITOR_TEAM_KEY, visitorTeam);
        return this;
    }

    public MatchBuilder setSex(String sex) {
        this.matchAttributes.put(SEX_KEY, sex);
        return this;
    }
}
