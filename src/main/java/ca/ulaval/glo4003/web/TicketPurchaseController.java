package ca.ulaval.glo4003.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.domain.payment.TicketPurchaseFacade;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.CreditCardViewModel;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

@Controller
@Scope("session")
public class TicketPurchaseController {

    @Inject
    TicketPurchaseFacade ticketPurchaseFacade;
    @Inject
    ShoppingCart shoppingCart;

    private SectionViewConverter sectionConverter = new SectionViewConverter();

    public TicketPurchaseController() {

    }

    @RequestMapping(value = "/purchaseReview/{venue}/{date}/{sectionName}", method = RequestMethod.GET)
    public String reviewSelectedTicketsForSection(@PathVariable String venue,
                                                  @PathVariable String date,
                                                  @PathVariable String sectionName,
                                                  @RequestParam(value = "quantity", required = true) int quantity,
                                                  Model model,
                                                  @ModelAttribute(value = "creditCardForm") CreditCardViewModel creditCard) {
        SectionViewModel viewModel = ticketPurchaseFacade.retriveSectionInformations(venue, date, sectionName, quantity);
        float purchaseTotal = ticketPurchaseFacade.computePurchaseTotal(viewModel, quantity);

        model.addAttribute("purchaseTotal", purchaseTotal);
        model.addAttribute("sections", Arrays.asList(viewModel));
        model.addAttribute("quantity", quantity);
        model.addAttribute("purchaseURL", String.format("/purchaseReceipt/%s/%s/%s", venue, date, sectionName));

        return "ticketPurchaseReview";
    }

    @RequestMapping(value = "/purchaseReceipt/{venue}/{date}/{sectionName}", method = RequestMethod.POST)
    public String purchaseSelectedTicketsForSection(@PathVariable String venue,
                                                    @PathVariable String date,
                                                    @PathVariable String sectionName,
                                                    @RequestParam(value = "quantity", required = true) int quantity,
                                                    Model model,
                                                    @ModelAttribute(value = "creditCardForm") CreditCardViewModel creditCard) {

        SectionViewModel viewModel = ticketPurchaseFacade.retriveSectionInformations(venue, date, sectionName, quantity);
        float purchaseTotal = ticketPurchaseFacade.computePurchaseTotal(viewModel, quantity);

        model.addAttribute("purchaseTotal", purchaseTotal);
        model.addAttribute("sections", Arrays.asList(viewModel));

        String purchaseState = ticketPurchaseFacade.processPurchase(model, venue, date, sectionName, quantity,
                                                                    creditCard);
        return purchaseState;
    }

    @RequestMapping(value = "/purchase/cart", method = RequestMethod.POST)
    public String purchaseCartContent(Model model,
                                      @ModelAttribute(value = "creditCardForm") CreditCardViewModel creditCard) {

        Map<Section, List<Ticket>> cartContents = shoppingCart.getCartContent();
        List<SectionViewModel> sectionsInCart = sectionConverter.convert(cartContents);

        model.addAttribute("purchaseTotal", shoppingCart.getCartValue());
        model.addAttribute("sections", sectionsInCart);

        String cartPurchaseState = ticketPurchaseFacade.processCartPurchase(model, shoppingCart, creditCard);

        return cartPurchaseState;
    }

    // For tests purpose only
    protected TicketPurchaseController(TicketPurchaseFacade ticketPurchaseFacade,
            SectionViewConverter sectionConverter, ShoppingCart shoppingCart) {
        this.ticketPurchaseFacade = ticketPurchaseFacade;
        this.sectionConverter = sectionConverter;
        this.shoppingCart = shoppingCart;
    }
}