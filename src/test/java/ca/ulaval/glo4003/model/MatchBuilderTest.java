package ca.ulaval.glo4003.model;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class MatchBuilderTest {

    MatchBuilder aMatchBuilder;

    @Before
    public void setup() {
        aMatchBuilder = new MatchBuilder();
    }

    @Test(expected = MatchBuilderException.class)
    public void whenBuildingAMatchWithUninitializedAttributesThrowsAMatchBuilderException() {
        aMatchBuilder.build();
    }

    @Test(expected = MatchBuilderException.class)
    public void afterBuildingAMatchTheBuilderAttributesAreReinitialized() {
        aMatchBuilder.setDate(new Date());
        aMatchBuilder.setHomeTeam("Rouge et Or");
        aMatchBuilder.setSport("FootBall");
        aMatchBuilder.setVenue("Telus");
        aMatchBuilder.setVisitorTeam("Vert et Or");
        aMatchBuilder.createSection("A", 20);
        Assert.assertNotNull(aMatchBuilder.build());

        aMatchBuilder.build();
    }
}
