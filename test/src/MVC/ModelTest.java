package src.MVC;

import org.junit.Test;
import src.Data.ECondition;
import src.Data.IMood;
import src.Data.ITag;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the Model Class
 */
public class ModelTest {

    /**
     * Method to test the Constructor of the Model Class
     */
    @Test
    public void testConstructorAndInit(){

        // Testing the Constructor method (called Model)
        Model model = new Model();
        assertSame(Model.class, model.getClass());
    }

    // Model for testing purposes
    Model model = new Model();

    /**
     * Method to test the shutdown method of the Model Class
     */
    @Test
    public void testShutdown(){
        model.shutdown();
        assertEquals(0, model.getPosts().size());
    }

    /**
     * Method to test the makePost method of the Model Class
     */
    @Test
    public void testMakePost(){
        ArrayList<ITag> testListTags = new ArrayList<>();
        ArrayList<IMood> testListMoods = new ArrayList<>();
        ArrayList<ECondition> testListEConditions = new ArrayList<>();
        model.makePost(LocalDate.now(), "TestPost123", 0, testListTags, testListMoods, testListEConditions);
        assertTrue(model.getPosts().containsKey(LocalDate.now()));
    }

    /**
     * Method to test the getters of the Model Class
     */
    @Test
    public void testGetters(){

        // Testing the Lock getters
        assertNull(model.getLockType());
        assertNull(model.getLock());
        assertTrue(model.getLockState());

        // Testing the Tag getters
        assertEquals(0, model.getAllTags().size());

        // Testing the Date getters
        assertEquals(LocalDate.now(), model.getCurrentDate());

        // Testing the Post getters
        ArrayList<ITag> testListTags = new ArrayList<>();
        ArrayList<IMood> testListMoods = new ArrayList<>();
        ArrayList<ECondition> testListEConditions = new ArrayList<>();
        model.makePost(LocalDate.now(), "TestPost123", 0, testListTags, testListMoods, testListEConditions);

        assertTrue(model.getPosts().containsKey(LocalDate.now()));

    }

    /**
     * Method to test the createPinLock method of the Model Class
     */
    @Test
    public void testCreatePinLock(){
        // Attempting to create a lock with invalid code
        model.createPinLock("aaaa");
        assertTrue(model.getLock() == null);

        // Attempting to create a lock with valid code
        model.createPinLock("1234");
        assertTrue(model.getLock() != null);

    }

    /**
     * Method to test the unlockLock method of the Model Class
     */
    @Test
    public void testUnlockLock(){
        model.createPinLock("1234");
        model.unlockLock("1234");
        assertFalse(model.getLockState());
    }

    /**
     * Method to test the lockLock method of the Model Class
     */
    @Test
    public void testLockLock(){
        model.createPinLock("1234");
        model.unlockLock("1234");
        model.lockLock();
        assertTrue(model.getLockState());
    }

    /**
     * Method to delete the save directory of the project if it exists
     * @throws IOException  Exception is thrown if the directory to be deleted doesn't exist
     */
    private void deleteDirectory() throws IOException {
        Files.deleteIfExists(Path.of(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal"));
    }
}