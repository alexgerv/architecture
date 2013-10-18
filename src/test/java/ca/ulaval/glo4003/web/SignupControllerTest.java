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
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.ui.Model;

import ca.ulaval.glo4003.dao.UserDAO;
import ca.ulaval.glo4003.repository.UserRepository;

public class SignupControllerTest {

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserDAO user;

    @Mock
    private UserRepository repository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    private SignupController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(session).when(request).getSession();
    }

    @Test
    public void canGetSignupPage() {
        assertEquals("signup", controller.signup(model));
    }

    @Test
    public void whenSigningOnLoginPageIsReturned() {
        assertEquals("login", controller.submitForm(user, model));
    }
}
