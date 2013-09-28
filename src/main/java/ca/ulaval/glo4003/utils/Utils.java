package ca.ulaval.glo4003.utils;

import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static <T> Set<T> getIntersection(Set<T> setA, Set<T> setB) {
        boolean setAIsBigger = setA.size() >= setB.size();
        Set<T> intersectionSet = new HashSet<T>(setAIsBigger ? setB : setA);
        intersectionSet.retainAll(setAIsBigger ? setA : setB);
        return intersectionSet;
    }

}
