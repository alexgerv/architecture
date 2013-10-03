package ca.ulaval.glo4003.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.dao.FileAccessor;
import ca.ulaval.glo4003.dao.RepositoryException;
import ca.ulaval.glo4003.model.User;
import ca.ulaval.glo4003.model.UserJSONConverter;

public class UserRepositoryTest {

    private static final String A_USERNAME = "AUsername";
    private static final String[] VALID_FILES_NAME_IN_A_DIRECTORY = {"ValidFile.json"};

    private UserRepository aUserRepository;
    
    @Mock
    private UserJSONConverter userJSONConverter;
    @Mock
    private FileAccessor fileAccessor;
    @Mock
    private User user;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        aUserRepository = new UserRepository(fileAccessor, userJSONConverter);
    }
    
    @Test
    public void newRepositoryContainsNoEntries() {
        boolean repositoryIsEmpty = aUserRepository.isEmpty();
        assertTrue(repositoryIsEmpty);
    }
    
    @Test
    public void whenLoadingAllUsersRepositoryContainsAllUsers() throws FileNotFoundException {
        doReturn(VALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doReturn(user).when(userJSONConverter).load(anyString());
        aUserRepository.loadAll();
        
        boolean repositoryIsEmpty = aUserRepository.isEmpty();
        assertFalse(repositoryIsEmpty);
    }
    
    @Test
    public void canRetrieveUserByItsUsername() throws FileNotFoundException {
        doReturn(VALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doReturn(user).when(userJSONConverter).load(anyString());
        doReturn(true).when(user).hasUsername(A_USERNAME);
        aUserRepository.loadAll();
        
        User retrievedUser = aUserRepository.getUser(A_USERNAME);
        assertEquals(user, retrievedUser);
    }
    
    @Test(expected = RepositoryException.class)
    public void cannotRetrieveInexistantUser() throws FileNotFoundException {
        aUserRepository.getUser(A_USERNAME);
    }
    
   //FIXME This is not a Unit test
    @Test
    public void canAddNewUser() {
        doReturn(true).when(user).hasUsername(A_USERNAME);
        aUserRepository.addNewUser(A_USERNAME);
        
        User retrievedUser = aUserRepository.getUser(A_USERNAME);
        assertTrue(retrievedUser.hasUsername(A_USERNAME));
    }
    
    @Test
    public void userIsSavedAfterAdding() throws IOException {
        aUserRepository.addNewUser(A_USERNAME);
        verify(userJSONConverter).save((User) anyObject(), anyString());
    }
    
    @Test(expected = ExistingUsernameException.class)
    public void cannotAddTheSameUsernameTwice() {
        aUserRepository.addNewUser(A_USERNAME);
        aUserRepository.addNewUser(A_USERNAME);
    }
    
}
