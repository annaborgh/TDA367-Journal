package src.Data;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the EMood
 */
public class EMoodTest {

    /**
     * Method to test EMood
     */
    @Test
    public void testEMood(){
       assertEquals("MISCONTENTTOCONTENT", EMood.MISCONTENTTOCONTENT.toString());
    }
}