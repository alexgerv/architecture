package ca.ulaval.glo4003.persistence.json;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.index.Filter;
import ca.ulaval.glo4003.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.matchCatalog.MatchCatalogFactory;
import ca.ulaval.glo4003.matchCatalog.MatchFilterCategories;
import ca.ulaval.glo4003.matchCatalog.MatchIndex;
import ca.ulaval.glo4003.matchCatalog.MatchQueryResolver;
import ca.ulaval.glo4003.repository.MatchRepository;

public class JSONMatchCatalogFactory implements MatchCatalogFactory {

    MatchRepository matchRepository;

    public JSONMatchCatalogFactory(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public MatchCatalog createMatchCatalog() {
        List<Filter<MatchFilterCategories>> filterListByCategories = new ArrayList<Filter<MatchFilterCategories>>();
        for (MatchFilterCategories category : MatchFilterCategories.values()) {
            filterListByCategories.add(new Filter<MatchFilterCategories>(category));
        }
        MatchIndex index = new MatchIndex(filterListByCategories);
        MatchQueryResolver queryResolver = new MatchQueryResolver(index);

        return new JSONMatchCatalog(queryResolver, index, matchRepository);
    }
}
