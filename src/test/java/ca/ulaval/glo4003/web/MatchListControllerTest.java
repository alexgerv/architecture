package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

public class MatchListControllerTest {

    private final String A_SECTION_NAME = "A";
    private final String A_VENUE = "Stade Telus";
    private final String A_DATE = "09/09/2013";
    private final String MATCH_IDENTIFIER = A_VENUE + "/" + A_DATE;

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private Match match;
    @Mock
    private Section section;
    @Mock
    private Model model;
    @Mock
    private MatchViewConverter matchConverter;
    @Mock
    private SectionViewConverter sectionConverter;
    @Mock
    private MatchViewModel matchViewModel;
    @Mock
    private SectionViewModel sectionViewModel;

    @InjectMocks
    private MatchListController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new MatchListController(matchRepository, matchConverter, sectionConverter);
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doReturn(section).when(match).getSectionByName(A_SECTION_NAME);
    }

    @Test
    public void getMatchGetsMatchFromRepository() {
        doReturn(matchViewModel).when(matchConverter).convert(match);
        controller.getMatch(A_VENUE, A_DATE, model);
        verify(matchRepository, times(1)).getMatchByIdentifier(MATCH_IDENTIFIER);
    }

    @Test
    public void canGetMatchList() {
        assertEquals("matchList", controller.matchList(model));
    }

    @Test
    public void canGetSectionDetails() {
        doReturn(sectionViewModel).when(sectionConverter).convert(section);
        assertEquals("sectionDetails", controller.getSection(A_VENUE, A_DATE, model, A_SECTION_NAME));
    }
}
