package src.MVC;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private Model model = new Model();//model måste populate dates from backend
    //-----------------------Statistics logic start-----------------------
    ArrayList<Integer> moodList;
    ArrayList<Integer> dayRatingList;
    ArrayList<LocalDate> dateList;

    LineChart lineChart;
    PieChart pieChart;

    private void populateChart(){
        LocalDate date = model.currentDate;
        HashMap hm = model.getPosts();
    }

    //-----------------------Statistics logic end-----------------------
}
