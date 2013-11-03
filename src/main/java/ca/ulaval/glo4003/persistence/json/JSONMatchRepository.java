package ca.ulaval.glo4003.persistence.json;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchMarshaller;
import ca.ulaval.glo4003.repository.MatchRepository;

@Repository
@Singleton
public class JSONMatchRepository extends MatchRepository {

    private static final String ROOT_REPOSITORY = "./matches/";
    private MatchMarshaller matchMarshaller = new JSONMatchMarshaller();

    public JSONMatchRepository() {

    }

    protected void loadMatch(String identifier) {
        Match newMatch;
        try {
            newMatch = matchMarshaller.load(ROOT_REPOSITORY + identifier);
            loadedEntries.put(identifier, newMatch);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void add(Match match) {
        String matchIdentifier = match.getIdentifier();
        try {
            matchMarshaller.save(match, ROOT_REPOSITORY + matchIdentifier);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    protected JSONMatchRepository(JSONMatchMarshaller JSONMatchConverter) {
        this.matchMarshaller = JSONMatchConverter;
    }

}
