package src.MVC;

import src.Data.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    private final ArrayList<IMood> allMoods = new ArrayList<>();
    private ArrayList<ITag> allTags = new ArrayList<>();
    private ILock lockType;
    private HashMap<LocalDate, IDay> posts;
    private final LocalDate currentDate;
    public Model() {
        // load posts from saved files
        currentDate = LocalDate.now();
    }

    public void makePost(ArrayList<ITag> tags){
        IDay post = new DailyPost(currentDate);
        // add text
        // add tags
        // add active moods
        addPost(post.getDate(), post);
    }

    private void addPost(LocalDate date, IDay newPost){
        posts.put(date, newPost);
    }

    public ILock getLockType() {
        return lockType;
    }

    public HashMap<LocalDate, IDay> getPosts() {
        return posts;
    }
}
