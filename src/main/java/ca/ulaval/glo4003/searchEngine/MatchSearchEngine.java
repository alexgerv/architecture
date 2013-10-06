package ca.ulaval.glo4003.searchEngine;

import java.util.List;
import java.util.Set;

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
    }

    public List<Match> getSpecifiedPageMatchesFromQuery(MatchQuery aMatchQuery, int aPageNumber) {
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

}
