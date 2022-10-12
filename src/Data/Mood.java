package src.Data;

/**
 * Class that creates objects of type Mood
 */
public class Mood implements IMood {

    int moodRating = -1;
    int moodLimitUpper = 100;
    int moodLimitLower = 0;
    String moodName = "PlaceholderName";

    /**
     * Constructor of Mood
     *
     * @param moodName      The name of the new Mood
     */
    public Mood(String moodName) {
        this.moodName = moodName;
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

    /**
     * Method to change moodRating value. Contains logic for preventing illegal values
     *
     * @param newRating        The new moodRating
     */
    @Override
    public void changeMoodRating(int newRating){
        if (newRating <= this.moodLimitUpper && newRating >= moodLimitLower){
            this.moodRating = newRating;
        }

    }
}
