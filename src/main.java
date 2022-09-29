package src;


import src.Data.*;
import src.MVC.*;

import java.util.ArrayList;

public class main {

    public static void main(String[] args){
        /* ~~Testing Controller~~ */

        Controller controller = new Controller();
        Model model = controller.getModel();

        Tag tag = new Tag("test", model.getAllTags());
        tag.setTagID(42);

        ArrayList<ITag> tags = new ArrayList<>();
        tags.add(tag);

        IMood mood = new Mood("testing mood");

        ArrayList<IMood> moods = new ArrayList<>();
        moods.add(mood);

        ArrayList<ECondition> conditions = new ArrayList<>();

        model.makePost("Hej detta är ett test", 4, tags, moods, conditions);

        model.savePosts();
    }
}
