package ca.ulaval.glo4003.searchEngine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import ca.ulaval.glo4003.fileAccess.FileAccessor;
import ca.ulaval.glo4003.fileAccess.JSONMatchConverter;
import ca.ulaval.glo4003.fileAccess.JSONMatchIndexConverter;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchRepository;

public class MatchCatalog {
    
    private static final int DEFAULT_NUMBER_OF_RESULTS_PER_PAGE = 30;
    private static final String MATCH_INDEX_PERSISTENCY_PATH = "./MatchSearchEngine/MatchSearchEngine.java";
    private static final String MATCHES_PATH = "./matches/";
    
    private static MatchCatalog searchEngine;
    private MatchIndex matchIndex;
    private MatchRepository matchRepository;
    private Integer numberOfResultPerPage;
   
    private JSONMatchIndexConverter JSONMatchIndexConverter = new JSONMatchIndexConverter();

    private MatchCatalog(){
        loadSearchEngine();
        this.matchRepository = MatchRepository.getInstance();
        this.numberOfResultPerPage = DEFAULT_NUMBER_OF_RESULTS_PER_PAGE;
    }
    
    public static MatchCatalog getInstance(){
        if(searchEngine == null){
            searchEngine = new MatchCatalog();
        }
        return searchEngine;
    }
    
    private void loadSearchEngine() {
        try {
            this.matchIndex = JSONMatchIndexConverter.load(MATCH_INDEX_PERSISTENCY_PATH);
        } catch (FileNotFoundException e) {
            createIndexFromPath(MATCHES_PATH );
            saveIndex();
        }
    }

    private void createIndexFromPath(String path) {
        FileAccessor fileAccessor = new FileAccessor();
        JSONMatchConverter converter = new JSONMatchConverter();
        for(String file : fileAccessor.getFilesNameInDirectory(path)){
            try {
                Match newMatch = converter.load(path + file);
                add(newMatch);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }    
    }
    
    private void saveIndex() {
        try {
            JSONMatchIndexConverter.save(matchIndex, MATCH_INDEX_PERSISTENCY_PATH);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }        
    }

    public Map<Integer, Match> getMatchesForSpecifiedPageFromQuery(MatchQuery aMatchQuery, int aPageNumber) {
        Set<Integer> queryMatchesIndex = matchIndex.getIndexesFromQuery(aMatchQuery);
        
        Integer[] refinedMatches = refineMatchesForSpecifiedPage(queryMatchesIndex, aPageNumber);
        
        return matchRepository.getMatchesById(refinedMatches);
    }
    
    private Integer[] refineMatchesForSpecifiedPage(Set<Integer> matches, Integer pageNumber){
        Integer startPosition = (pageNumber-1) * numberOfResultPerPage;
        Integer endPosition = startPosition + numberOfResultPerPage;
        
        return rangeOfIntegerSetToArray(matches, startPosition, endPosition);
    }
    
    private Integer[] rangeOfIntegerSetToArray(Set<Integer> set, int start, int end){
        Integer[] newArray = new Integer[numberOfResultPerPage];
        Object[] setArray = set.toArray();
        int newArrayPosition = 0;
        
        for(int i=start; i<end; i++){
            newArray[newArrayPosition++] = (Integer)setArray[i];
        } 
        return newArray;
    }

    public void add(Match match) {
        Integer newMatchId = matchIndex.add(match);
        matchRepository.add(match, newMatchId);
        saveIndex();
    }

    // For tests purposes only
    protected MatchCatalog(MatchIndex matchIndex, MatchRepository matchRepository, int numberOfResultPerPage) {
        this.matchIndex = matchIndex;
        this.matchRepository = matchRepository;
        this.numberOfResultPerPage = numberOfResultPerPage;
    }
}
