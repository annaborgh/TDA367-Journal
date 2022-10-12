package src;
import src.Data.*;
import src.MVC.*;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args){

        //Model model = new Model();

        // Testing logic for the lock

        /*

        System.out.println(model.getLock());

        model.createPinLock("1234");
        System.out.println(model.getLock());

        System.out.println("If lockstate is true then lock is locked, othewise unlocked");
        System.out.println("Lockstate is: " + model.getLockState());
        model.unlockLock("hej");
        System.out.println("Lockstate is: " + model.getLockState());

        model.unlockLock("1234");
        System.out.println("Lockstate is: " + model.getLockState());

        */

        /* ~~Testing Controller~~ */

        /*Controller controller = new Controller();
        Model model = controller.getModel();

        Tag tag = new Tag("test", model.getAllTags());
        tag.setTagID(42);

        ArrayList<ITag> tags = new ArrayList<>();
        tags.add(tag);

        IMood mood = new Mood("testing mood");

        ArrayList<IMood> moods = new ArrayList<>();
        moods.add(mood);

        ArrayList<ECondition> conditions = new ArrayList<>();
        conditions.add(ECondition.SICK);

        model.makePost("Hej detta är ett test", 4, tags, moods, conditions);

        model.savePosts();*/

        /*
        *    Testing of DailyPost's methods
        */

        /*IDay day = new DailyPost();

        ITag skola = day.createTag("skola");
        ITag träning = day.createTag("träning");
        ITag jobb = day.createTag("jobb");

        System.out.println(day.getTags());

        day.addTag(skola);
        System.out.println(day.getTags());


        for(int i = 0; i < 4; i++){
            if (i == 3){
                day.addTag(träning);
            }
            else{
                day.addTag(jobb);
            }
        }

        System.out.println(day.getTags());

        day.removeTag(skola);
        System.out.println(day.getTags());

        day.removeTag(träning);
        System.out.println(day.getTags());

        /*
        * Testing of Mood and IMood
        * */

        /*

        IMood anger = new Mood("Anger");

        System.out.println("Initial mood name\t\t" + anger.getMoodName());
        System.out.println("Initial mood rating\t\t" + anger.getMoodRating());

        anger.changeMoodRating(50);

        System.out.println("Changed mood rating\t\t" + anger.getMoodRating());

        // Attempting to change moodRating to a value outside of the valid range

        anger.changeMoodRating(10000);
        anger.changeMoodRating(-90);

        // Attempting to change moodRating to the lowest as well as the highest allowed value

        anger.changeMoodRating(0);
        System.out.println("Changed mood rating\t\t" + anger.getMoodRating());

        anger.changeMoodRating(100);
        System.out.println("Changed mood rating\t\t" + anger.getMoodRating());

        */



    }
}
