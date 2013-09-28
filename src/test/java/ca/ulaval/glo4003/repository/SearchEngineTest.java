package ca.ulaval.glo4003.repository;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.model.Match;

public class SearchEngineTest {

    MatchSearchEngine aSearchEngine;

    @Mock
    private Match match;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aSearchEngine = new MatchSearchEngine();
    }

}
