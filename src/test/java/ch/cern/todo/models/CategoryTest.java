package ch.cern.todo.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryTest {

    @Test
    public void testConstructor() {
        Category category = new Category("Sample Category", "Sample Description");
        assert category.getCategoryName().equals("Sample Category");
        assert category.getCategoryDescription().equals("Sample Description");
    }

    @Test
    public void emptyConstructor() {
        Category category = new Category();
        category.setCategoryName("Sample Category");
        category.setCategoryDescription("Sample Description");
        category.setId(25L);
        assert category.getCategoryName().equals("Sample Category");
        assert category.getCategoryDescription().equals("Sample Description");
        assert category.getId() == 25L;
    }

}
