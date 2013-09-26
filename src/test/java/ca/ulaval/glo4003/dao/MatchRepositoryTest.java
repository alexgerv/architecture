package ca.ulaval.glo4003.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.model.MatchFactoryFromJSON;

public class MatchRepositoryTest {

    private static final String[] INVALID_FILES_NAME_IN_A_DIRECTORY = { "InvalidFileA.json", "InvalidFileB.json" };
    private static final String[] VALID_FILES_NAME_IN_A_DIRECTORY = { "ValidFileA.json", "ValidFileB.json" };

    private MatchRepository aMatchRepository;

    @Mock
    private MatchFactoryFromJSON matchFactory;
    @Mock
    private FileAccessor fileAccessor;
    @Mock
    private Match match;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aMatchRepository = new MatchRepository(fileAccessor, matchFactory);
    }

    @Test
    public void whenGettingAllTheMatchEntriesNoEntryIsAddedForAnInvalidFileName() throws FileNotFoundException {
        doReturn(INVALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doThrow(new FileNotFoundException("whenRootDirectoryIsNotValidNoEntriesAreAddedToTheMatchRepository: FileNotFoundException has been thrown.")).when(matchFactory)
                                                                                                                                                      .createMatch(anyString());
        Map<Integer, Match> entries = aMatchRepository.getAll();

        assertTrue(entries.isEmpty());
    }

    @Test
    public void whenGettingAllTheMatchEntriesAllTheMatchEntriesAreReturned() throws FileNotFoundException {
        doReturn(VALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doReturn(match).when(matchFactory).createMatch(anyString());
        Map<Integer, Match> entries = aMatchRepository.getAll();

        assertFalse(entries.isEmpty());
    }
    
    @Test
    public void whenTryingToGetAMatchByAValidIdAMatchIsReturned() throws FileNotFoundException {
        doReturn(VALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doReturn(match).when(matchFactory).createMatch(anyString());
        aMatchRepository.getAll();
        
        Match returnedMatch = aMatchRepository.getById(0);
        assertEquals(match, returnedMatch);
    }
    
    @Test(expected = RepositoryException.class)
    public void whenTryingToGetAMatchByAnInvalidIdAnExceptionIsThrown() throws FileNotFoundException {
        aMatchRepository.getById(0);
    }
}
