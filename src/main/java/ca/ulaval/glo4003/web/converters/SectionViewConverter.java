package ca.ulaval.glo4003.web.converters;

import java.util.LinkedList;
import java.util.List;

import ca.ulaval.glo4003.model.Section;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

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

        viewModel.setName(entry.getName());
        viewModel.setAvailableTickets(entry.getNumberOfAvailableTickets());

        return viewModel;
    }
}
