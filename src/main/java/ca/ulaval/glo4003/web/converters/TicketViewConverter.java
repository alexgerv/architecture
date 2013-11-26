package ca.ulaval.glo4003.web.converters;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Singleton;

import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.web.viewmodels.TicketViewModel;

@Singleton
public class TicketViewConverter {

    public List<TicketViewModel> convert(List<Ticket> entries) {
        List<TicketViewModel> viewModels = new LinkedList<TicketViewModel>();
        for (Ticket entry : entries) {
            TicketViewModel viewModel = convert(entry);
            viewModels.add(viewModel);
        }
        return viewModels;

    }

    public TicketViewModel convert(Ticket entry) {
        TicketViewModel viewModel = new TicketViewModel();

        viewModel.setSport(entry.getSport());
        viewModel.setVenue(entry.getVenue());
        viewModel.setDate(entry.getDate());
        viewModel.setHomeTeam(entry.getHomeTeam());
        viewModel.setVisitorTeam(entry.getVisitorTeam());
        viewModel.setSex(entry.getSex());
        viewModel.setID(entry.getID());
        viewModel.setPrice(entry.getPrice());
        viewModel.setAdmissionType(entry.getAdmissionType());

        return viewModel;
    }
}
