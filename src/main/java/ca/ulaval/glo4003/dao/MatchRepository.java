package ca.ulaval.glo4003.dao;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

    private MatchFactoryFromJSON matchFactory = new MatchFactoryFromJSON();
    private List<Match> entries = new ArrayList<Match>();
    private FileAccessor fileAccessor = new FileAccessor();

    private Map<String, Set<Integer>> sportIndex = new HashMap<String, Set<Integer>>();

    public MatchRepository() {}

    public List<Match> getAllLoadedEntries() {
        return entries;
    }

    public void loadAllMatches() {
        for (String pathToMatch : fileAccessor.getFilesNameInDirectory(ROOT_REPOSITORY)) {
            Match newMatch;
            try {
                newMatch = matchFactory.createMatch(ROOT_REPOSITORY + pathToMatch);
                String sport = newMatch.getSport();
                if (!sportIndex.containsKey(sport)) {
                    sportIndex.put(sport, new HashSet<Integer>());
                }
                sportIndex.get(sport).add(entries.size());
                entries.add(newMatch);
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public List<Match> getAllMatchBySport(String sport) {
        if (sportIndex.isEmpty()) {
            throw new RepositoryException("The sport index is empty.");
        }

        List<Match> sportMatches = new ArrayList<Match>();
        Set<Integer> index = sportIndex.get(sport);
        for (Integer i : index) {
            sportMatches.add(entries.get(i));
        }
        return sportMatches;
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
