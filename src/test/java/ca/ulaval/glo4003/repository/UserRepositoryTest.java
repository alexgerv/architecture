package ca.ulaval.glo4003.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.fileAccess.FileAccessor;
import ca.ulaval.glo4003.fileAccess.JSONUserConverter;
import ca.ulaval.glo4003.model.DbUser;

public class UserRepositoryTest {

    private static final String A_USERNAME = "a_username";
    private static final String[] VALID_FILES_NAME_IN_A_DIRECTORY = { "ValidFile.json" };

    @Mock
    private JSONUserConverter JSONUserConverter;
    @Mock
    private FileAccessor fileAccessor;
    @Mock
    private DbUser user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        UserRepository.load(new UserRepository(fileAccessor, JSONUserConverter));
    }

    @Test
    public void newRepositoryContainsNoEntries() {
        boolean repositoryIsEmpty = UserRepository.getInstance().isEmpty();
        assertTrue(repositoryIsEmpty);
    }

    @Test
    public void whenLoadingAllUsersRepositoryContainsAllUsers() throws FileNotFoundException {
        doReturn(VALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doReturn(user).when(JSONUserConverter).load(anyString());
        UserRepository.getInstance().loadAll();

        boolean repositoryIsEmpty = UserRepository.getInstance().isEmpty();
        assertFalse(repositoryIsEmpty);
    }

    @Test
    public void canRetrieveUserByItsUsername() throws FileNotFoundException {
        doReturn(VALID_FILES_NAME_IN_A_DIRECTORY).when(fileAccessor).getFilesNameInDirectory(anyString());
        doReturn(user).when(JSONUserConverter).load(anyString());
        doReturn(true).when(user).hasUsername(A_USERNAME);
        UserRepository.getInstance().loadAll();

        DbUser retrievedUser = UserRepository.getInstance().getUser(A_USERNAME);
        assertEquals(user, retrievedUser);
    }

    @Test(expected = RepositoryException.class)
    public void cannotRetrieveInexistantUser() throws FileNotFoundException {
        UserRepository.getInstance().getUser(A_USERNAME);
    }

    // FIXME This is not a Unit test
    @Test
    public void canAddNewUser() {
        doReturn(true).when(user).hasUsername(A_USERNAME);
        UserRepository.getInstance().addNewUser(A_USERNAME);

        DbUser retrievedUser = UserRepository.getInstance().getUser(A_USERNAME);
        assertTrue(retrievedUser.hasUsername(A_USERNAME));
    }

    @Test
    public void userIsSavedAfterAdding() throws IOException {
        UserRepository.getInstance().addNewUser(A_USERNAME);
        verify(JSONUserConverter).save((DbUser) anyObject(), anyString());
    }

    @Test(expected = ExistingUsernameException.class)
    public void cannotAddTheSameUsernameTwice() {
        UserRepository.getInstance().addNewUser(A_USERNAME);
        UserRepository.getInstance().addNewUser(A_USERNAME);
    }

}
