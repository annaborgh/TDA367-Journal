package src;

import src.Data.*;
import src.MVC.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class main {

    public static void main(String[] args){
        /* ~~Testing save & load~~ */

        Controller controller = new Controller();
        Model model = controller.getModel();

        //save
        Tag tag = new Tag("test", model.getAllTags().size()+1);

        ArrayList<ITag> tags = new ArrayList<>();
        tags.add(tag);

        IMood mood = new Mood();
        mood.setName("testing");
        mood.setMoodRating(42);
        ArrayList<IMood> moods = new ArrayList<>();
        moods.add(mood);
        ArrayList<ECondition> conditions = new ArrayList<>();
        conditions.add(ECondition.SICK);
        model.makePost("Hej detta är ett test" + "\n" + "fungerar det med radbyten också?", 4, tags, moods, conditions);
        model.savePosts();

        //load
        HashMap<LocalDate, IDay> posts = model.getPosts();
        IDay post = posts.get(LocalDate.now());

        System.out.println(post.getDate());
        System.out.println(post.getText());
        System.out.println(post.getTags());
        System.out.println(post.getConditions());
    }
}
