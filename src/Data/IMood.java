package src.Data;

/**
 * Interface for Mood classes.
 *
 * @author Adam Wikström
 * @author Julia Ekeblad
 */
public interface IMood {
    String getMoodName();
    int getMoodRating();
    void setMoodRating(int newMood);
    void setName(String name);

}
