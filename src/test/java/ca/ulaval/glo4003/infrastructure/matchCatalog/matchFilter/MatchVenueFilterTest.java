package ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.matchCatalog.matchFilter.MatchVenueFilter;
import ca.ulaval.glo4003.domain.matchCatalog.matchFilter.MatchVenueFilter;


public class MatchVenueFilterTest {

    private static final String A_MATCH_IDENTIFIER = "/a/unique/identifier";
    private static final String ANOTHER_MATCH_IDENTIFIER = "/another/unique/identifier";
    private static final String A_VENUE = "Stade Telus";

    @Mock
    private Match aMatch;

    @Mock
    private Match anotherMatch;

    private MatchVenueFilter matchVenueFilter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        matchVenueFilter = new MatchVenueFilter();
    }
    
    @Test
    public void whenNoMatchesOfAVenueHaveBeenAddedToTheFilterThereIsNoIdentifiersForThisValue(){
        List<String> correspondingIdentifiers = matchVenueFilter.getIdentifiersFor(A_VENUE);
        assertTrue(correspondingIdentifiers.size() == 0);
    }

    @Test
    public void whenAddingAMatchItAccessesTheMatchsVenueAttribute() {
        matchVenueFilter.add(aMatch);
        verify(aMatch, times(1)).getVenue();
    }

    @Test
    public void whenAddingAVenueItsIdentifierIsSavedForTheRightFilterValue() {
        doReturn(A_MATCH_IDENTIFIER).when(aMatch).getIdentifier();
        doReturn(A_VENUE).when(aMatch).getVenue();

        matchVenueFilter.add(aMatch);

        List<String> correspondingIdentifiers = matchVenueFilter.getIdentifiersFor(A_VENUE);

        assertTrue(correspondingIdentifiers.contains(A_MATCH_IDENTIFIER));
    }

    @Test
    public void whenAddingTwoMatchesWithSameVenueValueBothMatchesAreFoundForThatValue() {
        doReturn(A_MATCH_IDENTIFIER).when(aMatch).getIdentifier();
        doReturn(A_VENUE).when(aMatch).getVenue();
        matchVenueFilter.add(aMatch);

        doReturn(ANOTHER_MATCH_IDENTIFIER).when(anotherMatch).getIdentifier();
        doReturn(A_VENUE).when(anotherMatch).getVenue();
        matchVenueFilter.add(anotherMatch);

        List<String> correspondingIdentifiers = matchVenueFilter.getIdentifiersFor(A_VENUE);

        boolean theTwoIdentifiersAreInTheFilter = correspondingIdentifiers.contains(A_MATCH_IDENTIFIER)
                                                  && correspondingIdentifiers.contains(ANOTHER_MATCH_IDENTIFIER);
        assertTrue(theTwoIdentifiersAreInTheFilter);
    }

}
