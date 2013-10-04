package ca.ulaval.glo4003.searchEngine;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.bouncycastle.util.Arrays;

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

    public void getSpecifiedPageMatchesFromQuery(MatchQuery aMatchQuery, int aPageNumber) {
        Set<Integer> queryMatches = matchIndex.getIndexesFromQuery(aMatchQuery);
        int[] refinedMatches = refineMatchesForSpecifiedPage(queryMatches, aPageNumber);
        matchRepository.getMatchesByIndex(refinedMatches);
    }
    
    private int[] refineMatchesForSpecifiedPage(Set<Integer> matches, Integer pageNumber){
        Integer startPosition = pageNumber * numberOfResultPerPage;
        Integer endPosition = startPosition + numberOfResultPerPage;
        
        return Arrays.copyOfRange(setToArrayOfInt(matches), startPosition, endPosition);
    }
    
    private int[] setToArrayOfInt(Set<Integer> set){
        
        int[] newArray = new int[set.size()];
        
        int index = 0;
        
        for(Integer i : set){
            newArray[index++] = i;
        }
        
        return newArray;
    }

}
