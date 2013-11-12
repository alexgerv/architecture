package ca.ulaval.glo4003.infrastructure.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.infrastructure.persistence.JSONMatchMarshaller;
import ca.ulaval.glo4003.infrastructure.repository.JSONMatchRepository;

public class JSONMatchRepositoryTest {

    private static final List<String> VALID_MATCH_INDENTIFIERS = Arrays.asList("stadeTelus/2010-05-30_22:00:00",
                                                                               "peps/2010-05-31 15:15:00",
                                                                               "terrain2/2010-05-30 12:30:00",
                                                                               "terrain3/2010-05-30 20:30:00",
                                                                               "terrain4/2010-05-30 17:30:00");
    private static final List<String> INVALID_MATCH_INDENTIFIER = Arrays.asList("terrain/2010-05-30 12:30:00");
    private static final String AN_ERROR_MESSAGE = "Error";
    private static final String VALID_MATCH_IDENTIFIER_TO_ADD = "terrain/2010-08-30 12:30:00";
    private static final String INVALID_MATCH_IDENTIFIER_TO_ADD = "terrain2/2010-10-30 12:30:00";

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private JSONMatchRepository aMatchRepository;

    @Mock
    private JSONMatchMarshaller matchConverter;
    @Mock
    private Match aMatch;
    @Mock
    private Match anotherMatch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        System.setErr(new PrintStream(errContent));
        aMatchRepository = new JSONMatchRepository(matchConverter);
    }

    @After
    public void cleanup() {
        System.setOut(null);
    }

    @Test
    public void whenGettingAListOfMatchesIfAMatchIsNotLoadedThenItIsLoaded() throws FileNotFoundException {
        aMatchRepository.getMatchesByIdentifier(VALID_MATCH_INDENTIFIERS);

        verify(matchConverter).load(contains(VALID_MATCH_INDENTIFIERS.get(0)));
    }

    @Test
    public void whenGettingAListOfMatchesIfAMatchIsLoadedThenItIsReturned() throws FileNotFoundException {
        doReturn(aMatch).when(matchConverter).load(anyString());
        aMatchRepository.getMatchesByIdentifier(VALID_MATCH_INDENTIFIERS);
        reset(matchConverter);

        aMatchRepository.getMatchesByIdentifier(VALID_MATCH_INDENTIFIERS);

        verifyZeroInteractions(matchConverter);
    }

    @Test
    public void whenGettingAMatchAnyErrorsArePrintToTheErrorStream() throws FileNotFoundException {
        doThrow(new FileNotFoundException(AN_ERROR_MESSAGE)).when(matchConverter).load(anyString());

        aMatchRepository.getMatchesByIdentifier(INVALID_MATCH_INDENTIFIER);

        assertEquals(AN_ERROR_MESSAGE, errContent.toString().trim());
    }

    @Test
    public void whenAddingNewMatchItIsSaved() throws IOException {
        doReturn(VALID_MATCH_IDENTIFIER_TO_ADD).when(aMatch).getIdentifier();

        aMatchRepository.add(aMatch);

        verify(matchConverter).save(any(Match.class), contains(VALID_MATCH_IDENTIFIER_TO_ADD));
    }

    @Test
    public void whenNewMatchIsSavedInTheRepositoryAnyErrorsArePrintedToTheErrorStream() throws IOException {
        doReturn(INVALID_MATCH_IDENTIFIER_TO_ADD).when(aMatch).getIdentifier();
        doThrow(new FileNotFoundException(AN_ERROR_MESSAGE)).when(matchConverter)
                                                            .save(any(Match.class),
                                                                  contains(INVALID_MATCH_IDENTIFIER_TO_ADD));

        aMatchRepository.add(aMatch);

        assertEquals(AN_ERROR_MESSAGE, errContent.toString().trim());
    }
}
