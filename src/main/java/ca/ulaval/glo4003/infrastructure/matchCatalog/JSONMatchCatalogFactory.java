package ca.ulaval.glo4003.infrastructure.matchCatalog;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.domain.persistence.FileAccessor;
import ca.ulaval.glo4003.domain.index.Filter;
import ca.ulaval.glo4003.domain.match.MatchAttribute;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalogFactory;
import ca.ulaval.glo4003.domain.matchCatalog.MatchIndex;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQueryResolver;
import ca.ulaval.glo4003.domain.repository.MatchRepository;
import ca.ulaval.glo4003.infrastructure.persistence.JSONMatchMarshaller;

public class JSONMatchCatalogFactory implements MatchCatalogFactory {

    MatchRepository matchRepository;

    public JSONMatchCatalogFactory(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public MatchCatalog createMatchCatalog() {
        List<Filter<MatchAttribute>> filterListByCategories = new ArrayList<Filter<MatchAttribute>>();
        for (MatchAttribute category : MatchAttribute.values()) {
            filterListByCategories.add(new Filter<MatchAttribute>(category));
        }
        MatchIndex index = new MatchIndex(filterListByCategories);
        MatchQueryResolver queryResolver = new MatchQueryResolver(index);
        JSONMatchMarshaller converter = new JSONMatchMarshaller();
        FileAccessor fileAccessor = new FileAccessor();

        return new JSONMatchCatalog(queryResolver, index, matchRepository, converter, fileAccessor);
    }
}
