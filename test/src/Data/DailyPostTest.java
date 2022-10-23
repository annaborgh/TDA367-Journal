package test.src.Data;

import org.junit.Test;
import src.Data.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Adam Wikström
 *
 * Class for JUnit tests of the DailyPost Class
 */
public class DailyPostTest {

    DailyPost post = new DailyPost();

    /**
     * Method to test all methods in DailyPost handling the date variable
     */
    @Test
    public void testDate() {

        // Testing that the date assigned to a post is correct
        post.setDate(LocalDate.now());
        assertEquals(post.getDate(), LocalDate.now());


        // Testing so that a difference between current date and post's date can be noticed
        post.setDate(post.getDate().minusDays(2));
        assertNotEquals(post.getDate(), LocalDate.now());
    }

    /**
     * Method to test all methods in DailyPost handling the Text variable
     */
    @Test
    public void testText(){

        // Testing so that the default text is an empty String
        assertEquals("", post.getText());


        // Testing the setText method
        String tempText = "This is some new text\t\t\t\nHELEOOEOEO!!2";
        post.setText(tempText);
        assertEquals(tempText, post.getText());

    }

    /**
     * Method to test all methods in DailyPost handling the Grade variable
     */
    @Test
    public void testGrade(){

        // Testing so that the default grade is 0
        assertEquals(0, post.getGrade());


        // Testing the setGrade method
        post.setGrade(4);
        assertEquals(4, post.getGrade());
    }

    /**
     * Method to test all methods in DailyPost handling the ActiveMoods list
     */
    @Test
    public void testActiveMoods(){

        // Testing so that the default activesMoods list is empty
        assertTrue(post.getActiveMoods().size() == 0);


        // Testing the setActiveMoods method
        IMood tempMood = new Mood();
        tempMood.setName("Happy");
        List<IMood> tempMoodList = new ArrayList<>();
        tempMoodList.add(tempMood);
        post.setActiveMoods(tempMoodList);

        assertEquals(tempMoodList, post.getActiveMoods());

        // Testing the add- and removeActiveMood methods
        IMood tempMood2 = new Mood();
        tempMood2.setName("TestName2");

        post.addActiveMood(tempMood2);
        assertTrue(post.getActiveMoods().contains(tempMood2));

        post.removeActiveMood(tempMood2);
        assertFalse(post.getActiveMoods().contains(tempMood2));
    }

    /**
     * Method to test all methods in DailyPost handling the Tags list
     */
    @Test
    public void testTags(){

        // Testing so the default list of tags on a post is empty
        assertTrue(post.getTags().size() == 0);


        // Testing so that it is possible to add tags
        ITag tempTag = new Tag("name", post.getTags().size()+1);
        post.addTag(tempTag);
        ITag tempTag2 = new Tag("name2", post.getTags().size()+1);
        post.addTag(tempTag2);
        assertEquals(2, post.getTags().size());


        // Testing createTag method
        ITag testCreateTag = post.createTag("ThisTag");
        assertEquals("ThisTag", testCreateTag.getTitle());


        // Testing addTag method
        post.addTag(testCreateTag);
        assertTrue(post.getTags().contains(testCreateTag));

        ITag addTagTestTag = new Tag("addTagTestTag", post.getTags().size()+1);
        post.addTag(addTagTestTag);

        // Testing removeTag method
        assertEquals(testCreateTag, post.getTags().get(2));
        post.removeTag(testCreateTag);
        assertFalse(post.getTags().contains(testCreateTag));

        // Testing setTags method
        List<ITag> tempListOfTags = new ArrayList<>();
        for (int i = 1; i <= 5; i++){
            ITag tempTag3 = new Tag("Tag "+i, post.getTags().size()+i);
            tempListOfTags.add(tempTag3);
        }
        post.setTags(tempListOfTags);
        assertEquals(8, post.getTags().size());

    }

    /**
     * Method to test all methods in DailyPost handling the Conditions list
     */
    @Test
    public void testConditions(){

        // Testing so that the default list of conditions is empty
        assertEquals(0, post.getConditions().size());


        // Testing addCondition method
        post.addCondition(ECondition.HOT);
        assertEquals(1, post.getConditions().size());

        // Testing setCondition method
        List<ECondition> tempListOfConditions = new ArrayList<>();

        for (int i = 0; i < ECondition.values().length; i++){
            if (!post.getConditions().contains(ECondition.values()[i])){
                tempListOfConditions.add(ECondition.values()[i]);
            }
        }

        post.setConditions(tempListOfConditions);

        assertTrue(ECondition.values().length == post.getConditions().size());


    }


}