package ca.ulaval.glo4003.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.fileAccess.JSONMatchConverter;
import ca.ulaval.glo4003.model.Match;

public class MatchRepositoryTest {

    private static final Integer[] VALID_MATCH_INDEXES = { 0, 1, 2, 3, 4, };
    private static final Integer[] INVALID_MATCH_INDEXES = { 5 };
    private static final String AN_ERROR_MESSAGE = "Error";
    private static final Integer VALID_MATCH_ID_TO_ADD = 8;
    private static final Integer INVALID_MATCH_ID_TO_ADD = 9;
    private static final String ROOT_REPOSITORY = "./matches/";

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private MatchRepository aMatchRepository;

    @Mock
    private JSONMatchConverter JSONMatchConverter;
    @Mock
    private Match aMatch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        System.setErr(new PrintStream(errContent));
        aMatchRepository = new MatchRepository(JSONMatchConverter);
    }

    @After
    public void cleanup() {
        System.setOut(null);
    }

    @Test
    public void whenGettingAListOfMatchesIfAMatchIsNotLoadedThenItIsLoaded() throws FileNotFoundException {
        String expectedPathOfFileToLoad = ROOT_REPOSITORY + VALID_MATCH_INDEXES[0];

        aMatchRepository.getMatchesById(VALID_MATCH_INDEXES);

        verify(JSONMatchConverter).load(expectedPathOfFileToLoad);
    }

    @Test
    public void whenGettingAListOfMatchesIfAMatchIsLoadedThenItIsReturned() throws FileNotFoundException {
        doReturn(aMatch).when(JSONMatchConverter).load(anyString());
        aMatchRepository.getMatchesById(VALID_MATCH_INDEXES);
        reset(JSONMatchConverter);

        aMatchRepository.getMatchesById(VALID_MATCH_INDEXES);

        verifyZeroInteractions(JSONMatchConverter);
    }

    @Test
    public void whenGettingAMatchAnyErrorsArePrintToTheErrorStream() throws FileNotFoundException {
        doThrow(new FileNotFoundException(AN_ERROR_MESSAGE)).when(JSONMatchConverter).load(anyString());

        aMatchRepository.getMatchesById(INVALID_MATCH_INDEXES);

        assertEquals(AN_ERROR_MESSAGE + "\n", errContent.toString());
    }

    @Test
    public void whenAddingNewMatchItIsSaved() throws IOException {
        aMatchRepository.add(aMatch, VALID_MATCH_ID_TO_ADD);

        verify(JSONMatchConverter).save(aMatch, ROOT_REPOSITORY + VALID_MATCH_ID_TO_ADD);
    }

    @Test
    public void whenNewMatchIsSavedInTheRepositoryAnyErrorsArePrintedToTheErrorStream() throws IOException {
        doThrow(new FileNotFoundException(AN_ERROR_MESSAGE)).when(JSONMatchConverter)
                                                            .save(aMatch, ROOT_REPOSITORY + INVALID_MATCH_ID_TO_ADD);

        aMatchRepository.add(aMatch, INVALID_MATCH_ID_TO_ADD);

        assertEquals(AN_ERROR_MESSAGE + "\n", errContent.toString());
    }

}
