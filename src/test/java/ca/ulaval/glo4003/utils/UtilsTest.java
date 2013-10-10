package ca.ulaval.glo4003.utils;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class UtilsTest {

    private static final int NON_INTERSECTING_OBJECT = 0;
    private static final int ANOTHER_NON_INTERSECTING_OBJECT = 1;
    private static final int INTERSECTING_OBJECT = 2;
    private Set<Integer> aSet;
    private Set<Integer> anotherSet;

    @Before
    public void setup() {
        aSet = new HashSet<Integer>();
        anotherSet = new HashSet<Integer>();
    }

    @Test
    public void getIntersectionReturnsEmptySetIfSetsAreNotInterseecting() {
        aSet.add(NON_INTERSECTING_OBJECT);
        anotherSet.add(ANOTHER_NON_INTERSECTING_OBJECT);
        Set<Integer> intersection = Utils.getIntersection(aSet, anotherSet);
        boolean intersectionIsEmpty = intersection.isEmpty();
        assertTrue(intersectionIsEmpty);
    }

    @Test
    public void getIntersectionReturnsSetContainingIntersectionOfTwoSets() {
        aSet.add(INTERSECTING_OBJECT);
        anotherSet.add(INTERSECTING_OBJECT);
        anotherSet.add(NON_INTERSECTING_OBJECT);
        Set<Integer> intersection = Utils.getIntersection(aSet, anotherSet);
        boolean intersectionContainsOnlyIntersectingValue =
                                                            intersection.contains(INTERSECTING_OBJECT)
                                                                    && intersection.size() == 1;
        assertTrue(intersectionContainsOnlyIntersectingValue);
    }
}
