package src.MVC;

import javafx.scene.chart.*;
/*import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;*/
import javafx.util.Pair;
import src.Data.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
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

    LineChart moodChart;
    PieChart dayRatingChart;
    PieChart tagsChart;
    PieChart conditionChart;

    /*final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    XYChart.Series series3 = new XYChart.Series();
    XYChart.Series series4 = new XYChart.Series();*/
    public static void main(String[] args) {
        Controller c = new Controller();
        c.getModel().makePost("a",3,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        c.populateMoodChart();

    }
    /*private void populateChart(){ //Split into more methods later potentially
        LocalDate date = model.currentDate;
    LineChart lineChart;
    PieChart pieChart;

    private void populateChart(){
        LocalDate date = model.getCurrentDate();
        HashMap<LocalDate, IDay> hm = model.getPosts();
        //This will gather all data available.
        *//*for (IDay value : hm.values()) {
            dateList.add(value.getDate());
            dayRatingList.add(value.getGrade());
            moodList.add(value.getActiveMoods());
        }*//*

    }*/
    //Assume functionality exists in model

    //This method returns a hashmap with the correct dates that will be in the X axis of the chart.
     HashMap<LocalDate,IMood> intervalToDatesMap(TimeInterval ti){
         HashMap<LocalDate,IMood> dates = new HashMap<>();
        if(ti==null) return dates;
        switch (ti){
            case WEEK -> {
                LocalDate d = LocalDate.now();
                for (int i = 0; i < 7; i++) {
                    dates.put(d.minusDays(i),null);
                }
                System.out.println(dates);
            }
            case MONTH -> {
                LocalDate d = LocalDate.now();
                for (int i = 0; i < d.getMonth().length(d.isLeapYear()); i++) {
                    dates.put(d.minusDays(i),null);
                }
                System.out.println(dates);
            }
            case YEAR -> {
                LocalDate d = LocalDate.now();
                for (int i = 0; i < 52; i++) {
                    dates.put(d.minusWeeks(i),null);
                }
                System.out.println(dates);
            }
        }
        return dates;
    }
    private void populateMoodChart(){
        TimeInterval t = TimeInterval.MONTH;
        HashMap dates = intervalToDatesMap(t);
        /*for (LocalDate value : moodMap.keySet()) {
            dates.add(value);
        }*/
        //Check if better to iterate through map altogether.t
        Map<LocalDate, List<IMood>> moodMap = model.getPosts().values().stream().collect(Collectors.toMap(IDay::getDate, IDay::getActiveMoods));
        System.out.println(moodMap);

    }
    private void populateConditionChart(){
        Map<ECondition, Integer> conditionCountMap = new HashMap<>();
        for (ECondition e:ECondition.values()) {
            conditionCountMap.put(e,0);
        }
        model.getPosts().values().stream().map(e->{
            List<ECondition> conditions = e.getConditions();
            for (ECondition eCondition: conditions) {
                int x = conditionCountMap.get(eCondition).intValue();
                conditionCountMap.put(eCondition, x++);
            }
            return null;
        });



    }
    public void populateDayRatingChart(){
        //this code below
        /*Map<Integer, Long> chartData = model.getPosts().values().stream()
                .collect(Collectors.groupingBy(p -> p.getGrade(),
                        Collectors.counting()));
        for (int x = 1; x <= 5; x++){
            chartData.putIfAbsent(x,0L);
        }*/

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
