
import it.unina.ceccarino.gaforjss.algo.GeneticManipulator;
import it.unina.ceccarino.gaforjss.algo.Population;
import it.unina.ceccarino.gaforjss.exceptions.InvalidSettingsException;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import it.unina.ceccarino.gaforjss.model.Settings;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author sommovir
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
//@Disabled
public class PopulationTest {

    private boolean exceptionHasBeenThrownMinor = false;
    private boolean exceptionHasBeenThrownMajor = false;

    public PopulationTest() {
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

    @Order(1)
    @Test
    public void testElectedPercentageException() {
        assertThrows(InvalidSettingsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Settings.getInstance().setElectedPercentage(-13);
            }
        });
        exceptionHasBeenThrownMinor = true;
        assertThrows(InvalidSettingsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Settings.getInstance().setElectedPercentage(150);
            }
        });
        exceptionHasBeenThrownMajor = true;
    }

    @Order(2)
    @Test
    public void testElectedPercentage() {
//
        System.out.println(exceptionHasBeenThrownMajor);
        System.out.println(exceptionHasBeenThrownMinor);
        Assumptions.assumeTrue(exceptionHasBeenThrownMajor);
        Assumptions.assumeTrue(exceptionHasBeenThrownMinor);

        JobIndividual[] pool = new JobIndividual[11];
        for (int i = 0; i < 11; i++) {
            pool[i] = new JobIndividual();
        }

        JobIndividual[][] result = GeneticManipulator.getInstance().prepareCrossover(pool);

        assertEquals(2, result.length, "la dimensione dovrebbe essere 2 invece è: " + result.length);
        assertEquals(5, result[0].length, "la dimensione 0 dovrebbe essere 5 invece è: " + result[0].length);
        assertEquals(5, result[1].length, "la dimensione 1 dovrebbe essere 5 invece è: " + result[1].length);

        
        JobIndividual[] pool2 = new JobIndividual[12];
        for (int i = 0; i < 12; i++) {
            pool2[i] = new JobIndividual();
        }
        JobIndividual[][] result2 = GeneticManipulator.getInstance().prepareCrossover(pool2);

        assertEquals(2, result2.length, "la dimensione dovrebbe essere 2 invece è: " + result2.length);
        assertEquals(6, result2[0].length, "la dimensione 0 dovrebbe essere 6 invece è: " + result2[0].length);
        assertEquals(6, result2[1].length, "la dimensione 1 dovrebbe essere 6 invece è: " + result2[1].length);

//        try {
//            Settings.getInstance().setElectedPercentage(0);
//            Population pop = new Population(100);
//            pop.prepareCrossover();
//            int electedIndex = pop.getElectedIndex();
//            assertEquals(-1, electedIndex, "errore nel calcolo dell'elected index con % a 0");
//            assertEquals(pop.getBoys().length, pop.getGirls().length, "gli array boys & girls non sono della stessa dimensione");
//        } catch (InvalidSettingsException ex) {
//            assertTrue(false, "Non dovrebbe lanciare eccezione con parametri tra 0 e 100");
//        }
//
//        try {
//            Settings.getInstance().setElectedPercentage(100);
//            Population pop = new Population(100);
//            pop.prepareCrossover();
//            int electedIndex = pop.getElectedIndex();
//            assertEquals(99, electedIndex, "errore nel calcolo dell'elected index con % a 100");
//            assertEquals(pop.getBoys().length, pop.getGirls().length, "gli array boys & girls non sono della stessa dimensione");
//        } catch (InvalidSettingsException ex) {
//            assertTrue(false, "Non dovrebbe lanciare eccezione con parametri tra 0 e 100");
//        }
//
//        try {
//            Settings.getInstance().setElectedPercentage(1);
//            Population pop = new Population(100);
//            pop.prepareCrossover();
//            int electedIndex = pop.getElectedIndex();
//            assertEquals(0, electedIndex, "errore nel calcolo dell'elected index con % a 1");
//            assertEquals(pop.getBoys().length, pop.getGirls().length, "gli array boys & girls non sono della stessa dimensione");
//        } catch (InvalidSettingsException ex) {
//            assertTrue(false, "Non dovrebbe lanciare eccezione con parametri tra 0 e 100");
//        }
//
//        try {
//            Settings.getInstance().setElectedPercentage(15);
//            Population pop = new Population(100);
//            pop.prepareCrossover();
//            int electedIndex = pop.getElectedIndex();
//            assertEquals(14, electedIndex, "errore nel calcolo dell'elected index con % a 15");
//            assertEquals(pop.getBoys().length, pop.getGirls().length, "gli array boys & girls non sono della stessa dimensione");
//        } catch (InvalidSettingsException ex) {
//            assertTrue(false, "Non dovrebbe lanciare eccezione con parametri tra 0 e 100");
//        }
    }

}
