package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import ca.ulaval.glo4003.domain.repository.UserRepository;
import ca.ulaval.glo4003.domain.user.User;

public class LoginLogoutControllerTest {

    private static final boolean ERROR = true;

    @Mock
    private ModelMap model;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private User user;

    @Mock
    private UserRepository repository;

    @InjectMocks
    private LoginLogoutController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(session).when(request).getSession();
    }

    @Test
    public void canGetDeniedPage() {
        assertEquals("deniedpage", controller.getDeniedPage());
    }

    @Test
    public void canGetLogin() {
        assertEquals("login", controller.getLoginPage(ERROR, model));
    }
}
