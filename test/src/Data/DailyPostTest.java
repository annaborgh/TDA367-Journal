package src.Data;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DailyPostTest {

    DailyPost post = new DailyPost();

    @Test
    public void testDate() {

        // Testing that the date assigned to a post is correct
        post.setDate(LocalDate.now());
        assertEquals(post.getDate(), LocalDate.now());

        // Testing so that a difference between current date and post's date can be noticed
        post.setDate(post.getDate().minusDays(2));
        assertNotEquals(post.getDate(), LocalDate.now());
    }

    @Test
    public void testText(){

        // Testing so that the default text is an empty String
        assertEquals("", post.getText());

        // Testing the setText method
        String tempText = "This is some new text\t\t\t\nHELEOOEOEO!!2";
        post.setText(tempText);
        assertEquals(tempText, post.getText());

    }

    @Test
    public void testGrade(){

        // Testing so that the default grade is 0
        assertEquals(0, post.getGrade());

        // Testing the setGrade method
        post.setGrade(4);
        assertEquals(4, post.getGrade());
    }

    @Test
    public void testActiveMoods(){

        // Testing so that the default activesMoods list is empty
        assertTrue(post.getActiveMoods().size() == 0);

        // Testing the setActiveMoods method
        IMood tempMood = new Mood("Happy");
        List<IMood> tempMoodList = new ArrayList<>();
        tempMoodList.add(tempMood);
        post.setActiveMoods(tempMoodList);

        assertEquals(tempMoodList, post.getActiveMoods());
    }

    @Test
    public void testTags(){

        // Testing so the default list of tags on a post is empty
        assertTrue(post.getTags().size() == 0);

        // Testing so that it is possible to add tags
        List<ITag> listOfTags = new ArrayList<ITag>();
        ITag tempTag = new Tag("name", post.getTags());
        listOfTags.add(tempTag);
        post.setTags(listOfTags);
        assertTrue(post.getTags().size() == 1);

        // Testing createTag method
        ITag testCreateTag = post.createTag("ThisTag");
        assertEquals("ThisTag", testCreateTag.getTitle());

        // Testing addTag method
        post.addTag(testCreateTag);
        assertEquals(testCreateTag, post.getTags().get(1));
        int tagsListSize = post.getTags().size();

        post.addTag(testCreateTag);
        assertEquals(tagsListSize, post.getTags().size());

        // Testing removeTag method
        assertEquals(testCreateTag, post.getTags().get(1));
        post.removeTag(testCreateTag);
        assertFalse(post.getTags().contains(testCreateTag));

    }

    @Test
    public void testConditions(){

        // Testing so that the default list of conditions is empty
        assertTrue(post.getConditions().size() == 0);

        // Testing setConditions method
        List<ECondition> tempConditions = new ArrayList<>();
        tempConditions = Arrays.asList(ECondition.values());
        post.setConditions(tempConditions);

        assertEquals(tempConditions, post.getConditions());
    }


}