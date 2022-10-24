package src.Data;

/**
 * @author Adam Wikström
 * @author Anna Borgh
 * @author Tarek Chorfi
 */
public class Mood implements IMood {

    int moodRating = -1;
    int moodLimitUpper = 100;
    int moodLimitLower = 0;
    String moodName = "PlaceholderName";

    /**
     * Constructor of Mood.
     */
    public Mood() {
    }

    /**
     * A method to get the name and rating for a mood.
     *
     * @param name  A String.
     * @param rating    An int.
     */
    public Mood(String name, int rating){
        this.moodName = name;
        this.moodRating = rating;
    }

    /**
     * Getter for the name of the mood.
     *
     * @return A String which is the name of the mood.
     */
    @Override
    public String getMoodName() {
        return this.moodName;
    }

    /**
     * Getter for the rating of the mood.
     *
     * @return An int which is the rating of the mood.
     */
    @Override
    public int getMoodRating() {
        return moodRating;
    }

    /**
     * A setter for the mood rating.
     *
     * @param newRating An int.
     */
    @Override
    public void setMoodRating(int newRating){
        if (newRating <= this.moodLimitUpper && newRating >= moodLimitLower){
            this.moodRating = newRating;
        }
        // Temporary else statement for testing purposes.
        else {
            System.out.println("INVALID MOOD RATING! INT MUST BE IN RANGE [0, 100]");
            System.out.println("You attempted to change mood rating to " + newRating);
        }
    }

    /**
     * A setter for the mood name.
     *
     * @param name A String.
     */
    @Override
    public void setName(String name) {
        moodName = name;
    }
}
