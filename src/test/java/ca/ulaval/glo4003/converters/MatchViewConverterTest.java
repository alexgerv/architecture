package ca.ulaval.glo4003.converters;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

public class MatchViewConverterTest {
    

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
        doReturn(new Date()).when(aMatch).getDate();
    }
    
    @Test
    public void whenConvertingAMapOfMatchToAMachViewModelCollectionTheConverterReturnsACollectionOfMatchViewModel() {
        Map<String, Match> anEntry= new HashMap<String, Match>();
        assertTrue(aMatchViewConverter.convert(anEntry) instanceof Collection<?>);
    }
    
    @Test
    public void whenConvertingAMapOfMatchToMatchViewModelCollectionTheIdentifiersAreAssignedToTheMatchViewModels(){
       HashSet<Entry<String, Match>> entrySet = new HashSet<Entry<String, Match>>();
       entrySet.add(aMatchEntry);
       doReturn(entrySet).when(entries).entrySet();
       doReturn(aMatch).when(aMatchEntry).getValue();
       
       aMatchViewConverter.convert(entries);
       verify(aMatchEntry,times(1)).getKey();
    }
    
    @Test
    public void whenConvertingAMatchToAMacthViewModelTheConverterReturnsAMatchViewModel() {
        Mockito.when(aMatch.getDate()).thenReturn(new Date());
        assertTrue(aMatchViewConverter.convert(aMatch) instanceof MatchViewModel);
    }
    
    @Test
    public void whenConvertingAMatchToAMachViewModelTheConverterUsesTheMatchAttributesToCreateTheModel() {
        Mockito.when(aMatch.getDate()).thenReturn(new Date());
        
        aMatchViewConverter.convert(aMatch);
        
        Mockito.verify(aMatch).getAvailableTicketsBySection();
        Mockito.verify(aMatch).getDate();
        Mockito.verify(aMatch).getHomeTeam();
        Mockito.verify(aMatch).getVisitorTeam();
        Mockito.verify(aMatch).getSport();
        Mockito.verify(aMatch).getTotatNumberOfAvailableTickets();
        Mockito.verify(aMatch).getVenue();
    }
}
