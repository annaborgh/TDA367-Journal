package src.MVC;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import src.Data.*;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the Persistance Class
 */
public class PersistenceTest {

    Model model = new Model();
    Persistence persistence = new Persistence();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Method to test the savePosts method in the Persistance class without exception
     */
    @Test
    public void testSavePostsWithoutException(){

        // Creating a post to save
        ArrayList<ITag> testListTags = new ArrayList<>();
        ITag testTag = new Tag("testTag", model.getAllTags().size()+1);
        testListTags.add(testTag);

        ArrayList<IMood> testListMoods = new ArrayList<>();
        IMood testMood = new Mood();
        testListMoods.add(testMood);

        ArrayList<ECondition> testListEConditions = new ArrayList<>();
        testListEConditions.add(ECondition.HOT);

        model.makePost("TestPost123", 0, testListTags, testListMoods, testListEConditions);

        // Saving the post
        persistence.savePosts(model.getPosts());

    }

    /**
     * Method to test the savePosts method in the Persistance class with exception
     * @throws IOException  Exception is thrown if directory doesn't exist
     */
    @Test (expected = IOException.class)
    public void testSavePostsWithException() throws IOException {
        deleteDirectory();
        persistence.savePosts(model.getPosts());

    }

    /**
     * Method to test the loadPost method in the Persistance Class with exception
     * @throws IOException  Exception is thrown if directory doesn't exist
     */
    @Test (expected = IOException.class)
    public void testLoadPostsWithException() throws IOException {
        deleteDirectory();
        persistence.loadPosts();
    }

    /**
     * Method to test the loadPost method in the Persistance Class without exception
     */
    @Test
    public void testLoadPostsWithoutException(){
        persistence.loadPosts();
    }

    File file = new File(Path.of(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal").toUri());
    /**
     * Method to delete the save directory of the project if it exists
     * @throws IOException  Exception is thrown if the directory to be deleted doesn't exist
     */
    private void deleteDirectory() throws IOException {
        if (Files.isDirectory(Path.of(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal"))){
            deleteFolder(file);
        } else{
            Files.deleteIfExists(Path.of(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal"));
        }
    }

    public void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }
}