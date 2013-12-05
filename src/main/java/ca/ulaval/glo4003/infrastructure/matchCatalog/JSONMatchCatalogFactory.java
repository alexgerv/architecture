package ca.ulaval.glo4003.infrastructure.matchCatalog;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.domain.index.Filter;
import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;
import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalogFactory;
import ca.ulaval.glo4003.domain.matchCatalog.MatchIndex;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQueryResolver;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchDateFilter;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchHomeTeamFilter;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchSportFilter;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchVenueFilter;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchVisitorTeamFilter;

public class JSONMatchCatalogFactory implements MatchCatalogFactory {

    MatchRepository matchRepository;

    public JSONMatchCatalogFactory(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public MatchCatalog createMatchCatalog() {
        MatchIndex index = createMatchIndex();
        MatchQueryResolver queryResolver = new MatchQueryResolver(index);

        return new JSONMatchCatalog(queryResolver, index, matchRepository);
    }

    private MatchIndex createMatchIndex() {
        List<Filter<MatchAttribute, Match>> filterListByCategories = new ArrayList<Filter<MatchAttribute, Match>>();
        filterListByCategories.add(new MatchDateFilter());
        filterListByCategories.add(new MatchSportFilter());
        filterListByCategories.add(new MatchVenueFilter());
        filterListByCategories.add(new MatchHomeTeamFilter());
        filterListByCategories.add(new MatchVisitorTeamFilter());
        return new MatchIndex(filterListByCategories);
    }
}
