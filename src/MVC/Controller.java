package src.MVC;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import src.Data.DailyPost;
import src.Data.IDay;
import src.Data.IMood;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private static Model model = new Model();//model måste populate dates from backend
    //-----------------------Statistics logic start-----------------------
    ArrayList<ArrayList<IMood>> moodList;
    ArrayList<Integer> dayRatingList;
    ArrayList<LocalDate> dateList;

    LineChart moodChart;
    LineChart dayRatingChart;
    PieChart tagsChart;
    PieChart conditionChart;

    public static void main(String[] args) {
        model.makePost("a",2,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        model.makePost("a",2,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        model.makePost("a",2,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        model.makePost("a",3,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        model.makePost("a",4,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        model.makePost("a",2,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        model.makePost("a",4,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        model.makePost("a",2,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        System.out.println(model.getPosts());
        populateDayRatingChart();
    }
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
     /*   HashMap<LocalDate, ArrayList<IMood>> moodMap = model.getMoodMap();*/
        /*for (LocalDate value : moodMap.keySet()) {
            dates.add(value);
        }*/
        //Check if better to iterate through map altogether.t
    }
    private void populateConditionChart(){
       /* HashMap<LocalDate, ArrayList<IMood>> conditionMap = model.getConditionMap();*/

    }
    private static void populateDayRatingChart(){
     /*   HashMap<LocalDate, ArrayList<IMood>> dayRatingMap = model.getDayRatingMap();*/
        ArrayList<Integer> intlist= new ArrayList<>();
        for (IDay d: model.getPosts().values()) {
            intlist.add(d.getGrade());
        }
        List<Integer> twolist= intlist.stream().filter(e-> e==2).collect(Collectors.toList());
        Map<Integer, List<Integer>> ratios = intlist.stream().collect(Collectors.groupingBy(Integer::intValue));
        System.out.println(ratios);
    }
    private void populateTagsChart(){
        /*HashMap<LocalDate, ArrayList<IMood>> tagsMap = model.getTagsMap();*/

    }
    //-----------------------Statistics logic end-----------------------
}
