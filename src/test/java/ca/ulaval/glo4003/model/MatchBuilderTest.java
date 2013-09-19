package ca.ulaval.glo4003.model;

import org.junit.Test;

public class MatchBuilderTest {

    MatchBuilder aMatchBuilder = new MatchBuilder();

    @Test(expected = MatchBuilderException.class)
    public void whenBuildingAMatchWithUninitialized() {
        aMatchBuilder.build();
    }

}
