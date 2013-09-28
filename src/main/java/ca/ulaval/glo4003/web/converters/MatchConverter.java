package ca.ulaval.glo4003.web.converters;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

public class MatchConverter {

    public Collection<MatchViewModel> convert(List<Match> entries) {
        Collection<MatchViewModel> viewModels = new LinkedList<MatchViewModel>();
        for (int i = 0; i < entries.size(); i++) {
            MatchViewModel viewModel = convert(entries.get(i));
            viewModel.matchID = i;
            viewModels.add(viewModel);
        }
        return viewModels;
    }

    public MatchViewModel convert(Match entry) {
        MatchViewModel viewModel = new MatchViewModel();

        viewModel.sport = entry.getSport();
        viewModel.venue = entry.getVenue();
        viewModel.date = entry.getDate();
        viewModel.homeTeam = entry.getHomeTeam();
        viewModel.visitorTeam = entry.getVisitorTeam();
        viewModel.availableTicketsBySection = entry.getAvailableTicketsBySection();
        viewModel.totalNumberOfAvailableTickets = entry.getTotatNumberOfAvailableTickets();

        return viewModel;
    }
}
