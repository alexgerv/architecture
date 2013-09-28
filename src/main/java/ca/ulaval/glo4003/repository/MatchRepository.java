package ca.ulaval.glo4003.repository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.dao.FileAccessor;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.model.MatchFactoryFromJSON;
import ca.ulaval.glo4003.searchEngine.MatchQuery;
import ca.ulaval.glo4003.searchEngine.MatchSearchEngine;

@Repository
@Singleton
public class MatchRepository {

    private static final String ROOT_REPOSITORY = "./matches/";

    private MatchFactoryFromJSON matchFactory = new MatchFactoryFromJSON();
    private List<Match> entries = new ArrayList<Match>();
    private FileAccessor fileAccessor = new FileAccessor();

    private MatchSearchEngine searchEngine = new MatchSearchEngine();

    public MatchRepository() {}

    public List<Match> getAllLoadedEntries() {
        return entries;
    }

    public void loadAll() {
        for (String pathToMatch : fileAccessor.getFilesNameInDirectory(ROOT_REPOSITORY)) {
            Match newMatch;
            try {
                newMatch = matchFactory.createMatch(ROOT_REPOSITORY + pathToMatch);
                searchEngine.add(newMatch, entries.size());
                entries.add(newMatch);
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public List<Match> getFromQuery(MatchQuery query) {
        List<Match> matches = new ArrayList<Match>();
        Set<Integer> matchIndexes = searchEngine.getIndexesFromQuery(query);
        for (Integer i : matchIndexes) {
            matches.add(entries.get(i));
        }
        return matches;
    }

    public Match getById(int id) {
        if (entries.size() > id) {
            return entries.get(id);
        }
        throw new RepositoryException("The repository does not contained the specified Id:" + id);
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    // For tests purposes only
    protected MatchRepository(FileAccessor fileAccessor, MatchFactoryFromJSON matchFactory) {
        this.fileAccessor = fileAccessor;
        this.matchFactory = matchFactory;
    }
}
