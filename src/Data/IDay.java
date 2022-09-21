package src.Data;


import java.time.LocalDate;
import java.util.ArrayList;

public interface IDay {

    LocalDate getDate();

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
