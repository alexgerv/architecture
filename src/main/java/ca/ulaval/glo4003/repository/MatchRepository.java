package ca.ulaval.glo4003.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.fileAccess.JSONMatchConverter;
import ca.ulaval.glo4003.model.Match;

@Repository
public class MatchRepository {

    private static final String ROOT_REPOSITORY = "./matches/";
    
    private static MatchRepository repository;
    private JSONMatchConverter JSONMatchConverter = new JSONMatchConverter();
    private Map<Integer, Match> loadedEntries = new HashMap<Integer, Match>();

    private MatchRepository() {
        
    }
    
    public static MatchRepository getInstance(){
        if(repository == null){
            repository = new MatchRepository();
        }
        
        return repository;            
    }
    
    
    public Match getMatchById(Integer matchId){
        if(loadedEntries.containsKey(matchId)){
            return loadedEntries.get(matchId);
        }
        else{
            loadMatch(matchId);
            return loadedEntries.get(matchId);
        }
    }
    
    private void loadMatch(Integer id) {
        Match newMatch;
        try {
            newMatch = JSONMatchConverter.load(ROOT_REPOSITORY + id);
            loadedEntries.put(id, newMatch);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }   
    }

    public Map<Integer, Match> getMatchesById(Integer[] matchesId) {
        Map<Integer, Match> matches = new HashMap<Integer, Match>();
        for(int id : matchesId){
            matches.put(id, getMatchById(id));
        }
        
        return matches;
    }    
    
    public Map<Integer, Match> getAllLoadedEntries(){
        return loadedEntries;
    }
    
    public void add(Match match, Integer id){
        try {
            JSONMatchConverter.save(match, ROOT_REPOSITORY + id.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // For tests purposes only
    protected MatchRepository(JSONMatchConverter JSONMatchConverter) {
        this.JSONMatchConverter = JSONMatchConverter;
    }
}
