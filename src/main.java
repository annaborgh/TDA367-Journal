package src;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.Data.*;
import src.MVC.*;
import src.MVC.ViewHandler;

import java.util.ArrayList;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(main.class.getResource("MVC/scenebuilder.fxml"));

        Scene scene = new Scene(root);
        scene.setRoot(root);

        stage.setScene(scene);
        stage.show();

        /*ViewHandler viewHandler = new ViewHandler(stage);
        viewHandler.start();*/


    }

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



    //}
}
