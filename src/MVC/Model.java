package src.MVC;

import src.Data.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    private ArrayList<ITag> allTags = new ArrayList<>();
    private ILock lockType;
    private HashMap<LocalDate, IDay> posts;
    private final LocalDate currentDate;
    public Model() {
        // load posts from saved files
        currentDate = LocalDate.now();
    }

    public void makePost(String text, int grade, ArrayList<ITag> tags, ArrayList<IMood> moods, ArrayList<ECondition> EConditions){
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
