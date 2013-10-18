package ca.ulaval.glo4003.matchCatalog;

import java.util.List;

import ca.ulaval.glo4003.index.Filter;
import ca.ulaval.glo4003.index.IndexWithList;

public class MatchIndex extends IndexWithList<MatchFilterCategories> {

    public MatchIndex(List<Filter<MatchFilterCategories>> filterListByCategories) {
        super(filterListByCategories);
    }

}
