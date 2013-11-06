package ca.ulaval.glo4003.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ulaval.glo4003.repository.MatchRepository;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

@Controller
public class TicketPurchaseController {

    @Inject
    MatchRepository matchRepository;

    private SectionViewConverter sectionConverter = new SectionViewConverter();

    public TicketPurchaseController() {

    }

    @RequestMapping(value = "/purchase/{venue}/{date}/{sectionName}", method = RequestMethod.GET)
    public String getTicketQuantityBySection(@PathVariable String venue, @PathVariable String date,
                                             @PathVariable String sectionName,
                                             @RequestParam(value = "quantity", required = true) int quantity,
                                             Model model) {
        SectionViewModel viewModel = sectionConverter.convert(matchRepository.getMatchByIdentifier(venue + "/" + date)
                                                                             .getSectionByName(sectionName));
        model.addAttribute("section", viewModel);
        model.addAttribute("quantity", quantity);

        return "ticketPurchase";
    }

    protected TicketPurchaseController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }
}