package src.Data;

/**
 * @author Adam Wikström
 * @author Julia Ekeblad
 *
 * Interface for Mood classes.
 */
public interface IMood {
    String getMoodName();
    int getMoodRating();
    void setMoodRating(int newMood);
    void setName(String name);

}
