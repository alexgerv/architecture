package ca.ulaval.glo4003.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.fileAccess.JSONMatchConverter;
import ca.ulaval.glo4003.model.Match;

@Repository
public class MatchRepository {

    private static final String ROOT_REPOSITORY = "./matches/";
    
    private static MatchRepository repository;
    private JSONMatchConverter JSONMatchConverter = new JSONMatchConverter();
    private Map<String, Match> loadedEntries = new HashMap<String, Match>();

    private MatchRepository() {
        
    }
    
    public static MatchRepository getInstance(){
        if(repository == null){
            repository = new MatchRepository();
        }
        
        return repository;            
    }
    
    
    public Match getMatchByIdentifier(String matchIdentifier){
        if(loadedEntries.containsKey(matchIdentifier)){
            return loadedEntries.get(matchIdentifier);
        }
        else{
            loadMatch(matchIdentifier);
            return loadedEntries.get(matchIdentifier);
        }
    }
    
    private void loadMatch(String identifier) {
        Match newMatch;
        try {
            newMatch = JSONMatchConverter.load(ROOT_REPOSITORY + identifier);
            loadedEntries.put(identifier, newMatch);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }   
    }

    public Map<String, Match> getMatchesById(List<String> matchesIdentifier) {
        Map<String, Match> matches = new HashMap<String, Match>();
        for(String identifier : matchesIdentifier){
            matches.put(identifier, getMatchByIdentifier(identifier));
        }
        
        return matches;
    }    
    
    public Map<String, Match> getAllLoadedEntries(){
        return loadedEntries;
    }
    
    public void add(Match match){
        String matchIdentifier = match.getIdentifier();
        try {
            JSONMatchConverter.save(match, ROOT_REPOSITORY + matchIdentifier);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // For tests purposes only
    protected MatchRepository(JSONMatchConverter JSONMatchConverter) {
        this.JSONMatchConverter = JSONMatchConverter;
    }
}
