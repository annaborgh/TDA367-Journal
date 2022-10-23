package src.Data;

public class Mood implements IMood {

    int moodRating = -1;
    int moodLimitUpper = 100;
    int moodLimitLower = 0;
    String moodName = "PlaceholderName";

    public Mood() {
    }

    public Mood(String name, int rating) {
        this.moodName =name;
        this.moodRating=rating;
    }

    @Override
    public String getMoodName() {
        return this.moodName;
    }

    @Override
    public int getMoodRating() {
        return moodRating;
    }

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

    @Override
    public void setName(String name) {
        moodName = name;
    }
}
