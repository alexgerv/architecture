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

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchRepository;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

public class MatchListControllerTest {

    private final String A_VENUE = "Stade Telus";
    private final String A_DATE = "09/09/2013";
    private final String MATCH_IDENTIFIER = A_VENUE + "/" + A_DATE;

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private Match match;
    @Mock
    private Model model;
    @Mock
    private MatchViewConverter matchConverter;
    @Mock
    private MatchViewModel matchViewModel;

    @InjectMocks
    private MatchListController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new MatchListController(matchRepository, matchConverter);
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
    }

    @Test
    public void getMatchGetsMatchFromRepository() {
        doReturn(matchViewModel).when(matchConverter).convert(match);
        controller.getMatch(A_VENUE, A_DATE, model);
        verify(matchRepository, times(1)).getMatchByIdentifier(MATCH_IDENTIFIER);
    }

    @Test
    public void cangetMatchList() {
        assertEquals("matchList", controller.matchList(model));
    }
}
