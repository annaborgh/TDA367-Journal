package src.Data;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the Mood Class
 */
public class MoodTest {

    IMood testMood = new Mood();


    /**
     * Method to test the getters of the Mood class
     */
    @Test
    public void testMoodGetters() {
        testMood.setName("Happy");

        // Testing that a Mood gets its assigned name at creation correctly
        assertTrue("Happy" == testMood.getMoodName());

        // Testing that the default mood rating is -1
        assertTrue(-1 == testMood.getMoodRating());
    }

}