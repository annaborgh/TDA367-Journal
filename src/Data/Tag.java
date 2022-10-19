package src.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam
 * @author Julia
 *
 * Class that creates Tag objects
 */
public class Tag implements ITag{

    String tagTitle = "";
    int tagID = 0;
    /**
     * Constructor of Tag
     *
     * @param tagTitle      The title of the tag
     * @param tagID          A List of existing tags
     */
    public Tag(String tagTitle, int tagID){
        this.tagTitle = tagTitle;
        this.tagID = tagID;
    }

    /**
     * @author Adam
     *
     * Getter of the tag title
     *
     * @return  A String containing the Tag title
     */
    @Override
    public String getTitle() {
        return this.tagTitle;
    }

    /**
     * @author Adam
     *
     * Getter of the tagID
     *
     * @return  An int of the tagID
     */
    @Override
    public int getTagID() {
        return this.tagID;
    }

    /**
     * @author Adam
     *
     * Setter of the tagID
     *
     * @param tagID     The new tagID
     */
    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    /**
     * @author Adam
     *
     * Setter of the tagTitle
     *
     * @param tagTitle      The new tagTitle
     */
    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }
}
