package ca.ulaval.glo4003.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQuery;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQueryFactory;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

@Controller
public class SearchBarController {

    @Inject
    MatchCatalog matchCatalog;

    @Inject
    MatchQueryFactory matchQueryFactory;

    private MatchViewConverter matchConverter = new MatchViewConverter();

    public SearchBarController() {

    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    List<MatchViewModel> searchResults(@RequestBody String request) {
        MatchQuery query = matchQueryFactory.create(request);
        List<MatchViewModel> matchList = (List<MatchViewModel>) matchConverter.convert(matchCatalog.getMatchesFromQuery(query));
        return matchList;
    }

    protected SearchBarController(MatchCatalog matchCatalog, MatchQueryFactory matchQueryFactory,
            MatchViewConverter matchViewConverter) {
        this.matchCatalog = matchCatalog;
        this.matchQueryFactory = matchQueryFactory;
        this.matchConverter = matchViewConverter;
    }
}
