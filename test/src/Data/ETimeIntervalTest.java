package Data;

import org.junit.Test;
import src.Data.ETimeInterval;

import static org.junit.Assert.assertEquals;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the ETimeInterval
 */
public class ETimeIntervalTest {

    /**
     * Method to test ETimeInterval
     */
    @Test
    public void testETime(){
        assertEquals("YEAR", ETimeInterval.YEAR.toString());
    }

}