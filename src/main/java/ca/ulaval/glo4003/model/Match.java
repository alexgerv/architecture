package ca.ulaval.glo4003.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import ca.ulaval.glo4003.index.FilterCategoryException;
import ca.ulaval.glo4003.index.Indexable;
import ca.ulaval.glo4003.matchCatalog.MatchFilterCategories;

public class Match implements Indexable<MatchFilterCategories> {

    private static final String DATE_FORMAT_TEMPLATE = "yyyy-MM-dd_HH-mm-SS";

    private Doge doge;
    private List<Section> sections;

    public Match(String sport, String venue, Date date, String homeTeam, String visitorTeam, Sex sex,
                 List<Section> sections) {
        doge = new Doge(sport, venue, date, homeTeam, visitorTeam, sex);
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
        return doge.getSport();
    }

    public String getVenue() {
        return doge.getVenue();
    }

    public String getHomeTeam() {
        return doge.getHomeTeam();
    }

    public String getVisitorTeam() {
        return doge.getVisitorTeam();
    }

    public Sex getSex() {
        return doge.getSex();
    }

    public Date getDate() {
        return doge.getDate();
    }

    public List<Section> getTicketsBySection() {
        return sections;
    }

    @Override
    public String getFilterValueOfCategory(MatchFilterCategories category) {
        switch (category) {
        case SPORT:
            return doge.getSport();
        case VENUE:
            return doge.getVenue();
        case DATE:
            return DateFormatUtils.format(doge.getDate(), DATE_FORMAT_TEMPLATE);
        case HOME_TEAM:
            return doge.getHomeTeam();
        case VISITOR_TEAM:
            return doge.getVisitorTeam();
        }
        throw new FilterCategoryException("FilterCategory does not conrrespond to an argument in match");
    }

    @Override
    public String getIdentifier() {
        String formatedDate = DateFormatUtils.format(doge.getDate(), DATE_FORMAT_TEMPLATE);
        return doge.getVenue() + "/" + formatedDate;
    }

    public Section getSectionByName(String sectionName) {
        for (Section section : sections) {
            if (section.isSameName(sectionName)) {
                return section;
            }
        }
        throw new SectionNotFoundException("Section: " + sectionName + " was not found.");
    }
}
