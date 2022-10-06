package src.MVC;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import src.Data.DailyPost;
import src.Data.IDay;
import src.Data.IMood;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private Model model = new Model();//model måste populate dates from backend
    //-----------------------Statistics logic start-----------------------
    ArrayList<ArrayList<IMood>> moodList;
    ArrayList<Integer> dayRatingList;
    ArrayList<LocalDate> dateList;

    LineChart lineChart;
    PieChart pieChart;

    /*private void populateChart(){ //Split into more methods later potentially
        LocalDate date = model.currentDate;
        HashMap<LocalDate, IDay> hm = model.getPosts();
        //This will gather all data available.
        *//*for (IDay value : hm.values()) {
            dateList.add(value.getDate());
            dayRatingList.add(value.getGrade());
            moodList.add(value.getActiveMoods());
        }*//*

    }*/
    //Assume functionality exists in model
    private void populateMoodChart(){
        ArrayList<LocalDate> dates = new ArrayList<>();
        HashMap<LocalDate, ArrayList<IMood>> moodMap = model.getMoodMap();
        /*for (LocalDate value : moodMap.keySet()) {
            dates.add(value);
        }*/
        //Check if better to iterate through map altogether.t
    }
    private void populateConditionChart(){
        HashMap<LocalDate, ArrayList<IMood>> conditionMap = model.getConditionMap();

    }
    private void populateDayRatingChart(){
        HashMap<LocalDate, ArrayList<IMood>> dayRatingMap = model.getDayRatingMap();

    }
    private void populateTagsChart(){
        HashMap<LocalDate, ArrayList<IMood>> tagsMap = model.getTagsMap();

    }
    //-----------------------Statistics logic end-----------------------
}
