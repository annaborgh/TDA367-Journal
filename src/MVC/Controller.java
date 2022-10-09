package src.MVC;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import src.Data.IDay;
import src.Data.IMood;
import src.Data.ITag;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private static Model model = new Model();//model måste populate dates from backend
    //-----------------------Statistics logic start-----------------------
    ArrayList<ArrayList<IMood>> moodList;
    ArrayList<Integer> dayRatingList;
    ArrayList<LocalDate> dateList;

    LineChart moodChart;
    PieChart dayRatingChart;
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
        new Controller().populateMoodChart();
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

        HashMap<LocalDate, IDay> moodMap = model.getPosts();
        /*moodMap.values().stream().map(e->e.getActiveMoods()).collect(Collectors.toList());*/
        moodMap.entrySet().clear();
        System.out.println(moodMap.toString());
        /*for (LocalDate value : moodMap.keySet()) {
            dates.add(value);
        }*/
        //Check if better to iterate through map altogether.t
    }
    private void populateConditionChart(){
       /* HashMap<LocalDate, ArrayList<IMood>> conditionMap = model.getConditionMap();*/

    }
    public void populateDayRatingChart(){
        //this code below
        Map<Integer, Long> chartData = model.getPosts().values().stream()
                .collect(Collectors.groupingBy(p -> p.getGrade(),
                        Collectors.counting()));
        for (int x = 1; x <= 5; x++){
            chartData.putIfAbsent(x,0L);
        }

        ArrayList<Integer> intlist= new ArrayList<>();
        intlist.add(1);intlist.add(1);intlist.add(2);intlist.add(4);intlist.add(4);intlist.add(4);intlist.add(5);

        Map<Integer, Long> counters = intlist.stream()
                .collect(Collectors.groupingBy(p -> p.intValue(),
                        Collectors.counting()));
        for (int x = 1; x <= 5; x++){
            counters.putIfAbsent(x,0L);
        }

        System.out.println(counters);

    }
    private void populateTagsChart(){
        ArrayList<ITag> tags = model.getAllTags();
        /*HashMap<LocalDate, ArrayList<IMood>> tagsMap = model.getTagsMap();*/
        Map<Object, Long> chartData = model.getPosts().values().stream()
                .collect(Collectors.groupingBy(p -> p.getTags(),
                        Collectors.counting()));
        for (int x = 0; x < tags.size(); x++){
            chartData.putIfAbsent(tags.get(x),0L);
        }
        System.out.println(tags);
    }
    //-----------------------Statistics logic end-----------------------
}
