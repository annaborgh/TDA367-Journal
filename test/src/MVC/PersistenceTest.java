package src.MVC;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import src.Data.*;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the Persistance Class
 */
public class PersistenceTest {

    private Model model = new Model();
    private Persistence persistence = new Persistence();
    private HashMap<LocalDate, IDay> posts = new HashMap<>();


    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Method to test the loadPost method in the Persistance Class
     */
    @Test
    public void testLoadPosts(){
        persistence.loadPosts();
    }

    /**
     * Method to test the savePosts method in the Persistance class
     */
    @Test
    public void testSavePosts() throws IOException {

        deleteDirectory();
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
        persistence.savePosts(model.getPosts());
    }



    /**
     * Method to delete the save directory of the project if it exists
     * @throws IOException  Exception is thrown if the directory to be deleted doesn't exist
     */
    private void deleteDirectory() throws IOException {

        File directory = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal");
        File[] templist = directory.listFiles();
        if(templist.length == 1){
            for (int i = 0; i < templist[0].listFiles().length; i++){
                templist[0].listFiles()[i].delete();
            }

        }

        Files.delete(Path.of(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal" + File.separatorChar + "Posts"));
        Files.delete(Path.of(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal"));

    }


}