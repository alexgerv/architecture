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
        tickets.addAll((match.reserveTickets(quantity, sectionName)));
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void remove(int ticketID) {
        for (Ticket ticket : tickets) {
            if (ticket.hasID(ticketID)) {
                removeTicketFromCart(ticket);
                break;
            }
        }
    }

    private void removeTicketFromCart(Ticket ticket) {
        ticket.free();
        tickets.remove(ticket);
    }
}
