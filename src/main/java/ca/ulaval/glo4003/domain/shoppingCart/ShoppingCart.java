package ca.ulaval.glo4003.domain.shoppingCart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.match.Ticket;

@Component
@Scope("session")
public class ShoppingCart {

    private Map<Section, Integer> cartContent = new HashMap<Section, Integer>();

    public void addTickets(Match match, int quantity, String sectionName) {
        Section section = match.getSectionByName(sectionName);
        if (cartContent.containsKey(section)) {
            int newTicketQuantity = cartContent.get(section) + quantity;
            cartContent.put(section, newTicketQuantity);
        } else {
            cartContent.put(section, quantity);
        }
    }

    public Map<Section, Integer> getCartContent() {
        return cartContent;
    }

    public void remove(int ticketID) {
    }

    private void removeTicketFromCart(Ticket ticket) {
    }

}
