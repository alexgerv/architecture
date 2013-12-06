package ca.ulaval.glo4003.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.payment.TransactionManager;
import ca.ulaval.glo4003.domain.payment.TransactionService;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

@Controller
@Scope("session")
public class CartController {

    @Inject
    TransactionService transactionService;
    @Inject
    TransactionManager transactionManager;
    @Inject
    MatchRepository matchRepository;

    @Inject
    ShoppingCart shoppingCart;

    private SectionViewConverter sectionViewConverter = new SectionViewConverter();

    public CartController() {

    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Model model) {
        List<SectionViewModel> cartContent = sectionViewConverter.convert(shoppingCart.getCartContent());
        model.addAttribute("cartContent", cartContent);
        return "cart";
    }

    @RequestMapping(value = "/cart/add/{venue}/{date}/{sectionName}", method = RequestMethod.POST)
    public String reviewSelectedTicketsForSection(@PathVariable String venue, @PathVariable String date,
                                                  @PathVariable String sectionName,
                                                  @RequestParam(value = "quantity", required = true) int quantity,
                                                  Model model) {
        Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);
        shoppingCart.addTicketsQuantity(match, sectionName, quantity);

        List<SectionViewModel> cartContent = sectionViewConverter.convert(shoppingCart.getCartContent());
        model.addAttribute("cartContent", cartContent);

        return "cart";
    }

    @RequestMapping(value = "/cart/changeQuantity/{venue}/{date}/{setionName}", method = RequestMethod.POST)
    public String removeATicketFromCart(@PathVariable String venue, @PathVariable String date,
                                        @RequestParam(value = "quantity", required = true) int quantity,
                                        @PathVariable String sectionName, Model model) {
        Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);
        shoppingCart.changeTicketsQuantity(match, sectionName, quantity);
        return cart(model);
    }

    protected CartController(TransactionService transactionService, TransactionManager transactionManager,
                             ShoppingCart shoppingCart, SectionViewConverter sectionConverter) {
        this.transactionService = transactionService;
        this.transactionManager = transactionManager;
        this.shoppingCart = shoppingCart;
        this.sectionViewConverter = sectionConverter;
    }
}
