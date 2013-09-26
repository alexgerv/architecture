package ca.ulaval.glo4003.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

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
	
	public MatchRepository(){
	    
	}
	
	public Map<Integer, Match> getAll() {
	    entries.clear();
	    loadAllMatches();
		return entries;
	}
	
	private void loadAllMatches(){
	    for(String pathToMatch : fileAccessor.getFilesNameInDirectory(ROOT_REPOSITORY)){
	        Match newMatch;
            try {
                newMatch = matchFactory.createMatch(ROOT_REPOSITORY + pathToMatch);
                entries.put(currentID, newMatch);
                currentID++;
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
	    }
	}
	
	public Match getById(int id) {
        if(entries.containsKey(id)) {
            return entries.get(id);
        }
        throw new RepositoryException("The repository does not contained the specified Id:" + id);
    }
	
	// For tests purposes only
	protected MatchRepository(FileAccessor fileAccessor, MatchFactoryFromJSON matchFactory){
	    this.fileAccessor = fileAccessor;
	    this.matchFactory = matchFactory;
	}
}
