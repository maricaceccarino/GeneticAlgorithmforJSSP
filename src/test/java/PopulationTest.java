
import it.unina.ceccarino.gaforjss.algo.Population;
import it.unina.ceccarino.gaforjss.exceptions.InvalidSettingsException;
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
                System.out.println("asdasd");
                Settings.getInstance().setElectedPercentage(-13);
            }
        });
        exceptionHasBeenThrownMinor = true;
        System.out.println("minchia "+exceptionHasBeenThrownMinor);
        assertThrows(InvalidSettingsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Settings.getInstance().setElectedPercentage(150);
            }
        });
        exceptionHasBeenThrownMajor = true;
        System.out.println("e major è "+exceptionHasBeenThrownMajor);
    }

    @Order(2)
    @Test
    public void testElectedPercentage() {
//
        System.out.println(exceptionHasBeenThrownMajor);
        System.out.println(exceptionHasBeenThrownMinor);
        Assumptions.assumeTrue(exceptionHasBeenThrownMajor);
        Assumptions.assumeTrue(exceptionHasBeenThrownMinor);

        try {
            Settings.getInstance().setElectedPercentage(0);
            Population pop = new Population(100);
            pop.mutate();
            int electedIndex = pop.getElectedIndex();
            assertEquals(-1, electedIndex, "errore nel calcolo dell'elected index con % a 0");
        } catch (InvalidSettingsException ex) {
            assertTrue(false, "Non dovrebbe lanciare eccezione con parametri tra 0 e 100");
        }

        try {
            Settings.getInstance().setElectedPercentage(100);
            Population pop = new Population(100);
            pop.mutate();
            int electedIndex = pop.getElectedIndex();
            assertEquals(99, electedIndex, "errore nel calcolo dell'elected index con % a 100");
        } catch (InvalidSettingsException ex) {
            assertTrue(false, "Non dovrebbe lanciare eccezione con parametri tra 0 e 100");
        }

        try {
            Settings.getInstance().setElectedPercentage(1);
            Population pop = new Population(100);
            pop.mutate();
            int electedIndex = pop.getElectedIndex();
            assertEquals(0, electedIndex, "errore nel calcolo dell'elected index con % a 1");
        } catch (InvalidSettingsException ex) {
            assertTrue(false, "Non dovrebbe lanciare eccezione con parametri tra 0 e 100");
        }

        try {
            Settings.getInstance().setElectedPercentage(15);
            Population pop = new Population(100);
            pop.mutate();
            int electedIndex = pop.getElectedIndex();
            assertEquals(14, electedIndex, "errore nel calcolo dell'elected index con % a 15");
        } catch (InvalidSettingsException ex) {
            assertTrue(false, "Non dovrebbe lanciare eccezione con parametri tra 0 e 100");
        }

    }

}
