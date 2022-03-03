/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Marica
 */
public class InputTest {

    public InputTest() {
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

    @Test
    public void testPermutationConsistency() {

        Map<Integer, Integer> map = InputManager.getInstance().getJobQuantityMap();
        JobIndividual individuo = InputManager.getInstance().generateJobIndividual();
        for (Integer jobType : map.keySet()) {
            Integer finalQuantity = map.get(jobType) * 6;
            int checked = 0;
            for (int type : individuo.getJobPermutation()) {
                if(type == jobType){
                    checked++;
                }
            }
            assertEquals(finalQuantity, checked, "mi spiace ma mi aspettavo "+finalQuantity+" di job["+jobType+"] e invece ce ne sono "+checked);

        }

    }

}
