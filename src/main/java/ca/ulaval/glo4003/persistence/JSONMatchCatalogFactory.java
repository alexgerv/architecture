package ca.ulaval.glo4003.persistence;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.matchCatalog.MatchCatalogFactory;
import ca.ulaval.glo4003.matchCatalog.MatchFilterCategories;
import ca.ulaval.glo4003.matchCatalog.QueryResolver;
import ca.ulaval.glo4003.matchCatalog.index.Filter;
import ca.ulaval.glo4003.matchCatalog.index.Index;
import ca.ulaval.glo4003.matchCatalog.index.IndexWithList;
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
        Index<MatchFilterCategories> index = new IndexWithList<MatchFilterCategories>(filterListByCategories);
        QueryResolver<MatchFilterCategories> queryResolver = new QueryResolver<MatchFilterCategories>(index);

        return new JSONMatchCatalog(queryResolver, index, matchRepository);
    }
}
