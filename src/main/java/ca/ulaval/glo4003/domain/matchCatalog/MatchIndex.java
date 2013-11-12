package ca.ulaval.glo4003.domain.matchCatalog;

import java.util.List;

import ca.ulaval.glo4003.domain.index.Filter;
import ca.ulaval.glo4003.domain.index.IndexWithList;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public class MatchIndex extends IndexWithList<MatchAttribute> {

    public MatchIndex(List<Filter<MatchAttribute>> filterListByCategories) {
        super(filterListByCategories);
    }

}
