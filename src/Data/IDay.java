package src.Data;


import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IDay {

    LocalDate getDate();

    String getText();

    int getGrade();

    ArrayList<IMood> getActiveMoods();

    ArrayList<ITag> getTags();

    void addActiveMood(IMood mood);

    void removeActiveMood(IMood mood);

    ITag createTag(String tagName);

    void addTag(ITag str);

    void removeTag(ITag tag);

    void setText(String text);

    void setDate(LocalDate date);

    void setGrade(int grade);

    void setTags(ArrayList<ITag> tags);

    void setActiveMoods(ArrayList<IMood> moods);

    void setConditions(ArrayList<ECondition> conditions);

    ArrayList<ECondition> getConditions();

    void addMood(IMood mood);
    void addCondition(ECondition condition);
}
