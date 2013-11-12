package ca.ulaval.glo4003.model;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4003.index.FilterCategoryException;
import ca.ulaval.glo4003.index.Indexable;

public class Match implements Indexable<MatchAttribute> {

    private MatchInformations matchInformations;
    private List<Section> sections;

    public Match(String sport, String venue, Date date, String homeTeam, String visitorTeam, Sex sex,
                 List<Section> sections) {
        matchInformations = new MatchInformations(sport, venue, date, homeTeam, visitorTeam, sex);
        this.sections = sections;
    }

    public int getTotatNumberOfAvailableTickets() {
        return calculateTotalNumberOfAvailableTickets();
    }

    private int calculateTotalNumberOfAvailableTickets() {
        int numberOfAvailableTickets = 0;
        for (Section section : sections) {
            numberOfAvailableTickets += section.getNumberOfAvailableTickets();
        }
        return numberOfAvailableTickets;
    }

    public String getSport() {
        return matchInformations.getSport();
    }

    public String getVenue() {
        return matchInformations.getVenue();
    }

    public String getHomeTeam() {
        return matchInformations.getHomeTeam();
    }

    public String getVisitorTeam() {
        return matchInformations.getVisitorTeam();
    }

    public Sex getSex() {
        return matchInformations.getSex();
    }

    public String getDate() {
        return matchInformations.getFormatedDate();
    }

    public List<Section> getTicketsBySection() {
        return sections;
    }

    @Override
    public String getFilterValueOfCategory(MatchAttribute category) {
        switch (category) {
        case SPORT:
            return matchInformations.getSport();
        case VENUE:
            return matchInformations.getVenue();
        case DATE:
            return matchInformations.getFormatedDate();
        case HOME_TEAM:
            return matchInformations.getHomeTeam();
        case VISITOR_TEAM:
            return matchInformations.getVisitorTeam();
        }
        throw new FilterCategoryException("FilterCategory does not conrrespond to an argument in match");
    }

    @Override
    public String getIdentifier() {
        return matchInformations.getVenue() + "/" + matchInformations.getFormatedDate();
    }

    public Section getSectionByName(String sectionName) {
        for (Section section : sections) {
            if (section.hasSameName(sectionName)) {
                return section;
            }
        }
        throw new SectionNotFoundException("Section: " + sectionName + " was not found.");
    }

    public void buyTickets(String sectionName, int quantity) {
        Section section = getSectionByName(sectionName);
        section.buyTickets(quantity);
    }
}
