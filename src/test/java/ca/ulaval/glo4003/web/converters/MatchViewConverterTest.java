package ca.ulaval.glo4003.web.converters;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

public class MatchViewConverterTest {

    private static final String A_FORMATED_DATE = "2013-11-05 11h38";
    @Mock
    Match aMatch;

    @Mock
    Entry<String, Match> aMatchEntry;

    @Mock
    HashMap<String, Match> entries;
    
    MatchViewConverter aMatchViewConverter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aMatchViewConverter = new MatchViewConverter();
        doReturn(A_FORMATED_DATE).when(aMatch).getDate();
    }

    @Test
    public void whenConvertingAMapOfMatchToAMachViewModelCollectionTheConverterReturnsACollectionOfMatchViewModel() {
        Map<String, Match> anEntry = new HashMap<String, Match>();
        assertTrue(aMatchViewConverter.convert(anEntry) instanceof Collection<?>);
    }

    @Test
    public void whenConvertingAMapOfMatchToMatchViewModelCollectionTheIdentifiersAreAssignedToTheMatchViewModels() {
        HashSet<Entry<String, Match>> entrySet = new HashSet<Entry<String, Match>>();
        entrySet.add(aMatchEntry);
        doReturn(entrySet).when(entries).entrySet();
        doReturn(aMatch).when(aMatchEntry).getValue();

        aMatchViewConverter.convert(entries);
        verify(aMatchEntry, times(1)).getKey();
    }

    @Test
    public void whenConvertingAMatchToAMacthViewModelTheConverterReturnsAMatchViewModel() {
        Mockito.when(aMatch.getDate()).thenReturn(A_FORMATED_DATE);
        assertTrue(aMatchViewConverter.convert(aMatch) instanceof MatchViewModel);
    }

    @Test
    public void whenConvertingAMatchToAMachViewModelTheConverterUsesTheMatchAttributesToCreateTheModel() {
        Mockito.when(aMatch.getDate()).thenReturn(A_FORMATED_DATE);

        aMatchViewConverter.convert(aMatch);

        Mockito.verify(aMatch).getTicketsBySection();
        Mockito.verify(aMatch).getDate();
        Mockito.verify(aMatch).getHomeTeam();
        Mockito.verify(aMatch).getVisitorTeam();
        Mockito.verify(aMatch).getSport();
        Mockito.verify(aMatch).getTotatNumberOfAvailableTickets();
        Mockito.verify(aMatch).getVenue();
    }
}
