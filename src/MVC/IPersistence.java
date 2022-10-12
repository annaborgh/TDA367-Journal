package src.MVC;

import src.Data.IDay;

import java.time.LocalDate;
import java.util.HashMap;

public interface IPersistence {
    void savePosts(HashMap<LocalDate, IDay> posts);
    HashMap<LocalDate, IDay> loadPosts();
}
