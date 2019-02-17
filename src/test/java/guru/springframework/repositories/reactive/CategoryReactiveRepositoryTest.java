package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    private static final String CATEGORY_DESCRIPTION = "Category description";
    @Autowired
    private CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testCategorySave() {
        Category category = new Category();
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryReactiveRepository.save(category).block();

        Category fetched = categoryReactiveRepository.findByDescription(CATEGORY_DESCRIPTION).block();

        assertNotNull(fetched);
        assertEquals(CATEGORY_DESCRIPTION, fetched.getDescription());
    }
}