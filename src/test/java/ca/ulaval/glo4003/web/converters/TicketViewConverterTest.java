package ca.ulaval.glo4003.web.converters;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.web.viewmodels.TicketViewModel;

public class TicketViewConverterTest {

    private static final String A_FORMATED_DATE = "2013-11-05 11h38";

    @Mock
    Ticket aTicket;

    TicketViewConverter aTicketViewConverter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        aTicketViewConverter = new TicketViewConverter();
        doReturn(A_FORMATED_DATE).when(aTicket).getDate();
    }

    @Test
    public void whenConvertingATicketToATicketViewModelTheConverterReturnsATicketViewModel() {
        Mockito.when(aTicket.getDate()).thenReturn(A_FORMATED_DATE);
        assertTrue(aTicketViewConverter.convert(aTicket) instanceof TicketViewModel);
    }

    @Test
    public void whenConvertingAMapOfMatchToAMachViewModelCollectionTheConverterReturnsACollectionOfMatchViewModel() {
        List<Ticket> entries = new ArrayList<Ticket>();
        entries.add(aTicket);
        assertTrue(aTicketViewConverter.convert(entries) instanceof List<?>);
    }

    @Test
    public void whenConvertingATicketToATicketViewModelTheConverterUsesTheTicketAttributesToCreateTheModel() {
        Mockito.when(aTicket.getDate()).thenReturn(A_FORMATED_DATE);

        aTicketViewConverter.convert(aTicket);

        Mockito.verify(aTicket).getAdmissionType();
        Mockito.verify(aTicket).getDate();
        Mockito.verify(aTicket).getHomeTeam();
        Mockito.verify(aTicket).getPrice();
        Mockito.verify(aTicket).getSex();
        Mockito.verify(aTicket).getSport();
        Mockito.verify(aTicket).getVenue();
        Mockito.verify(aTicket).getVisitorTeam();
        Mockito.verify(aTicket).getID();

    }
}
