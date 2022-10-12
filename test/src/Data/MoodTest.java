package src.Data;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the Mood Class
 */
public class MoodTest {

    IMood testMood = new Mood("Happy");

    /**
     * Method to test the getters of the Mood class
     */
    @Test
    public void testMoodGetters() {

        // Testing that a Mood gets its assigned name at creation correctly
        assertTrue("Happy" == testMood.getMoodName());

        // Testing that the default mood rating is -1
        assertTrue(-1 == testMood.getMoodRating());
    }

    /**
     * Method to test the changeMoodRating method in Mood class
     */
    @Test
    public void testChangeMoodRating(){

        // Testing that changing the initial moodRating to a value in range [0, 100] works
        testMood.changeMoodRating(50);
        assertEquals(50, testMood.getMoodRating());

        // Testing that attempting to change the moodRating to a value outside the range [0, 100] doesn't work
        testMood.changeMoodRating(101);
        assertEquals(50, testMood.getMoodRating());

        testMood.changeMoodRating(-1);
        assertEquals(50, testMood.getMoodRating());
    }


}