package ca.ulaval.glo4003.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.ulaval.glo4003.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.matchCatalog.MatchCatalogFactory;
import ca.ulaval.glo4003.matchCatalog.MatchQueryFactory;
import ca.ulaval.glo4003.persistence.json.JSONMatchCatalogFactory;
import ca.ulaval.glo4003.persistence.json.JSONMatchQueryFactory;
import ca.ulaval.glo4003.repository.MatchRepository;

@Configuration
public class AppConfig {
    
    @Autowired
    MatchRepository matchRepository;

    @Bean
    public MatchCatalogFactory matchCatalogFactory() throws Exception {
        return new JSONMatchCatalogFactory(matchRepository);
    }

    @Bean
    public MatchCatalog matchCatalog() throws Exception {
        return matchCatalogFactory().createMatchCatalog();
    }

    @Bean
    public MatchQueryFactory matchQueryFactory() throws Exception {
        return new JSONMatchQueryFactory();
    }

}
