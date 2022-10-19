package src.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Wikström
 * @author Julia Ekeblad
 *
 * Class that creates Tag objects.
 */
public class Tag implements ITag{

    String tagTitle = "";
    int tagID = 0;

    /**
     * @author Adam Wikström
     *
     * Constructor of Tag.
     *
     * @param tagTitle  A String which contains the title of the tag.
     * @param tagID     A List of existing tags.
     */
    public Tag(String tagTitle, int tagID){
        this.tagTitle = tagTitle;
        this.tagID = tagID;
    }

    /**
     * @author Adam Wikström
     *
     * Getter of the tag title.
     *
     * @return  A String containing the tag title.
     */
    @Override
    public String getTitle() {
        return this.tagTitle;
    }

    /**
     * @author Adam Wikström
     *
     * Getter of the tagID.
     *
     * @return  An int which contains the tagID.
     */
    @Override
    public int getTagID() {
        return this.tagID;
    }

    /**
     * @author Adam Wikström
     *
     * A method to set the tagID.
     *
     * @param tagID The new tagID.
     */
    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    /**
     * @author Adam Wikström
     *
     * A method to set the tagTitle.
     *
     * @param tagTitle  A String which contains the new tagTitle.
     */
    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }
}
