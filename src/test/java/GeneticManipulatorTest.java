
import it.unina.ceccarino.gaforjss.algo.GeneticManipulator;
import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import it.unina.ceccarino.gaforjss.model.Machine;
import it.unina.ceccarino.gaforjss.model.Settings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
        JobIndividual[] evicted = (JobIndividual[]) GeneticManipulator.getInstance().evict(array, 4);

        assertEquals(6, evicted.length);

    }

    @Test
    public void testEvictAndShrink() {

        JobIndividual[] array = new JobIndividual[10];

        for (int i = 0; i < 10; i++) {
            array[i] = new JobIndividual();
        }
        Pair<JobIndividual[], JobIndividual[]> evictAndShrink = GeneticManipulator.getInstance().evictAndShrink(array, 4);
        JobIndividual[] evicted = evictAndShrink.getLeft();
        array = evictAndShrink.getRight();

        assertEquals(4, evicted.length);
        assertEquals(6, array.length);

    }

    @Test
    public void testValidateSolution() {
        try {
            int N = 14;
            Settings.getInstance().setMaxJobOveralQuantity(N);
            JobIndividual generateJobIndividual = InputManager.getInstance().generateJobIndividual();
            Map<Integer, Integer> jobQuantityMap = InputManager.getInstance().getJobQuantityMap();

            assertEquals(10, jobQuantityMap.size(), "la mappa dovrebbe avere un size di 10");

            int totalJob = 0;
            for (Integer value : jobQuantityMap.values()) {
                totalJob += value;
            }

            assertEquals(N, totalJob);
            assertEquals(N*6, generateJobIndividual.getJobPermutation().length);
            System.out.println(generateJobIndividual);
            for (int i = 1; i < N; i++) {
                Integer rightQuantity = jobQuantityMap.get(i) * 6;
                int check = 0;
                for (int k : generateJobIndividual.getJobPermutation()) {
                    if (k == i) {
                        check++;
                    }
                }
                System.out.println("right quantity di "+i+ " è "+rightQuantity);
                System.out.println("numero di "+i+" -> "+check);
                assertEquals(rightQuantity, check);
            }

            boolean validOne = GeneticManipulator.getInstance().validateSolution(generateJobIndividual);

            Assertions.assertTrue(validOne);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void testCrossover() {

        JobIndividual dad = InputManager.getInstance().generateJobIndividual();
        JobIndividual mum = InputManager.getInstance().generateJobIndividual();
        
        boolean validDad = GeneticManipulator.getInstance().validateSolution(dad);
        boolean validMum = GeneticManipulator.getInstance().validateSolution(mum);
        
        JobIndividual son = GeneticManipulator.getInstance().crossOver(dad, mum);
        
        boolean validSon = GeneticManipulator.getInstance().validateSolution(son);
        
        Assertions.assertTrue(validDad, "il padre non è valido");
        Assertions.assertTrue(validMum, "la madre non è valida");
        Assertions.assertTrue(validSon, "il figlio non è valido");
        
    }
    
    @Test
    public void testMutation(){
        JobIndividual mutante = InputManager.getInstance().generateJobIndividual();
        mutante.swap(1);
        
         boolean validMutante = GeneticManipulator.getInstance().validateSolution(mutante);
        
        Assertions.assertTrue(validMutante, "il mutante non è valido");
    }

}
