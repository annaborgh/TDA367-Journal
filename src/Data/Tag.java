package src.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that creates Tag objects.
 *
 * @author Adam Wikström
 * @author Julia Ekeblad
 */
public class Tag implements ITag{

    String tagTitle = "";
    int tagID = 0;

    /**
     * Constructor of Tag.
     *
     * @param tagTitle  A String which contains the title of the tag.
     * @param tagID A List of existing tags.
     *
     * @author Adam Wikström
     * @author Julia Ekeblad
     */
    public Tag(String tagTitle, int tagID){
        this.tagTitle = tagTitle;
        this.tagID = tagID;
    }

    /**
     * Getter of the tag title.
     *
     * @return A String containing the tag title.
     *
     * @author Adam Wikström
     * @author Julia Ekeblad
     */
    @Override
    public String getTitle() {
        return this.tagTitle;
    }

    /**
     * Getter of the tagID.
     *
     * @return An int which contains the tagID.
     *
     * @author Adam Wikström
     * @author Julia Ekeblad
     */
    @Override
    public int getTagID() {
        return this.tagID;
    }

    /**
     * A method to set the tagID.
     *
     * @param tagID The new tagID.
     *
     * @author Adam Wikström
     * @author Julia Ekeblad
     */
    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    /**
     * A method to set the tagTitle.
     *
     * @param tagTitle  A String which contains the new tagTitle.
     *
     * @author Adam Wikström
     * @author Julia Ekeblad
     */
    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }
}
