package ca.ulaval.glo4003.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.ulaval.glo4003.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.matchCatalog.MatchFilterCategories;
import ca.ulaval.glo4003.matchCatalog.MatchQueryFactory;
import ca.ulaval.glo4003.matchCatalog.Query;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

@Controller
public class SearchBarController {

    @Inject
    MatchCatalog matchCatalog;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    List<MatchViewModel> searchResults(@RequestBody String request) {
        MatchQueryFactory factory = new MatchQueryFactory();
        Query<MatchFilterCategories> query = factory.create(request);
        MatchViewConverter converter = new MatchViewConverter();
        List<MatchViewModel> matchList =
                                         (List<MatchViewModel>) converter.convert(matchCatalog.getMatchesFromQuery(query));

        return matchList;
    }
}
