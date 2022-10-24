package src.Data;

/**
 * Interface for Tag objects.
 *
 * @author Adam Wikström
 * @author Julia Ekeblad
 */
public interface ITag {

    String getTitle();

    int getTagID();

    void setTagID(int i);

    void setTagTitle(String tagTitle);
}
