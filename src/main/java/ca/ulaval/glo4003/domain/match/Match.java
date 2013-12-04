package ca.ulaval.glo4003.domain.match;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4003.domain.index.Indexable;

public class Match implements Indexable<MatchAttribute> {

    private MatchInformation matchInformation;
    private List<Section> sections;

    public Match(String sport, String venue, Date date, String homeTeam, String visitorTeam, Sex sex,
                 List<Section> sections) {
        matchInformation = new MatchInformation(sport, venue, date, homeTeam, visitorTeam, sex);
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
        return matchInformation.getSport();
    }

    public String getVenue() {
        return matchInformation.getVenue();
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

    public String getDate() {
        return matchInformation.getFormatedDate();
    }

    public List<Section> getTicketsBySection() {
        return sections;
    }

    @Override
    public String getIdentifier() {
        return matchInformation.getVenue() + "/" + matchInformation.getFormatedDate();
    }

    public Section getSectionByName(String sectionName) {
        for (Section section : sections) {
            if (section.hasSameName(sectionName)) {
                return section;
            }
        }
        throw new SectionNotFoundException("Section: " + sectionName + " was not found.");
    }

    @Override
    public String toString() {
        return String.format("%s's %s, %s VS %s at %s on %s",
                             matchInformation.getSex(),
                             matchInformation.getSport(),
                             matchInformation.getHomeTeam(),
                             matchInformation.getVisitorTeam(),
                             matchInformation.getVenue(),
                             matchInformation.getFormatedDate());
    }

    public List<Ticket> reserveTickets(int quantity, String sectionName) {
        Section section = getSectionByName(sectionName);
        return section.reserveTickets(quantity);
    }
}
