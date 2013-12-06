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
            addTickets(section, quantity);
        } else {
            List<Ticket> newTicketList = new ArrayList<Ticket>();
            cartContent.put(section, newTicketList);
            addTickets(section, quantity);
        }
    }

    public void changeTicketsQuantity(Match match, String sectionName, int newQuantity) {
        Section section = match.getSectionByName(sectionName);
        List<Ticket> sectionTickets = cartContent.get(section);
        int currentQuantity = sectionTickets.size();
        if (currentQuantity < newQuantity) {
            int quantityToAdd = newQuantity - currentQuantity;
            addTickets(section, quantityToAdd);
        } else {
            int quantityToRemove = currentQuantity - newQuantity;
            removeTickets(section, quantityToRemove);
        }
    }

    private void addTickets(Section section, int quantityToAdd) {
        List<Ticket> tickets = section.reserveTickets(quantityToAdd);
        cartContent.get(section).addAll(tickets);
    }

    private void removeTickets(Section section, int quantityToRemove) {
        int indexToRemove = 0;
        List<Ticket> sectionTickets = cartContent.get(section);
        if (sectionTickets.size() == quantityToRemove) {
            cartContent.remove(section);
        }
        for (int i = quantityToRemove; i > 0; i--) {
            Ticket removedTicket = sectionTickets.remove(indexToRemove);
            removedTicket.free();
        }
    }

    public Map<Section, List<Ticket>> getCartContent() {
        return cartContent;
    }

    public void removeSectionFromCart(Match match, String sectionName) {
        changeTicketsQuantity(match, sectionName, 0);
        cartContent.remove(match.getSectionByName(sectionName));
    }

}
