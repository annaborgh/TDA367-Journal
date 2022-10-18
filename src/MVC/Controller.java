package src.MVC;

import javafx.scene.chart.*;
/*import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;*/
import javafx.util.Pair;
import src.Data.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import src.Data.DailyPost;
import src.Data.IDay;
import src.Data.IMood;

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
    public void shutdown(){
        model.shutdown();
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
        c.getModel().makePost("a",3,new ArrayList<>(), new ArrayList<>(Arrays.asList(new Mood("MISCONTENTTOCONTENT",10),new Mood("SADTOHAPPY",12),new Mood("SCAREDTOSAFE",25),new Mood("DISGUSTEDTOSURPRISED",57))),new ArrayList<>());
        c.getModel().makePost(LocalDate.now().minusDays(1),"a",3,new ArrayList<>(), new ArrayList<>(Arrays.asList(new Mood("MISCONTENTTOCONTENT",20),new Mood("SADTOHAPPY",20),new Mood("SCAREDTOSAFE",10),new Mood("DISGUSTEDTOSURPRISED",38))),new ArrayList<>());
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
     HashMap<LocalDate, HashMap<String, Integer>> intervalToDataMap(ETimeInterval ti){
         HashMap<LocalDate,HashMap<String,Integer>> dates = new HashMap<>();
        if(ti==null) return dates;
        switch (ti){
            case WEEK -> {
                LocalDate currentDate = LocalDate.now();
                for (int i = 0; i < 7; i++) {
                    LocalDate d = currentDate.minusDays(i);
                    HashMap<String,Integer> tmpMap = new HashMap<>();
                    model.getPosts().get(d).getActiveMoods().forEach(e->{
                        tmpMap.put(e.getMoodName(),e.getMoodRating());
                    });
                    dates.put(d,tmpMap);
                }
                System.out.println(dates);
            }
            case MONTH -> {
                LocalDate currentDate = LocalDate.now();
                for (int i = 0; i < currentDate.getMonth().length(currentDate.isLeapYear()); i++) {
                    LocalDate d = currentDate.minusDays(i);
                    HashMap<String, Integer> tmpMap= new HashMap<>();
                    model.getPosts().get(d).getActiveMoods().forEach(e->{
                        tmpMap.put(e.getMoodName(),e.getMoodRating());
                    });

                    dates.put(d,tmpMap);
                }
            }
            case YEAR -> {

                for (int i = 0; i < 1; i++) {
                    LocalDate d = LocalDate.now().minusWeeks(i);
                    /*HashMap<EMood,IMood> weeklyMoodMap;*/
                    HashMap<String, Integer> weeklyMoodMap = new HashMap<>() ;
                    HashMap<String, Pair<Integer,Integer>> tmpMoodMap = new HashMap<>() ;
                    IMood m = null;
                    for (int j = 0; j < 7; j++) {
                       LocalDate currentDay =  d.minusDays(d.getDayOfWeek().getValue()-1 - j);
                       if(model.getPosts().get(currentDay)==null) {
                           System.out.println(currentDay);
                       }
                       else{
                           model.getPosts().get(currentDay).getActiveMoods().forEach(e -> {
                               tmpMoodMap.putIfAbsent(e.getMoodName(),new Pair<>(0,0));
                               Pair<Integer, Integer> p = tmpMoodMap.get(e.getMoodName());
                               tmpMoodMap.put(e.getMoodName(),
                                       new Pair<>(p.getKey()+1,p.getValue().intValue()+e.getMoodRating())) ;
                           });
                       }
                    }
                    tmpMoodMap.forEach((k,v)-> weeklyMoodMap.put(k,v.getValue()/v.getKey()));
                    dates.put(d, weeklyMoodMap);
                    System.out.println(weeklyMoodMap );
                }
                System.out.println(dates);
            }
        }

        return dates;
    }
    private void populateMoodChart(){
        ETimeInterval t = ETimeInterval.YEAR;
        HashMap data = intervalToDataMap(t);

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
