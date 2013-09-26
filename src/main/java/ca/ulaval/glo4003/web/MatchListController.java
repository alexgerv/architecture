
package ca.ulaval.glo4003.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.dao.MatchRepository;
import ca.ulaval.glo4003.web.converters.MatchConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

@Controller
public class MatchListController {
	private MatchRepository repository = new MatchRepository();
	private MatchConverter matchConverter = new MatchConverter();
	
    @RequestMapping(value = "/matchList", method = RequestMethod.GET)
    public String matchList(Model model) {
        model.addAttribute("matches", matchConverter.convert(repository.getAll()));

        return "matchList";
    }

    @RequestMapping(value = "/match/{matchID}", method = RequestMethod.GET)
    public String match(@PathVariable int matchID, Model model) {
		MatchViewModel viewModel = matchConverter.convert(repository.getById(matchID));
		model.addAttribute("availableTicketsBySection", viewModel.getAvailableTicketsBySection());

    	return "matchDetails";
    }
}
