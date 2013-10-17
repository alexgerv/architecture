package ca.ulaval.glo4003.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.ulaval.glo4003.persistence.JSONMatchRepository;
import ca.ulaval.glo4003.persistence.JSONUserRepository;
import ca.ulaval.glo4003.repository.MatchRepository;
import ca.ulaval.glo4003.repository.UserRepository;

@Configuration
public class AppConfig {

    @Bean
    public MatchRepository matchRepository() throws Exception {
        return new JSONMatchRepository();
    }

    @Bean
    public UserRepository userRepository() throws Exception {
        return new JSONUserRepository();
    }
}
