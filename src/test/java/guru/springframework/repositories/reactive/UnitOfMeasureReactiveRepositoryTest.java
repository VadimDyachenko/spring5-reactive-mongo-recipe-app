package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
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
public class UnitOfMeasureReactiveRepositoryTest {

    private static final String EACH = "Each";

    @Autowired
    private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    private UnitOfMeasure uom;

    @Before
    public void setUp() {
        unitOfMeasureReactiveRepository.deleteAll().block();

        uom = new UnitOfMeasure();
        uom.setDescription(EACH);
    }

    @Test
    public void testSaveUom() {
        unitOfMeasureReactiveRepository.save(uom).block();

        Long count = unitOfMeasureReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindByDescription() {
        unitOfMeasureReactiveRepository.save(uom).block();

        UnitOfMeasure fetched = unitOfMeasureReactiveRepository.findByDescription(EACH).block();

        assertNotNull(fetched);
        assertEquals(EACH, fetched.getDescription());
    }
}