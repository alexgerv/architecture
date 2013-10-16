package ca.ulaval.glo4003.web;

import matchCatalog.MatchCatalog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.repository.MatchRepository;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

@Controller
public class MatchListController {

    private MatchViewConverter matchConverter = new MatchViewConverter();

    @RequestMapping(value = "/matchList", method = RequestMethod.GET)
    public String matchList(Model model) {

        MatchCatalog.getInstance();
        model.addAttribute("matches", matchConverter.convert(MatchRepository.getInstance().getAllLoadedEntries()));

        return "matchList";
    }

    @RequestMapping(value = "/match/{venue}/{date}", method = RequestMethod.GET)
    public String match(@PathVariable String venue, @PathVariable String date, Model model) {
        MatchViewModel viewModel = matchConverter.convert(MatchRepository.getInstance().getMatchByIdentifier(venue + "/" + date));
        model.addAttribute("match", viewModel);

        return "matchDetails";
    }
}