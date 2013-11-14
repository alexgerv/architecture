package ca.ulaval.glo4003.domaine.persistence;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.domain.persistence.FileAccessor;
import ca.ulaval.glo4003.domain.repository.RepositoryException;


public class FileAccessorTest {

    private static final String AN_INVALID_DIRECTORY = "INVALID_DIRECTORY";
    private FileAccessor fileAccessor;
    
    @Before
    public void setup(){
        fileAccessor = new FileAccessor();
    }
    
    @Test(expected = RepositoryException.class)
    public void whenAccessingAPathThatIsNotADirectoryARepositoryExceptionIsThrown() {
        fileAccessor.getFilesNameInDirectory(AN_INVALID_DIRECTORY);
    }
    
    @Test
    public void whenAccessingAValidDirectoryWeGetAListOfTheFiltredFilesName() {
        assertTrue(fileAccessor.getFilesNameInDirectory("/") instanceof List<?>);
    }
}
