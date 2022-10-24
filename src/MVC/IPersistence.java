package src.MVC;

import src.Data.IDay;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Interface for Persistence class.
 *
 * @author Anna Borgh
 */
public interface IPersistence {
    void savePosts(HashMap<LocalDate, IDay> posts);
    HashMap<LocalDate, IDay> loadPosts();
}
