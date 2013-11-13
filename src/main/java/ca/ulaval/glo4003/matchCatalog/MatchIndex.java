package ca.ulaval.glo4003.matchCatalog;

import java.util.List;

import ca.ulaval.glo4003.index.Filter;
import ca.ulaval.glo4003.index.IndexWithList;
import ca.ulaval.glo4003.model.MatchAttribute;

public class MatchIndex extends IndexWithList<MatchAttribute> {

    public MatchIndex(List<Filter<MatchAttribute>> filterListByCategories) {
        super(filterListByCategories);
    }

}
