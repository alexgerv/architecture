package ca.ulaval.glo4003.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

@Controller
public class MatchListController {

    @Inject
    MatchRepository matchRepository;

    @Inject
    MatchCatalog matchCatalog;

    @Inject
    MatchViewConverter matchConverter;

    @Inject
    SectionViewConverter sectionConverter;

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

    @RequestMapping(value = "/match/{venue}/{date}/{sectionName}", method = RequestMethod.GET)
    public String getSection(@PathVariable String venue, @PathVariable String date, Model model,
                             @PathVariable String sectionName) {
        SectionViewModel viewModel = sectionConverter.convert(matchRepository.getMatchByIdentifier(venue + "/" + date)
                                                                             .getSectionByName(sectionName));
        model.addAttribute("section", viewModel);

        return "sectionDetails";
    }

    protected MatchListController(MatchRepository matchRepository, MatchViewConverter matchViewConverter,
            SectionViewConverter sectionConverter) {
        this.matchConverter = matchViewConverter;
        this.matchRepository = matchRepository;
        this.sectionConverter = sectionConverter;
    }
}