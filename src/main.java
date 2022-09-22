package src;

import src.Data.DailyPost;
import src.Data.IDay;
import src.Data.IMood;
import src.Data.Mood;

public class main {

    public static void main(String[] args){

        /*
        *    Testing of DailyPost's methods
        */

        /*IDay day = new DailyPost("1");

        System.out.println(day.getTags());

        day.addTag("Skola");
        System.out.println(day.getTags());

        for(int i = 0; i < 9; i++){
            if (i == 3){
                day.addTag("Stolpe");
            }
            else{
                day.addTag("Bröd");
            }
        }

        System.out.println(day.getTags());

        day.removeTag("Stolpe");
        System.out.println(day.getTags());

        day.removeTag("Bröd");
        System.out.println(day.getTags());*/

        /*
        * Testing of Mood and IMood
        * */

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

    }
}
