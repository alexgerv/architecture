package ca.ulaval.glo4003.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import ca.ulaval.glo4003.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.matchCatalog.MatchFilterCategories;
import ca.ulaval.glo4003.matchCatalog.MatchQueryFactory;
import ca.ulaval.glo4003.matchCatalog.Query;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

@Controller
public class SearchBarController {

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    List<MatchViewModel> searchResults(@RequestBody String request) {
        MatchQueryFactory factory = new MatchQueryFactory();
        Query<MatchFilterCategories> query = factory.create(request);
        MatchViewConverter converter = new MatchViewConverter();
        List<MatchViewModel> matchList = (List<MatchViewModel>) converter.convert(MatchCatalog.getInstance().getMatchesFromQuery(query));
        
        return matchList;
    }
}
