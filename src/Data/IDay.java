package src.Data;


import java.time.LocalDate;
import java.util.ArrayList;

public interface IDay {

    String getText();

    int getGrade();

    ArrayList<IMood> getActiveMoods();

    ArrayList<ITag> getTags();

    void addActiveMood(IMood mood);

    void removeActiveMood(IMood mood);

}
