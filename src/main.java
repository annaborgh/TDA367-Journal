package src;
import src.Data.*;
import src.MVC.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Adam Wikström
 * @author Anna Borg
 * @author Julia Ekeblad
 * @author Wilma Nordlund
 *
 * Main class for running the program.
 */
public class main {

    public static void main(String[] args){

        /**
         * @author Adam Wikström
         * @author Wilma Nordlund
         */

        /*
        Model model = new Model();

        // ~~Testing logic for the lock~~

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


        /**
         * @author Anna Borgh
         */

        // ~~Testing save & load~~

        /*

        Controller controller = new Controller();

        model = controller.getModel();

        //save
        Tag tag = new Tag("test", model.getAllTags().size()+1);

        ArrayList<ITag> tags = new ArrayList<>();
        tags.add(tag);

        IMood mood = new Mood();
        mood.setName("testingMood");
        mood.setMoodRating(42);
        ArrayList<IMood> moods = new ArrayList<>();
        moods.add(mood);
        ArrayList<ECondition> conditions = new ArrayList<>();
        conditions.add(ECondition.SICK);
        model.makePost("Hej detta är ett test" + "\n\n" + "fungerar det med dubbla radbyten också?", 4, tags, moods, conditions);
        controller.shutdown();

        //load
        model = new Model();
        HashMap<LocalDate, IDay> posts = model.getPosts();
        IDay post = posts.get(LocalDate.now());

        System.out.println(post.getDate());
        System.out.println(post.getText());
        System.out.println(post.getTags());
        System.out.println(post.getConditions());

         */


        /**
         * @author TODO
         */

        // ~~Testing of DailyPost's methods~~

        /*

        IDay day = new DailyPost();

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

         */


        /**
         * @author TODO
         */

        // ~~Testing of Mood and IMood~~

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
