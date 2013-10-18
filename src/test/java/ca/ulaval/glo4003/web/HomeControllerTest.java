package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import ca.ulaval.glo4003.web.HomeController;

public class HomeControllerTest {

    @Mock
    Model model;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void canGetHome() {
        HomeController homeController = new HomeController();
        assertEquals("home", homeController.home(model));
    }

}
