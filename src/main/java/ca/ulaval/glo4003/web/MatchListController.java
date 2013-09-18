
package ca.ulaval.glo4003.web;

import java.util.ArrayList;
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
    	Match match = matchBuilder.setSport("Soccer").build();
    	matchList.add(match);
    	
        model.addAttribute("matches", matchList);

        return "matchList";
    }

}
