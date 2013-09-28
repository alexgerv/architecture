package ca.ulaval.glo4003.dao;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.model.MatchFactoryFromJSON;

@Repository
@Singleton
public class MatchRepository {

    private static final String ROOT_REPOSITORY = "./matches/";

    private int currentID = 0;
    private MatchFactoryFromJSON matchFactory = new MatchFactoryFromJSON();
    private Map<Integer, Match> entries = new HashMap<Integer, Match>();
    private FileAccessor fileAccessor = new FileAccessor();

    private Map<String, Set<Integer>> sportIndex = new HashMap<String, Set<Integer>>();

    public MatchRepository() {}

    public Map<Integer, Match> getAllLoadedEntries() {
        return entries;
    }

    public void loadAllMatches() {
        for (String pathToMatch : fileAccessor.getFilesNameInDirectory(ROOT_REPOSITORY)) {
            Match newMatch;
            try {
                newMatch = matchFactory.createMatch(ROOT_REPOSITORY + pathToMatch);
                entries.put(currentID, newMatch);
                String sport = newMatch.getSport();
                if (!sportIndex.containsKey(sport)) {
                    sportIndex.put(sport, new HashSet<Integer>());
                }
                sportIndex.get(sport).add(currentID);
                currentID++;
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public Map<Integer, Match> getAllMatchBySport(String sport) {
        Map<Integer, Match> mapMatches = new HashMap<Integer, Match>();

        Set<Integer> index = sportIndex.get(sport);
        for (Integer i : index) {
            mapMatches.put(i, entries.get(i));
        }

        if (sportIndex.isEmpty()) {
            throw new RuntimeException("sportIndex is empty.");
        }

        return mapMatches;
    }

    public Match getById(int id) {
        if (entries.containsKey(id)) {
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
