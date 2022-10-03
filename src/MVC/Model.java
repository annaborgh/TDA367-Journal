package src.MVC;
import src.Data.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model {
    private ArrayList<ITag> allTags = new ArrayList<>();
    private ILock lockType;
    private HashMap<LocalDate, IDay> posts;
    public final LocalDate currentDate;
    public Model() {
        currentDate = LocalDate.now();
    }

    public void makePost(String text, int grade, List<ITag> tags, List<IMood> moods, List<ECondition> EConditions){
        IDay post = new DailyPost();

        post.setDate(currentDate);
        post.setText(text);
        post.setGrade(grade);
        post.setTags(tags);
        post.setActiveMoods(moods);
        post.setConditions(EConditions);

        posts.put(currentDate, post);
    }

    public ILock getLockType() {
        return lockType;
    }

    public HashMap<LocalDate, IDay> getPosts() {
        return posts;
    }
}
