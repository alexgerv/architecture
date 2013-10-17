package ca.ulaval.glo4003.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.matchCatalog.MatchCatalogFactory;
import ca.ulaval.glo4003.repository.MatchRepository;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

@Controller
public class MatchListController {

    @Inject
    MatchRepository matchRepository;

    private MatchViewConverter matchConverter = new MatchViewConverter();

    @RequestMapping(value = "/matchList", method = RequestMethod.GET)
    public String matchList(Model model) {

        // List<Filter<MatchFilterCategories>> filterListByCategories = new
        // ArrayList<Filter<MatchFilterCategories>>();
        // for (MatchFilterCategories category : MatchFilterCategories.values())
        // {
        // filterListByCategories.add(new
        // Filter<MatchFilterCategories>(category));
        // }
        // Index<MatchFilterCategories> index = new
        // IndexWithList<MatchFilterCategories>(filterListByCategories);
        // QueryResolver<MatchFilterCategories> queryResolver = new
        // QueryResolver<MatchFilterCategories>(index);

        MatchCatalogFactory catalogFactory = new MatchCatalogFactory(matchRepository);
        MatchCatalog matchCatalog = catalogFactory.createMatchCatalog();
        // MatchCatalog matchCatalog = new MatchCatalog(queryResolver, index,
        // matchRepository);

        model.addAttribute("matches", matchConverter.convert(matchRepository.getAllLoadedEntries()));
        return "matchList";
    }

    @RequestMapping(value = "/match/{venue}/{date}", method = RequestMethod.GET)
    public String match(@PathVariable String venue, @PathVariable String date, Model model) {
        MatchViewModel viewModel = matchConverter.convert(matchRepository.getMatchByIdentifier(venue + "/" + date));
        model.addAttribute("match", viewModel);

        return "matchDetails";
    }
}