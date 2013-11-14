package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.NoAvailableTicketsException;
import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.repository.MatchRepository;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

public class TicketPurchaseControllerTest {

    private static final String A_VENUE = "Stade Telus";
    private static final String A_DATE = "09/09/2013";
    private static final String MATCH_IDENTIFIER = A_VENUE + "/" + A_DATE;
    private static final String A_SECTION_NAME = "A";
    private static final int A_NUMBER_OF_TICKET_TO_BUY = 10;
    private static final String QUANTITY_IDENTIFIER = "quantity";
    private static final String SECTION_IDENTIFIER = "section";

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private Match match;
    @Mock
    private Section section;
    @Mock
    private Model model;
    @Mock
    private SectionViewConverter sectionConverter;
    @Mock
    private SectionViewModel sectionViewModel;

    @InjectMocks
    private TicketPurchaseController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new TicketPurchaseController(matchRepository, sectionConverter);
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doReturn(section).when(match).getSectionByName(A_SECTION_NAME);
        doReturn(sectionViewModel).when(sectionConverter).convert(section);
    }

    @Test
    public void whenReviewingAPurchaseTheQuantityIsPassedToTheView() {
        controller.reviewSelectedTicketsForSection(A_VENUE, A_DATE, A_SECTION_NAME, A_NUMBER_OF_TICKET_TO_BUY, model);
        verify(model, times(1)).addAttribute(QUANTITY_IDENTIFIER, A_NUMBER_OF_TICKET_TO_BUY);
    }
    
    @Test
    public void whenReviewingAPurchaseTheSectionInformationsArePassedToTheView(){
        controller.reviewSelectedTicketsForSection(A_VENUE, A_DATE, A_SECTION_NAME, A_NUMBER_OF_TICKET_TO_BUY, model);
        verify(model, times(1)).addAttribute(SECTION_IDENTIFIER, sectionViewModel);
    }
    
    @Test
    public void whenPurchasingTicketsThatAreAvailableWeAreRedirectedToTheHomeView() {  
        assertEquals("home", controller.purchaseSelectedTicketsForSection(A_VENUE, A_DATE, A_SECTION_NAME, A_NUMBER_OF_TICKET_TO_BUY, model));
    }
    
    @Test
    public void whenPurchasingTicketsThatAreNotAvailableWeAreRedirectedToTheSectionDetailsView() {
        doThrow(NoAvailableTicketsException.class).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        assertEquals("sectionDetails", controller.purchaseSelectedTicketsForSection(A_VENUE, A_DATE, A_SECTION_NAME, A_NUMBER_OF_TICKET_TO_BUY, model));
    }
}
