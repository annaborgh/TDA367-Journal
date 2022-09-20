package src.Data;


import java.util.ArrayList;

public interface IDay {

    String getDate();

    String getText();

    int getGrade();

    ArrayList<IMood> getAllMoods();

    ArrayList<IMood> getActiveMoods();

    ArrayList<ITag> getTags();

    void addActiveMood(IMood mood);

    void removeActiveMood(IMood mood);

    void addTag(ITag str);

    void removeTag(ITag tag);

}
