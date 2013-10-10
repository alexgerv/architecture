package ca.ulaval.glo4003.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.fileAccess.FileAccessor;
import ca.ulaval.glo4003.fileAccess.JSONMatchConverter;
import ca.ulaval.glo4003.model.Match;

public class MatchRepositoryTest {

    private static final List<String> INVALID_FILES_NAME_IN_A_DIRECTORY = new ArrayList<String>();
    private static final List<String> VALID_FILES_NAME_IN_A_DIRECTORY = new ArrayList<String>();
    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE =
                                                                   "whenRootDirectoryIsNotValidNoEntriesAreAddedToTheMatchRepository: FileNotFoundException has been thrown.";

    private MatchRepository aMatchRepository;

    @Mock
    private JSONMatchConverter JSONMatchConverter;
    @Mock
    private FileAccessor fileAccessor;
    @Mock
    private Match match;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aMatchRepository = new MatchRepository(fileAccessor, JSONMatchConverter);
        INVALID_FILES_NAME_IN_A_DIRECTORY.add("InvalidFileA.json");
        VALID_FILES_NAME_IN_A_DIRECTORY.add("ValidFileA.json");
    }

    @Test
    public void newRepositoryContainsNoEntries() throws FileNotFoundException {
        boolean repositoryIsEmpty = aMatchRepository.isEmpty();
        assertTrue(repositoryIsEmpty);
    }

    @Test
    public void whenGettingAllTheMatchEntriesNoEntryIsAddedForAnInvalidFileName() throws FileNotFoundException {
        doReturn(INVALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());

        doThrow(new FileNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE)).when(JSONMatchConverter)
                                                                            .load(anyString());
        Map<Integer, Match> entries = aMatchRepository.getAllLoadedEntries();

        assertTrue(entries.isEmpty());
    }

    @Test
    public void whenGettingAllTheMatchEntriesAllTheMatchEntriesAreReturned() throws FileNotFoundException {
        doReturn(VALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doReturn(match).when(JSONMatchConverter).load(anyString());
        aMatchRepository.loadAllMatches();
        Map<Integer, Match> entries = aMatchRepository.getAllLoadedEntries();

        assertFalse(entries.isEmpty());
    }

    @Test
    public void whenTryingToGetAMatchByAValidIdAMatchIsReturned() throws FileNotFoundException {
        doReturn(VALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doReturn(match).when(JSONMatchConverter).load(anyString());
        aMatchRepository.loadAllMatches();
        aMatchRepository.getAllLoadedEntries();

        Match returnedMatch = aMatchRepository.getById(0);
        assertEquals(match, returnedMatch);
    }

    @Test(expected = RepositoryException.class)
    public void whenTryingToGetAMatchByAnInvalidIdAnExceptionIsThrown() throws FileNotFoundException {
        aMatchRepository.getById(0);
    }
}
