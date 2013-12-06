package ca.ulaval.glo4003.domain.shoppingCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.match.Ticket;

@Component
@Scope("session")
public class ShoppingCart {

    private Map<Section, List<Ticket>> cartContent = new HashMap<Section, List<Ticket>>();

    public void addTicketsQuantity(Match match, String sectionName, int quantity) {
        Section section = match.getSectionByName(sectionName);
        if (cartContent.containsKey(section)) {
            addTickets(match, sectionName, cartContent.get(section), quantity);
        } else {
            List<Ticket> newTicketList = new ArrayList<Ticket>();
            cartContent.put(section, newTicketList);
            addTickets(match, sectionName, newTicketList, quantity);
        }
    }

    public void changeTicketsQuantity(Match match, String sectionName, int newQuantity) {
        Section section = match.getSectionByName(sectionName);
        List<Ticket> sectionTickets = cartContent.get(section);
        int currentQuantity = sectionTickets.size();
        if (currentQuantity < newQuantity) {
            int quantityToAdd = newQuantity - currentQuantity;
            addTickets(match, sectionName, sectionTickets, quantityToAdd);
        } else {
            int quantityToRemove = currentQuantity - newQuantity;
            removeTickets(sectionTickets, quantityToRemove);
        }
    }

    private void addTickets(Match match, String sectionName, List<Ticket> sectionTickets, int quantityToAdd) {
        List<Ticket> tickets = match.reserveTickets(quantityToAdd, sectionName);
        sectionTickets.addAll(tickets);
    }

    private void removeTickets(List<Ticket> sectionTickets, int quantityToRemove) {
        int indexToRemove = 0;
        while (quantityToRemove != 0) {
            Ticket removedTicket = sectionTickets.remove(indexToRemove);
            removedTicket.free();
            --quantityToRemove;
        }
    }

    public Map<Section, List<Ticket>> getCartContent() {
        return cartContent;
    }

}
