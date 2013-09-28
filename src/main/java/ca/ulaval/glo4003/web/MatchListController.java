package ca.ulaval.glo4003.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.repository.MatchRepository;
import ca.ulaval.glo4003.web.converters.MatchConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

@Controller
public class MatchListController {

    @Inject
    private MatchRepository repository;

    private MatchConverter matchConverter = new MatchConverter();

    @RequestMapping(value = "/matchList", method = RequestMethod.GET)
    public String matchList(Model model) {

        if (repository.isEmpty()) {
            repository.loadAll();
        }
        // MatchQuery query = new MatchQuery();
        // query.addFilter(MatchFilter.SPORT, "Soccer");
        // query.addFilter(MatchFilter.VENUE, "Montreal");
        // model.addAttribute("matches",
        // matchConverter.convert(repository.getFromQuery(query)));
        model.addAttribute("matches", matchConverter.convert(repository.getAllLoadedEntries()));

        return "matchList";
    }

    @RequestMapping(value = "/match/{matchID}", method = RequestMethod.GET)
    public String match(@PathVariable int matchID, Model model) {
        MatchViewModel viewModel = matchConverter.convert(repository.getById(matchID));
        model.addAttribute("match", viewModel);

        return "matchDetails";
    }
}
