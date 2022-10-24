package Data;

import org.junit.Test;
import src.Data.EMood;

import static org.junit.Assert.assertEquals;

/**
 * Class for JUnit tests of the EMood.
 *
 * @author Adam Wikström
 */
public class EMoodTest {

    /**
     * Method to test EMood.
     */
    @Test
    public void testEMood(){
       assertEquals("MISCONTENTTOCONTENT",
               EMood.MISCONTENTTOCONTENT.toString());
    }
}