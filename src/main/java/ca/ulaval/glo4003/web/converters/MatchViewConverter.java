package ca.ulaval.glo4003.web.converters;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.time.DateFormatUtils;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

public class MatchViewConverter {

    private static final String DATE_FORMAT_TEMPLATE = "yyyy-MM-dd_HH-mm-ss";
    private SectionViewConverter sectionConverter = new SectionViewConverter();

    public Collection<MatchViewModel> convert(Map<String, Match> entries) {
        Collection<MatchViewModel> viewModels = new LinkedList<MatchViewModel>();
        for (Entry<String, Match> entry : entries.entrySet()) {
            MatchViewModel viewModel = convert(entry.getValue());
            viewModel.setMatchIdentifier(entry.getKey());
            viewModels.add(viewModel);
        }
        return viewModels;
    }

    public MatchViewModel convert(Match entry) {
        MatchViewModel viewModel = new MatchViewModel();

        viewModel.setSport(entry.getSport());
        viewModel.setVenue(entry.getVenue());
        viewModel.setDate(DateFormatUtils.format(entry.getDate(), DATE_FORMAT_TEMPLATE));
        viewModel.setHomeTeam(entry.getHomeTeam());
        viewModel.setVisitorTeam(entry.getVisitorTeam());
        viewModel.setSex(entry.getSex());
        viewModel.setTicketsBySection(sectionConverter.convert(entry.getTicketsBySection()));
        viewModel.setTotalNumberOfTickets(entry.getTotatNumberOfAvailableTickets());
        viewModel.setMatchIdentifier(entry.getIdentifier());

        return viewModel;
    }
}
