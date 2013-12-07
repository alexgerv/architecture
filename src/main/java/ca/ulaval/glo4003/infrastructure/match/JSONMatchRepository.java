package ca.ulaval.glo4003.infrastructure.match;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Singleton;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchRepository;

@Repository
@Singleton
public class JSONMatchRepository extends MatchRepository {

    private static final String ROOT_REPOSITORY = "./matches/";
    private JSONMatchMarshaller matchMarshaller = new JSONMatchMarshaller();
    private Logger logger = LogManager.getRootLogger();

    public JSONMatchRepository() {

    }

    protected void loadMatch(String identifier) {
        Match newMatch;
        try {
            newMatch = matchMarshaller.load(ROOT_REPOSITORY + identifier);
            loadedEntries.put(identifier, newMatch);
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage());
        }
    }

    public void add(Match match) {
        String matchIdentifier = match.getIdentifier();
        try {
            matchMarshaller.save(match, ROOT_REPOSITORY + matchIdentifier);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    protected JSONMatchRepository(JSONMatchMarshaller JSONMatchConverter, Logger logger) {
        this.matchMarshaller = JSONMatchConverter;
        this.logger = logger;
    }

}
