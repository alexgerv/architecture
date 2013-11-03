package ca.ulaval.glo4003.web.converters;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import ca.ulaval.glo4003.model.Section;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

public class SectionViewConverter {

    private static final String DATE_FORMAT_TEMPLATE = "yyyy-MM-dd_HH-mm-ss";

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
        viewModel.setSection(entry.getName());
        viewModel.setDate(DateFormatUtils.format(entry.getDate(), DATE_FORMAT_TEMPLATE));
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
