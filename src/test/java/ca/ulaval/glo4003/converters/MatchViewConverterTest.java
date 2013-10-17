package ca.ulaval.glo4003.converters;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

public class MatchViewConverterTest {

    @Mock
    Match aMatch;
    
    MatchViewConverter aMatchViewConverter;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aMatchViewConverter = new MatchViewConverter();
    }
    
    @Test
    public void whenConvertingAMapOfMatchToAMachViewModelCollectionTheConverterReturnsACollectionOfMatchViewModel() {
        Map<String, Match> anEntry= new HashMap<String, Match>();
        assertTrue(aMatchViewConverter.convert(anEntry) instanceof Collection<?>);
    }
    
    @Test
    public void whenConvertingAMatchToAMacthViewModelTheConverterReturnsAMatchViewModel() {
        assertTrue(aMatchViewConverter.convert(aMatch) instanceof MatchViewModel);
    }
    
    @Test
    public void whenConvertingAMatchToAMachViewModelTheConverterUsesTheMatchAttributesToCreateTheModel() {
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
