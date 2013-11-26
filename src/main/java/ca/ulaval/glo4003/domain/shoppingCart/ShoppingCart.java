package ca.ulaval.glo4003.domain.shoppingCart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.Ticket;

@Component
@Scope("session")
public class ShoppingCart {

    private List<Ticket> tickets = new ArrayList<Ticket>();

    public void addTickets(Match match, int quantity, String sectionName) {
        tickets.addAll((match.getTickets(quantity, sectionName)));
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
