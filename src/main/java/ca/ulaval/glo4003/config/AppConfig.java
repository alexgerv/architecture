//package ca.ulaval.glo4003.config;
//
//import javax.inject.Singleton;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import ca.ulaval.glo4003.persistence.JSONMatchRepository;
//import ca.ulaval.glo4003.persistence.JSONUserRepository;
//import ca.ulaval.glo4003.repository.MatchRepository;
//import ca.ulaval.glo4003.repository.UserRepository;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    @Singleton
//    public MatchRepository matchRepository() {
//        return new JSONMatchRepository();
//    }
//
//    @Bean
//    @Singleton
//    public UserRepository userRepository() {
//        return new JSONUserRepository();
//    }
// }
