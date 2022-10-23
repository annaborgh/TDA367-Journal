package src.Data;

/**
 * @author Adam Wikström
 *
 * Interface for Tag objects.
 */

public interface ITag {

    String getTitle();

    int getTagID();

    void setTagID(int i);

    void setTagTitle(String tagTitle);
}
