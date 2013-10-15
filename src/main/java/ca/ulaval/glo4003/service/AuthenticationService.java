package ca.ulaval.glo4003.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import ca.ulaval.glo4003.model.User;
import ca.ulaval.glo4003.repository.RepositoryException;
import ca.ulaval.glo4003.repository.UserRepository;

@Transactional(readOnly = true)
public class AuthenticationService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        UserDetails springUser = null;

        try {

            User user = UserRepository.getInstance().getUser(username);

            springUser = new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                                user.getPassword().toLowerCase(),
                            true,
                            true,
                            true,
                            true,
                            getAuthorities(user.getAccess()));

        } catch (RepositoryException e) {
            throw new UsernameNotFoundException("Error in retrieving user");
        }

        return springUser;
    }

    public Collection<GrantedAuthority> getAuthorities(Integer access) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

        authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Check if this user has admin access
        // We interpret Integer(1) as an admin user
        if (access.compareTo(1) == 0) {
            // User has admin access
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        // Return list of granted authorities
        return authList;
    }
}