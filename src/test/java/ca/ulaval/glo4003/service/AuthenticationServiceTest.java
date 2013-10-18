package ca.ulaval.glo4003.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ca.ulaval.glo4003.repository.RepositoryException;
import ca.ulaval.glo4003.repository.UserRepository;

public class AuthenticationServiceTest {

    private static final String A_USERNAME = "user";

    private static final Integer NOT_ADMIN_ACCESS = 2;

    private static final Integer ADMIN_ACCESS = 1;

    AuthenticationService authenticationService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authenticationService = new AuthenticationService(userRepository);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void userNameNotFoundExceptionIsThrownWhenARepositoryExceptionIsThrown() {
        doThrow(new RepositoryException("")).when(userRepository).getUser(A_USERNAME);
        authenticationService.loadUserByUsername(A_USERNAME);
    }

    @Test
    public void userAccessIsAlwaysGranted() {
        Collection<GrantedAuthority> grantedAuthorities = authenticationService.getAuthorities(NOT_ADMIN_ACCESS);
        boolean grantedAuthoritiesContainsUserAccess =
                                                       grantedAuthorities.contains(new SimpleGrantedAuthority(
                                                                                                              "ROLE_USER"));
        assertTrue(grantedAuthoritiesContainsUserAccess);
    }

    @Test
    public void whenUserIsNotAdminAdminAccessIsNotGranted() {
        Collection<GrantedAuthority> grantedAuthorities = authenticationService.getAuthorities(NOT_ADMIN_ACCESS);
        boolean grantedAuthoritiesContainsUserAccess =
                                                       grantedAuthorities.contains(new SimpleGrantedAuthority(
                                                                                                              "ROLE_ADMIN"));
        assertFalse(grantedAuthoritiesContainsUserAccess);
    }

    @Test
    public void whenUserIsAdminAdminAccessIsGranted() {
        Collection<GrantedAuthority> grantedAuthorities = authenticationService.getAuthorities(ADMIN_ACCESS);
        boolean grantedAuthoritiesContainsUserAccess =
                                                       grantedAuthorities.contains(new SimpleGrantedAuthority(
                                                                                                              "ROLE_ADMIN"));
        assertTrue(grantedAuthoritiesContainsUserAccess);
    }
}
