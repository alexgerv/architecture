package ca.ulaval.glo4003.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import ca.ulaval.glo4003.index.FilterCategoryException;
import ca.ulaval.glo4003.index.Indexable;
import ca.ulaval.glo4003.matchCatalog.MatchFilterCategories;

public class Match implements Indexable<MatchFilterCategories> {

    private static final String DATE_FORMAT_TEMPLATE = "yyyy-MM-dd_HH-mm-SS";

    private String sport;
    private String venue;
    private Date date;
    private String homeTeam;
    private String visitorTeam;
    private Sex sex;
    private List<Section> sections;

    public Match(String sport, String venue, Date date, String homeTeam, String visitorTeam, Sex sex,
                 List<Section> sections) {
        this.sport = sport;
        this.venue = venue;
        this.date = date;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.sex = sex;
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
        return sport;
    }

    public String getVenue() {
        return venue;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public Sex getSex() {
        return sex;
    }

    public Date getDate() {
        return date;
    }

    public List<Section> getTicketsBySection() {
        return sections;
    }

    @Override
    public String getFilterValueOfCategory(MatchFilterCategories category) {
        switch (category) {
        case SPORT:
            return sport;
        case VENUE:
            return venue;
        case DATE:
            return DateFormatUtils.format(date, DATE_FORMAT_TEMPLATE);
        case HOME_TEAM:
            return homeTeam;
        case VISITOR_TEAM:
            return visitorTeam;
        }
        throw new FilterCategoryException("FilterCategory does not conrrespond to an argument in match");
    }

    @Override
    public String getIdentifier() {
        String formatedDate = DateFormatUtils.format(date, DATE_FORMAT_TEMPLATE);
        return venue + "/" + formatedDate;
    }
}
