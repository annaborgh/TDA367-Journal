package src.MVC;

import src.Data.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model {
    private final List<ITag> allTags = new ArrayList<>();
    private ILock lockType;
    private HashMap<LocalDate, IDay> posts = new HashMap<>();
    private final LocalDate currentDate;
    private final IPersistence persistence;

    public Model() {
        currentDate = LocalDate.now();
        persistence = new Persistence();
        init();
    }

    private void init(){
        //load posts
        posts = persistence.loadPosts();
    }

    protected void shutdown(){
        //save posts
        persistence.savePosts(posts);
        posts.clear();
    }

    public void makePost(String text, int grade, List<ITag> tags, ArrayList<IMood> moods, ArrayList<ECondition> EConditions){
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

    public List<ITag> getAllTags() {
        return allTags;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public HashMap<LocalDate, IDay> getPosts() {
        return posts;
    }

}
