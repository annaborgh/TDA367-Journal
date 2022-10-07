package src.MVC;

import src.Data.*;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Controller {
    private Model model;

    public Controller() {
        this.startUp();
    }

    private void startUp(){
        this.model = new Model();
    }

    //matching shutdown-method goes here
    private void shutdown(){
        model.savePosts();
    }

    public Model getModel() {
        return model;
    }

    //-----------------------Statistics logic start-----------------------
    List<List<IMood>> moodList;
    List<Integer> dayRatingList;
    List<LocalDate> dateList;

    LineChart lineChart;
    PieChart pieChart;

    private void populateChart(){
        LocalDate date = model.getCurrentDate();
        HashMap<LocalDate, IDay> hm = model.getPosts();
        for (IDay value : hm.values()) {
            dateList.add(value.getDate());
            dayRatingList.add(value.getGrade());
            moodList.add(value.getActiveMoods());
        }
    }

    //-----------------------Statistics logic end-----------------------
}
