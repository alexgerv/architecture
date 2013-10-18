package ca.ulaval.glo4003.web.converters;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.time.DateFormatUtils;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

public class MatchViewConverter {
	
	private static final String DATE_FORMAT_TEMPLATE = "yyyy-MM-dd HH:mm";

    public Collection<MatchViewModel> convert(Map<String, Match> entries) {
		Collection<MatchViewModel> viewModels = new LinkedList<MatchViewModel>();
		for (Entry<String, Match> entry : entries.entrySet()) {
			MatchViewModel viewModel = convert(entry.getValue());
			viewModel.matchIdentifier = entry.getKey();
			viewModels.add(viewModel);
		}
		return viewModels;
	}
	
	public MatchViewModel convert(Match entry) {
		MatchViewModel viewModel = new MatchViewModel();
		
	    viewModel.sport = entry.getSport();
	    viewModel.venue = entry.getVenue();
	    viewModel.date = DateFormatUtils.format(entry.getDate(), DATE_FORMAT_TEMPLATE);
		viewModel.homeTeam = entry.getHomeTeam();
		viewModel.visitorTeam = entry.getVisitorTeam();
		viewModel.availableTicketsBySection = entry.getAvailableTicketsBySection();
		viewModel.totalNumberOfAvailableTickets = entry.getTotatNumberOfAvailableTickets();
		
		return viewModel;
	}
}
