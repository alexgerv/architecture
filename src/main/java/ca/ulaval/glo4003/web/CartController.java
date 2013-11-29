package ca.ulaval.glo4003.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.domain.payment.TransactionManager;
import ca.ulaval.glo4003.domain.payment.TransactionService;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.TicketViewConverter;
import ca.ulaval.glo4003.web.viewmodels.TicketViewModel;

@Controller
@Scope("session")
public class CartController {

    @Inject
    TransactionService transactionService;
    @Inject
    TransactionManager transactionManager;

    @Autowired
    ShoppingCart shoppingCart;

    private TicketViewConverter ticketConverter = new TicketViewConverter();

    public CartController() {

    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Model model) {
        List<TicketViewModel> tickets = ticketConverter.convert(shoppingCart.getTickets());
        model.addAttribute("tickets", tickets);
        return "cart";
    }

    @RequestMapping(value = "/cart/remove/{ticketID}", method = RequestMethod.POST)
    public String removeATicketFromCart(Model model, @PathVariable String ticketID) {
        shoppingCart.remove(Integer.parseInt(ticketID));
        return cart(model);
    }

    protected CartController(TransactionService transactionService, TransactionManager transactionManager,
                             ShoppingCart shoppingCart, TicketViewConverter ticketConverter) {
        this.transactionService = transactionService;
        this.transactionManager = transactionManager;
        this.shoppingCart = shoppingCart;
        this.ticketConverter = ticketConverter;
    }
}
