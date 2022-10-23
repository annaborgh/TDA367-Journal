package Data;

import org.junit.Test;
import src.Data.ITag;
import src.Data.Tag;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the Tag Class
 */
public class TagTest {

    List<ITag> testTagList = new ArrayList<>();

    /**
     * Method to test the getters of the Tag class
     */
    @Test
    public void testTagGetters() {

        ITag testTag1 = new Tag("Skola", testTagList.size()+1);
        testTagList.add(testTag1);

        ITag testTag2 = new Tag("Fritid", testTagList.size()+1);
        testTagList.add(testTag2);

        // Test that the tag gets it's assigned title upon creation
        assertEquals("Skola", testTag1.getTitle());
        assertEquals("Fritid", testTag2.getTitle());

        // Test that the tag gets the correct ID at creation
        /*
        *   This test only works if every tag is added to the list of tags
        *   when they get created.
        * */
        assertEquals(1, testTag1.getTagID());
        assertEquals(2, testTag2.getTagID());

    }

    /**
     * Method to tst the setters of the Tag class
     */
    @Test
    public void testTagSetters(){

        ITag testTag1 = new Tag("Skola", testTagList.size()+1);
        testTagList.add(testTag1);

        ITag testTag2 = new Tag("Fritid", testTagList.size()+1);
        testTagList.add(testTag2);

        // Test that the tagID changes correctly when setter is called
        testTag1.setTagID(45);
        testTag2.setTagID(30);

        assertEquals(45, testTag1.getTagID());
        assertEquals(30, testTag2.getTagID());


        // Test that the tagTitle changes correctly when setter is called
        testTag1.setTagTitle("NotSkola");
        testTag2.setTagTitle("NotFritid");

        assertEquals("NotSkola", testTag1.getTitle());
        assertEquals("NotFritid", testTag2.getTitle());

    }
}