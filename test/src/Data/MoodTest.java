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
     *
     */
    //@Test


    /**
     * Method to test the setters of the Mood class
     */
    @Test
    public void testMoodSetters(){

        // Testing the setName method
        testMood.setName("Happy");
        assertEquals("Happy", testMood.getMoodName());

        // Testing the setMoodRating method
        testMood.setMoodRating(69);
        assertEquals(69, testMood.getMoodRating());

    }

    /**
     * Method to test the getters of the Mood class
     */
    @Test
    public void testMoodGetters() {

        // Assigning the mood a name
        testMood.setName("Happy");

        // Testing that a Mood has its assigned name
        assertEquals("Happy", testMood.getMoodName());

        // Testing that the default mood rating is -1
        assertEquals(-1, testMood.getMoodRating());
    }

}