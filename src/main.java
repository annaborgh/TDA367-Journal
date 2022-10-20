package src;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.Data.*;
import src.MVC.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class main extends Application {

    public static void main(String[] args){

        Model model = new Model();

        // Testing logic for the lock


        /*System.out.println(model.getLock());
        model.createPinLock("1234");
        System.out.println(model.getLock());
        System.out.println("If lockstate is true then lock is locked, othewise unlocked");
        System.out.println("Lockstate is: " + model.getLockState());
        model.unlockLock("hej");
        System.out.println("Lockstate is: " + model.getLockState());
        model.unlockLock("1234");
        System.out.println("Lockstate is: " + model.getLockState());


        /* ~~Testing save & load~~ */

        /*Controller controller = new Controller();
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
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        Controller controller = new Controller();

        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("MVC/scenebuilder.fxml"));
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);

        controller.init(model);

        stage.setTitle("Min Dagbok");
        stage.setScene(scene);
        stage.show();

        //Runtime.getRuntime().addShutdownHook(new Thread(()-> model.shutdown()));

        /*Parent root = (Parent) FXMLLoader.load(main.class.getResource("MVC/scenebuilder.fxml"));
        Scene scene = new Scene(root);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();

    controller.init(controller.getModel());*/

    }
}
