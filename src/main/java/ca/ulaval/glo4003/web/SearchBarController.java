package ca.ulaval.glo4003.web;

import java.util.ArrayList;
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

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

@Controller
public class SearchBarController {

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody
    List<MatchViewModel> searchResults(@RequestBody String request) {
        List<MatchViewModel> matchList = new ArrayList<MatchViewModel>();
        return matchList;
    }
}
