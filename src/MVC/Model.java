package src.MVC;

import src.Data.DailyPost;
import src.Data.IDay;
import src.Data.ILock;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Model {
    private ILock lockType;
    private boolean hasLock;
    private HashMap<Date, IDay> posts;

    private final Date currentDayMonthYear;
    public Model() throws ParseException {
        // get posts from saved files
        currentDayMonthYear = setDayMonthYear();
    }

    public void createPost(){
        IDay post = new DailyPost(currentDayMonthYear);
        posts.put(post.getDate(), post);
    }

    private Date setDayMonthYear() throws ParseException {
        // currently only updates when the model is created (i.e. when the programme starts).
        // should probably reset at 12am each day instead but cba
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();
        return format.parse(format.format(now));
    }

    private void addPost(Date currentDate, IDay newPost){
        posts.put(currentDate, newPost);
    }

    public ILock getLockType() {
        return lockType;
    }

    public boolean isHasLock() {
        return hasLock;
    }

    public HashMap<Date, IDay> getPosts() {
        return posts;
    }
}
