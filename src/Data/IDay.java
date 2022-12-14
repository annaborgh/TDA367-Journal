package src.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface for DailyPost objects.
 *
 * @author Adam Wikström
 * @author Julia Ekeblad
 */
public interface IDay {

    LocalDate getDate();

    String getText();

    int getGrade();

    List<IMood> getActiveMoods();

    List<ITag> getTags();

    void addActiveMood(IMood mood);

    void removeActiveMood(IMood mood);

    ITag createTag(String tagName);

    void addTag(ITag str);

    void removeTag(ITag tag);

    void setText(String text);

    void setDate(LocalDate date);

    void setGrade(int grade);

    void setTags(List<ITag> tags);

    void setActiveMoods(List<IMood> moods);

    void setConditions(List<ECondition> conditions);

    List<ECondition> getConditions();
}
