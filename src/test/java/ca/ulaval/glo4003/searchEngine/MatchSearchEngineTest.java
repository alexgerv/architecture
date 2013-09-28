package ca.ulaval.glo4003.searchEngine;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.model.Match;

public class MatchSearchEngineTest {

    private static final String A_SPORT = "Soccer";
    private static final String ANOTHER_SPORT = "Football";
    private static final String UNQUERIED_SPORT = "Hockey";
    private static final int A_QUERIED_INDEX = 0;
    private static final int ANOTHER_QUERIED_INDEX = 1;
    private static final int UNQUERIED_INDEX = 2;
    private static final String A_VENUE = "ULaval";
    private static final String UNQUERIED_VENUE = "Sherbrook";

    Map<MatchFilter, List<Object>> aMatchQueryContent;

    MatchSearchEngine aSearchEngine;

    @Mock
    private Match aMatch;
    @Mock
    private Match anotherMatch;
    @Mock
    private Match unqueriedMatch;
    @Mock
    private MatchQuery aMatchQuery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aSearchEngine = new MatchSearchEngine();
    }

    @Test
    public void returnOnlyIndexesWithDesiredSportWhenQueryingForASpecificSport() {
        aMatchQueryContent = new HashMap<MatchFilter, List<Object>>();
        List<Object> filterValues = new ArrayList<Object>();
        filterValues.add(A_SPORT);
        aMatchQueryContent.put(MatchFilter.SPORT, filterValues);

        doReturn(A_SPORT).when(aMatch).getSport();
        doReturn(UNQUERIED_SPORT).when(unqueriedMatch).getSport();
        doReturn(aMatchQueryContent).when(aMatchQuery).getQuery();

        aSearchEngine.add(aMatch, A_QUERIED_INDEX);
        aSearchEngine.add(unqueriedMatch, UNQUERIED_INDEX);
        Set<Integer> returnedIndexes = aSearchEngine.getIndexesFromQuery(aMatchQuery);

        boolean returnedIntexContainsOnlyDesiredSports = returnedIndexes.size() == 1 && returnedIndexes.contains(0);
        assertTrue(returnedIntexContainsOnlyDesiredSports);
    }

    @Test
    public void returnOnlyIndexesWithDesiredSportWhenQueryingForMultipleSpecificSports() {
        aMatchQueryContent = new HashMap<MatchFilter, List<Object>>();
        List<Object> filterValues = new ArrayList<Object>();
        filterValues.add(A_SPORT);
        filterValues.add(ANOTHER_SPORT);
        aMatchQueryContent.put(MatchFilter.SPORT, filterValues);

        doReturn(A_SPORT).when(aMatch).getSport();
        doReturn(ANOTHER_SPORT).when(anotherMatch).getSport();
        doReturn(UNQUERIED_SPORT).when(unqueriedMatch).getSport();
        doReturn(aMatchQueryContent).when(aMatchQuery).getQuery();

        aSearchEngine.add(aMatch, A_QUERIED_INDEX);
        aSearchEngine.add(anotherMatch, ANOTHER_QUERIED_INDEX);
        Set<Integer> returnedIndexes = aSearchEngine.getIndexesFromQuery(aMatchQuery);

        boolean returnedIntexContainsOnlyDesiredSports =
                                                         returnedIndexes.size() == 2
                                                                 && returnedIndexes.contains(A_QUERIED_INDEX)
                                                                 && returnedIndexes.contains(ANOTHER_QUERIED_INDEX);
        assertTrue(returnedIntexContainsOnlyDesiredSports);
    }

    @Ignore
    @Test
    public void returnOnlyIndexesWithDesiredFiltersWhenQueryingWithMultipleFilters() {
        aMatchQueryContent = new HashMap<MatchFilter, List<Object>>();
        List<Object> sportValues = new ArrayList<Object>();
        sportValues.add(A_SPORT);
        aMatchQueryContent.put(MatchFilter.SPORT, sportValues);

        List<Object> venueValues = new ArrayList<Object>();
        venueValues.add(A_VENUE);
        aMatchQueryContent.put(MatchFilter.VENUE, venueValues);

        doReturn(A_SPORT).when(aMatch).getSport();
        doReturn(A_VENUE).when(aMatch).getVenue();
        doReturn(A_SPORT).when(anotherMatch).getSport();
        doReturn(A_VENUE).when(anotherMatch).getVenue();
        doReturn(A_SPORT).when(unqueriedMatch).getSport();
        doReturn(UNQUERIED_VENUE).when(unqueriedMatch).getVenue();

        aSearchEngine.add(aMatch, A_QUERIED_INDEX);
        aSearchEngine.add(anotherMatch, ANOTHER_QUERIED_INDEX);
        aSearchEngine.add(unqueriedMatch, UNQUERIED_INDEX);
        Set<Integer> returnedIndexes = aSearchEngine.getIndexesFromQuery(aMatchQuery);

        boolean returnedIntexContainsOnlyDesiredSports =
                                                         returnedIndexes.size() == 1
                                                                 && returnedIndexes.contains(A_QUERIED_INDEX);
        assertTrue(returnedIntexContainsOnlyDesiredSports);
    }
}
