package ca.ulaval.glo4003.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ulaval.glo4003.model.NoAvailableTicketsException;
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

    @RequestMapping(value = "/purchaseReview/{venue}/{date}/{sectionName}", method = RequestMethod.GET)
    public String reviewSelectedTicketsForSection(@PathVariable String venue, @PathVariable String date,
                                                  @PathVariable String sectionName,
                                                  @RequestParam(value = "quantity", required = true) int quantity,
                                                  Model model) {
        SectionViewModel viewModel = sectionConverter.convert(matchRepository.getMatchByIdentifier(venue + "/" + date)
                                                                             .getSectionByName(sectionName));
        model.addAttribute("section", viewModel);
        model.addAttribute("quantity", quantity);

        return "ticketPurchaseReview";
    }

    @RequestMapping(value = "/purchaseConfirm/{venue}/{date}/{sectionName}", method = RequestMethod.POST)
    public String purchaseSelectedTicketsForSection(@PathVariable String venue, @PathVariable String date,
                                                    @PathVariable String sectionName,
                                                    @RequestParam(value = "quantity", required = true) int quantity,
                                                    Model model) {
        try {
            matchRepository.getMatchByIdentifier(venue + "/" + date).buyTickets(sectionName, quantity);
        } catch (NoAvailableTicketsException e) {
            String message = "There are not enough available tickets";
            model.addAttribute("message", message);
            return "sectionDetails";
        }
        return "home";
    }

    protected TicketPurchaseController(MatchRepository matchRepository, SectionViewConverter sectionConverter) {
        this.matchRepository = matchRepository;
        this.sectionConverter = sectionConverter;
    }
}