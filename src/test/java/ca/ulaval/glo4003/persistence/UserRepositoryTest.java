package ca.ulaval.glo4003.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.fileAccess.FileAccessor;
import ca.ulaval.glo4003.fileAccess.JSONUserConverter;
import ca.ulaval.glo4003.model.User;
import ca.ulaval.glo4003.persistence.JSONUserRepository;
import ca.ulaval.glo4003.repository.ExistingUsernameException;
import ca.ulaval.glo4003.repository.RepositoryException;

public class UserRepositoryTest {

    private static final String A_USERNAME = "a_username";
    private static final String A_PASSWORD = "a_password";
    private static final Integer AN_ACCESS_LEVEL = 0;
    private static final List<String> VALID_FILES_NAME_IN_A_DIRECTORY = new ArrayList<String>();
    private static final String ANOTHER_USERNAME = "another_username";

    private JSONUserRepository userRepository;

    @Mock
    private JSONUserConverter JSONUserConverter;
    @Mock
    private FileAccessor fileAccessor;
    @Mock
    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userRepository = new JSONUserRepository(fileAccessor, JSONUserConverter);
        VALID_FILES_NAME_IN_A_DIRECTORY.add("ValidFileA.json");
    }

    @Test
    public void newRepositoryContainsNoEntries() {
        boolean repositoryIsEmpty = userRepository.isEmpty();
        assertTrue(repositoryIsEmpty);
    }

    @Test
    public void whenLoadingAllUsersRepositoryContainsAllUsers() throws FileNotFoundException {
        doReturn(VALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doReturn(user).when(JSONUserConverter).load(anyString());
        userRepository.loadAll();

        boolean repositoryIsEmpty = userRepository.isEmpty();
        assertFalse(repositoryIsEmpty);
    }

    @Test
    public void canRetrieveUserByItsUsername() throws FileNotFoundException {
        doReturn(VALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doReturn(user).when(JSONUserConverter).load(anyString());
        doReturn(true).when(user).hasUsername(A_USERNAME);
        userRepository.loadAll();

        User retrievedUser = userRepository.getUser(A_USERNAME);
        assertEquals(user, retrievedUser);
    }

    @Test(expected = RepositoryException.class)
    public void cannotRetrieveInexistantUser() throws FileNotFoundException {
        userRepository.getUser(A_USERNAME);
    }

    // FIXME This is not a Unit test
    @Test
    public void canAddNewUser() {
        doReturn(true).when(user).hasUsername(A_USERNAME);
        userRepository.addNewUser(A_USERNAME, A_PASSWORD, AN_ACCESS_LEVEL);

        User retrievedUser = userRepository.getUser(A_USERNAME);
        assertTrue(retrievedUser.hasUsername(A_USERNAME));
    }

    @Test
    public void userIsSavedAfterAdding() throws IOException {
        userRepository.addNewUser(A_USERNAME, A_PASSWORD, AN_ACCESS_LEVEL);
        verify(JSONUserConverter).save((User) anyObject(), anyString());
    }

    @Test(expected = ExistingUsernameException.class)
    public void cannotAddTheSameUsernameTwice() {
        userRepository.addNewUser(A_USERNAME, A_PASSWORD, AN_ACCESS_LEVEL);
        userRepository.addNewUser(A_USERNAME, A_PASSWORD, AN_ACCESS_LEVEL);
    }

    @Test
    public void canAddMultipleUsersIfTheyHaveDifferentUsernames() {
        userRepository.addNewUser(A_USERNAME, A_PASSWORD, AN_ACCESS_LEVEL);
        userRepository.addNewUser(ANOTHER_USERNAME, A_PASSWORD, AN_ACCESS_LEVEL);
    }

}
