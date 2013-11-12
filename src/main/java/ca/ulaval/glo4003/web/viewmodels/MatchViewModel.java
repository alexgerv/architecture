package ca.ulaval.glo4003.web.viewmodels;

import java.util.List;

import ca.ulaval.glo4003.domain.match.Sex;

public class MatchViewModel {

    private String matchIdentifier;
    private String sport;
    private String venue;
    private String date;
    private String homeTeam;
    private String visitorTeam;
    private Sex sex;
    private List<SectionViewModel> sections;
    private int totalNumberOfAvailableTickets;

    public String getMatchIdentifier() {
        return matchIdentifier;
    }

    public void setMatchIdentifier(String identifier) {
        this.matchIdentifier = identifier;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public List<SectionViewModel> getAvailableTicketsBySection() {
        return sections;
    }

    public void setTicketsBySection(List<SectionViewModel> ticketsBySection) {
        this.sections = ticketsBySection;
    }

    public int getTotalNumberOfAvailableTickets() {
        return totalNumberOfAvailableTickets;
    }

    public void setTotalNumberOfTickets(int totalNumberOfAvailableTickets) {
        this.totalNumberOfAvailableTickets = totalNumberOfAvailableTickets;
    }
}
