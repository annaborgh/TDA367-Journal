package src.Data;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

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