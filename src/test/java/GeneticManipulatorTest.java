
import it.unina.ceccarino.gaforjss.algo.GeneticManipulator;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import it.unina.ceccarino.gaforjss.model.Machine;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author sommovir
 */
public class GeneticManipulatorTest {

    public GeneticManipulatorTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

//    @Test
//    public void testPartitionArray() {
//
////        Integer[] array = {1, 2, 3, 4, 5, 6, 7};
////
////        Object[] partArray = GeneticManipulator.getInstance().partArray(array, 4);
////
////        assertNotNull(partArray);
////        assertEquals(Integer.class, partArray[0].getClass());
////
////        assertEquals(4, partArray.length);
////
////        assertEquals(1, partArray[0]);
////        assertEquals(2, partArray[1]);
////        assertEquals(3, partArray[2]);
////        assertEquals(4, partArray[3]);
//
//    }

//    @Test
//    public void testPartitionArrayShifted() {
////
////        Integer[] array = {1, 2, 3, 4, 5, 6, 7};
////
////        Object[] partArray = GeneticManipulator.getInstance().partArray(array, 2, 3);
////
////        assertNotNull(partArray);
////
////        assertEquals(Integer.class, partArray[0].getClass());
////
////        assertEquals(3, partArray.length);
////
////        assertEquals(3, partArray[0]);
////        assertEquals(4, partArray[1]);
////        assertEquals(5, partArray[2]);
//
//    }

    @Test
    public void testEvict() {

        JobIndividual[] array = new JobIndividual[10];

        for (int i = 0; i < 10; i++) {
            array[i] = new JobIndividual();
        }
        JobIndividual[] evicted = (JobIndividual[])GeneticManipulator.getInstance().evict(array, 4);

        assertEquals(6, evicted.length);

    }

}
