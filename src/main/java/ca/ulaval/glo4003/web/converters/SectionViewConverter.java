package ca.ulaval.glo4003.web.converters;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

@Repository
@Singleton
public class SectionViewConverter {

    public List<SectionViewModel> convert(List<Section> entries) {
        List<SectionViewModel> viewModels = new LinkedList<SectionViewModel>();
        for (Section entry : entries) {
            SectionViewModel viewModel = convert(entry);
            viewModels.add(viewModel);
        }
        return viewModels;

    }

    public SectionViewModel convert(Section entry) {
        SectionViewModel viewModel = new SectionViewModel();

        viewModel.setSport(entry.getSport());
        viewModel.setVenue(entry.getVenue());
        viewModel.setSection(entry.getName());
        viewModel.setDate(entry.getDate());
        viewModel.setHomeTeam(entry.getHomeTeam());
        viewModel.setVisitorTeam(entry.getVisitorTeam());
        viewModel.setSex(entry.getSex());
        viewModel.setName(entry.getName());
        viewModel.setAvailableTickets(entry.getNumberOfAvailableTickets());
        viewModel.setPrice(entry.getPrice());
        viewModel.setAdmissionType(entry.getAdmissionType());

        return viewModel;
    }
}
