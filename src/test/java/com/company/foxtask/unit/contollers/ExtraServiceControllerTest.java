package com.company.foxtask.unit.contollers;

import com.company.foxtask.contollers.ExtraServiceController;
import com.company.foxtask.model.entity.ExtraService;
import com.company.foxtask.model.repository.ExtraServiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExtraServiceControllerTest {

    @Mock
    private ExtraServiceRepository repository;
    private ExtraServiceController controller;

    @Before
    public void construct() {
        ExtraService service = new ExtraService();
        service.setName("Cleaning");
        service.setPrice("10");

        ExtraService service1 = new ExtraService();
        service1.setName("Breakfast");
        service1.setPrice("5");

        when(repository.findAll()).thenReturn(asList(service, service1));
        controller = new ExtraServiceController(repository);
    }

    @Test
    public void controller_should_return_list() {
        List<ExtraService> services = controller.getAll();

        assertThat(services).isNotEmpty();
        assertThat(services).size().isEqualTo(2);
        assertThat(services).anyMatch(s -> s.getName().equals("Cleaning"));
    }
}