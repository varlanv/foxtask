package com.company.foxtask.unit.contollers;

import com.company.foxtask.contollers.CategoryController;
import com.company.foxtask.model.entity.Category;
import com.company.foxtask.model.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @Mock
    private CategoryRepository repository;
    private CategoryController controller;

    @Before
    public void construct() {
        controller = new CategoryController(repository);
    }

    @Test
    public void controller_should_return_all() {
        Category category = new Category();
        List<Category> categoriesBefore = singletonList(category);
        when(repository.findAll()).thenReturn(categoriesBefore);
        List<Category> categoriesAfter = controller.allCategories();

        verify(repository, only()).findAll();
        assertTrue(categoriesAfter.contains(category));
        assertEquals(categoriesBefore.size(), categoriesAfter.size());
    }
}
