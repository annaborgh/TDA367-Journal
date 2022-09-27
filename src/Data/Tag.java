package src.Data;

import java.util.ArrayList;

public class Tag implements ITag{

    String tagTitle = "";
    int tagID = 0;

    public Tag(String tagTitle, ArrayList<ITag> tags){
        this.tagTitle = tagTitle;
        this.tagID = tags.size()+1;
    }

    @Override
    public String getTitle() {
        return this.tagTitle;
    }

    @Override
    public int getTagID() {
        return this.tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }
}