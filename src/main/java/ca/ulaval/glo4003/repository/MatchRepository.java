package ca.ulaval.glo4003.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.fileAccess.JSONMatchConverter;
import ca.ulaval.glo4003.model.Match;

@Repository
@Singleton
public class MatchRepository {

    private static final String ROOT_REPOSITORY = "./matches/";
    
    private JSONMatchConverter JSONMatchConverter = new JSONMatchConverter();
    private Map<Integer, Match> loadedEntries = new HashMap<Integer, Match>();

    public MatchRepository() {}

    public List<Match> getMatchesByIndex(Integer[] matchesId) {
        List<Match> matches = new ArrayList<Match>();
        
        for(int id : matchesId){
            if(loadedEntries.containsKey(id)){
                matches.add(loadedEntries.get(id));
            }
            else{
                loadMatch(id);
                matches.add(loadedEntries.get(id));
            }
        }
        
        return matches;
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
