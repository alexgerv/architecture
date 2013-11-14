package ca.ulaval.glo4003.web.converters;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

public class SectionViewConverterTest {

    private static final String A_FORMATED_DATE = "2013-11-05 11h38";

    @Mock
    Section aSection;

    SectionViewConverter aSectionViewConverter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        aSectionViewConverter = new SectionViewConverter();
        doReturn(A_FORMATED_DATE).when(aSection).getDate();
    }

    @Test
    public void whenConvertingASectionToASectionViewModelTheConverterReturnsASectionViewModel() {
        Mockito.when(aSection.getDate()).thenReturn(A_FORMATED_DATE);
        assertTrue(aSectionViewConverter.convert(aSection) instanceof SectionViewModel);
    }

    @Test
    public void whenConvertingAMapOfMatchToAMachViewModelCollectionTheConverterReturnsACollectionOfMatchViewModel() {
        List<Section> entries = new ArrayList<Section>();
        assertTrue(aSectionViewConverter.convert(entries) instanceof List<?>);
    }

    @Test
    public void whenConvertingASectionToASectionViewModelTheConverterUsesTheSectionAttributesToCreateTheModel() {
        Mockito.when(aSection.getDate()).thenReturn(A_FORMATED_DATE);

        aSectionViewConverter.convert(aSection);

        Mockito.verify(aSection).getAdmissionType();
        Mockito.verify(aSection).getDate();
        Mockito.verify(aSection).getHomeTeam();
        Mockito.verify(aSection).getNumberOfAvailableTickets();
        Mockito.verify(aSection).getPrice();
        Mockito.verify(aSection).getSex();
        Mockito.verify(aSection).getSport();
        Mockito.verify(aSection).getVenue();
        Mockito.verify(aSection).getVisitorTeam();

    }
}
