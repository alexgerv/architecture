package ca.ulaval.glo4003.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.TicketViewConverter;
import ca.ulaval.glo4003.web.viewmodels.TicketViewModel;

@Controller
@Scope("session")
public class CartController {

    @Autowired
    ShoppingCart shoppingCart;

    private TicketViewConverter ticketConverter = new TicketViewConverter();

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
}
