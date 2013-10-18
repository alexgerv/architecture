package ca.ulaval.glo4003.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.repository.MatchRepository;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

@Controller
public class MatchListController {

    @Inject
    MatchRepository matchRepository;

    @Inject
    MatchCatalog matchCatalog;

    private MatchViewConverter matchConverter = new MatchViewConverter();

    public MatchListController() {

    }

    @RequestMapping(value = "/matchList", method = RequestMethod.GET)
    public String matchList(Model model) {

        return "matchList";
    }

    @RequestMapping(value = "/match/{venue}/{date}", method = RequestMethod.GET)
    public String getMatch(@PathVariable String venue, @PathVariable String date, Model model) {
        MatchViewModel viewModel = matchConverter.convert(matchRepository.getMatchByIdentifier(venue + "/" + date));
        model.addAttribute("match", viewModel);

        return "matchDetails";
    }

    protected MatchListController(MatchRepository matchRepository, MatchViewConverter matchViewConverter) {
        this.matchConverter = matchViewConverter;
        this.matchRepository = matchRepository;
    }
}