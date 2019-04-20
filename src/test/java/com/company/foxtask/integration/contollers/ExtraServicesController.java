package com.company.foxtask.integration.contollers;

import com.company.foxtask.contollers.ExtraServiceController;
import com.company.foxtask.model.entity.ExtraService;
import com.company.foxtask.model.repository.ExtraServiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExtraServiceController.class)
@RunWith(SpringRunner.class)
public class ExtraServicesController {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ExtraServiceRepository repository;

    @Before
    public void construct() {
        ExtraService extraService1 = new ExtraService();
        ExtraService extraService2 = new ExtraService();
        extraService1.setName("Cleaning");
        extraService2.setName("Breakfast");
        when(repository.findAll()).thenReturn(asList(extraService1, extraService2));
    }

    @Test
    public void controller_should_return_all() throws Exception {


        String contentAsString = mockMvc.perform(get("/extra-services"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        verify(repository, only()).findAll();

        List<ExtraService> extraServices = asList(mapper.readValue(contentAsString, ExtraService[].class));

        extraServices.stream()
                .map(ExtraService::getName)
                .forEach(s -> assertThat(s).isIn("Cleaning", "Breakfast"));
    }
}