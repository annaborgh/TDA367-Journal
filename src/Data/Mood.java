package src.Data;

/**
 * @author Adam
 *
 * Class that creates objects of type Mood
 */
public class Mood implements IMood {

    int moodRating = -1;
    int moodLimitUpper = 100;
    int moodLimitLower = 0;
    String moodName = "PlaceholderName";

    /**
     * Constructor of Mood
     */
    public Mood() {

    }

    /**
     * Getter method for the Mood's name
     *
     * @return A String containing the Mood's name
     */
    @Override
    public String getMoodName() {

        return this.moodName;
    }

    /**
     * Getter method for the Mood's current rating
     *
     * @return An int of the Mood's current rating
     */
    @Override
    public int getMoodRating() {
        return moodRating;
    }

    @Override
    public void setMoodRating(int newMood) {
        this.moodRating = newMood;
    }

    @Override
    public void setName(String name) {
        moodName = name;
    }
}
