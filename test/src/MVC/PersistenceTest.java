package src.MVC;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.*;
import src.Data.*;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the Persistance Class
 */
public class PersistenceTest {

    private Model model = new Model();
    private Persistence persistence = new Persistence();
    private HashMap<LocalDate, IDay> posts = new HashMap<>();



    /**
     * Method to test the loadPost method in the Persistance Class
     */
    @Test
    public void testLoadPosts() throws IOException {
        Path pathToDelete = Path.of(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal");
        boolean res = deleteDirectory(pathToDelete.toFile());
        persistence.loadPosts();
    }

    /**
     * Method to test the savePosts method in the Persistance class
     */
    @Test
    public void testSavePosts() throws IOException {
        Path pathToDelete = Path.of(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal");

        boolean result = deleteDirectory(pathToDelete.toFile());

        assertTrue(result);
        // Creating a post to save
        ArrayList<ITag> testListTags = new ArrayList<>();
        ITag testTag = new Tag("testTag", model.getAllTags().size() + 1);
        testListTags.add(testTag);

        ArrayList<IMood> testListMoods = new ArrayList<>();
        IMood testMood = new Mood();
        testListMoods.add(testMood);

        ArrayList<ECondition> testListEConditions = new ArrayList<>();
        testListEConditions.add(ECondition.HOT);

        model.makePost(LocalDate.now(),"TestPost123", 0, testListTags, testListMoods, testListEConditions);

        // Saving the post
        result = deleteDirectory(pathToDelete.toFile());
        persistence.savePosts(model.getPosts());
        persistence.savePosts(model.getPosts());

    }


    /**
     * Method to delete the save directory of the project if it exists
     * @throws IOException  Exception is thrown if the directory to be deleted doesn't exist
     */
    private boolean deleteDirectory(File directoryToBeDeleted) throws IOException {

        File[] allContents = directoryToBeDeleted.listFiles();

        if(allContents != null){
            for (File file : allContents){
                deleteDirectory(file);
            }

        }
        return directoryToBeDeleted.delete();

    }


}