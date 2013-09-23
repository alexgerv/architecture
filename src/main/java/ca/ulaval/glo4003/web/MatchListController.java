
package ca.ulaval.glo4003.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.model.MatchBuilder;

@Controller
public class MatchListController {
    @RequestMapping(value = "/matchList", method = RequestMethod.GET)
    public String matchList(Locale locale, Model model) {
    	
    	//TODO remove this, it is just for a fun test.
    	ArrayList<Match> matchList = new ArrayList<Match>();
    	MatchBuilder matchBuilder = new MatchBuilder();
    	Date currentDate = new Date();
    	Match match1 = matchBuilder.setSport("Soccer").setDate(currentDate).setHomeTeam("ULaval").setVenue("Stade Telus").setVisitorTeam("Sherbrook").createSection("A", 10).build();
    	Match match2 = matchBuilder.setSport("Football").setDate(currentDate).setHomeTeam("ULaval").setVenue("Stade").setVisitorTeam("Sherbrook").createSection("A", 10).createSection("B", 20).build();

    	matchList.add(match1);
    	matchList.add(match2);
    	
        model.addAttribute("matches", matchList);

        return "matchList";
    }

}
