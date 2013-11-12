package ca.ulaval.glo4003.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalogFactory;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQueryFactory;
import ca.ulaval.glo4003.domain.repository.MatchRepository;
import ca.ulaval.glo4003.infrastructure.index.JSONMatchQueryFactory;
import ca.ulaval.glo4003.infrastructure.matchCatalog.JSONMatchCatalogFactory;

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
