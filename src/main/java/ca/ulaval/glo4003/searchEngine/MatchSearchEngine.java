package ca.ulaval.glo4003.searchEngine;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import ca.ulaval.glo4003.fileAccess.FileAccessor;
import ca.ulaval.glo4003.fileAccess.JSONMatchConverter;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchRepository;

public class MatchSearchEngine {
    
    private MatchIndex matchIndex;
    private MatchRepository matchRepository;
    private Integer numberOfResultPerPage;

    public MatchSearchEngine(MatchIndex matchIndex, MatchRepository matchRepository, int numberOfResultPerPage) {
        this.matchIndex = matchIndex;
        this.matchRepository = matchRepository;
        this.numberOfResultPerPage = numberOfResultPerPage;
        
        temporarilyIndexAllMatch();
    }

    private void temporarilyIndexAllMatch() {
        FileAccessor tempFileAccessor = new FileAccessor();
        JSONMatchConverter converter = new JSONMatchConverter();
        String rootDirectory = "./matches/";
        for(String file : tempFileAccessor.getFilesNameInDirectory(rootDirectory)){
            try {
                Match newMatch = converter.load(rootDirectory + file);
                add(newMatch);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        
    }

    public List<Match> getMatchesForSpecifiedPageFromQuery(MatchQuery aMatchQuery, int aPageNumber) {
        Set<Integer> queryMatchesIndex = matchIndex.getIndexesFromQuery(aMatchQuery);
        
        Integer[] refinedMatches = refineMatchesForSpecifiedPage(queryMatchesIndex, aPageNumber);
        
        return matchRepository.getMatchesByIndex(refinedMatches);
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
    }

}
