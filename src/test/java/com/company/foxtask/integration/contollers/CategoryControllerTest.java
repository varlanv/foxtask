package com.company.foxtask.integration.contollers;

import com.company.foxtask.contollers.CategoryController;
import com.company.foxtask.model.entity.Category;
import com.company.foxtask.model.repository.CategoryRepository;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@RunWith(SpringRunner.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private CategoryRepository repository;

    @Before
    public void construct() {
        Category category1 = new Category();
        Category category2 = new Category();
        category1.setName("LUX");
        category2.setName("SINGLE");

        when(repository.findAll()).thenReturn(asList(category1, category2));
    }

    @Test
    public void abc() throws Exception {
        String contentAsString = mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        List<Category> categories = asList(mapper.readValue(contentAsString, Category[].class));

        categories.stream()
                .map(Category::getName)
                .forEach(s -> assertThat(s).isIn("LUX", "SINGLE"));
    }
}
