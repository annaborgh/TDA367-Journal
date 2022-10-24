package Data;

import org.junit.Test;
import src.Data.ETimeInterval;

import static org.junit.Assert.assertEquals;

/**
 * Class for JUnit tests of the ETimeInterval.
 *
 * @author Adam Wikström
 */
public class ETimeIntervalTest {

    /**
     * Method to test ETimeInterval.
     */
    @Test
    public void testETime(){
        assertEquals("YEAR", ETimeInterval.YEAR.toString());
    }

}