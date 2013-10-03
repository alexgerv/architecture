package ca.ulaval.glo4003.dao;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.model.MatchJSONConverter;

@Repository
@Singleton
public class MatchRepository {

    private static final String ROOT_REPOSITORY = "./matches/";

    private int currentID = 0;
    private MatchJSONConverter matchJSONConverter = new MatchJSONConverter();
    private Map<Integer, Match> entries = new HashMap<Integer, Match>();
    private FileAccessor fileAccessor = new FileAccessor();

    public MatchRepository() {

    }

    public Map<Integer, Match> getAllLoadedEntries() {
        return entries;
    }

    public void loadAllMatches() {
        for (String pathToMatch : fileAccessor.getFilesNameInDirectory(ROOT_REPOSITORY)) {
            Match newMatch;
            try {
                newMatch = matchJSONConverter.load(ROOT_REPOSITORY + pathToMatch);
                entries.put(currentID, newMatch);
                currentID++;
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public Match getById(int id) {
        if (entries.containsKey(id)) {
            return entries.get(id);
        }
        throw new RepositoryException("The repository does not contained the specified Id:" + id);
    }

    // For tests purposes only
    protected MatchRepository(FileAccessor fileAccessor, MatchJSONConverter matchJSONConverter) {
        this.fileAccessor = fileAccessor;
        this.matchJSONConverter = matchJSONConverter;
    }
}
