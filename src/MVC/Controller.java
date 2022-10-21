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
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import src.Data.DailyPost;
import src.Data.ECondition;
import src.Data.IDay;
import src.Data.IMood;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {

    @FXML private AnchorPane dailyPostAnchorPane;
    @FXML private AnchorPane miniCalendarAnchorPane; //Finns inte?
    @FXML private AnchorPane moodAnchorPane;
    @FXML private AnchorPane preferencesAnchorPane;
    @FXML private AnchorPane statisticsAnchorPane;
    @FXML private AnchorPane tagsAnchorPane;
    @FXML private AnchorPane pinAnchorPane;

    @FXML private Button calendarButton;
    @FXML private Button changeCodeButton;
    @FXML private Button moodsButton;
    @FXML private Button myJournalButton;
    @FXML private Button newPostButton;
    @FXML private Button nextDayButton;
    @FXML private Button searchButton; //Finns inte??
    @FXML private Button seeTagsButton;
    @FXML private Button statisticsButton;
    @FXML private Button preferencesButton; //Dates?
    @FXML private Button previousDayButton; //Dates??
    @FXML private Button checkPasswordButton;

    @FXML private CheckBox activeLockCheckBox;

    @FXML private FlowPane tagsFlowPane;

    @FXML private GridPane ratingGridPane;

    @FXML private ImageView preferencesImageView;

    @FXML private Label lockSettingsLabel;
    @FXML private Label lockTypeLabel;
    @FXML private Label tagsLabel;
    @FXML private Label changeCodeLabel;
    @FXML private Label moodSliderFourLabel;
    @FXML private Label moodSliderOneLabel;
    @FXML private Label moodSliderThreeLabel;
    @FXML private Label moodSliderTwoLabel;
    @FXML private Label myTagsLabel;
    @FXML private Label preferencesLabel;

    @FXML private ListView<?> tagsListView;

    @FXML private PasswordField newCodePasswordField;
    @FXML private PasswordField pinPasswordField;

    @FXML private RadioButton oneRatingRadioButton;
    @FXML private RadioButton twoRatingRadioButton;
    @FXML private RadioButton threeRatingRadioButton;
    @FXML private RadioButton fourRatingRadioButton;
    @FXML private RadioButton fiveRatingRadioButton;
    @FXML private RadioButton passwordLockRadioButton;
    @FXML private RadioButton patternLockRadioButton;
    @FXML private RadioButton pinLockRadioButton;

    @FXML private Slider moodSliderFour;
    @FXML private Slider moodSliderOne;
    @FXML private Slider moodSliderThree;
    @FXML private Slider moodSliderTwo;
    @FXML private ToggleGroup Lock;
    @FXML private ToggleGroup Grade;
    @FXML private TextField dailyPostTextField = new TextField();
    @FXML private DatePicker datePicker;

    private Model model;
    private IDay dp;
    private LocalDate currentDate = LocalDate.now();
    private String password = "1234";

    public Controller() {
        this.startUp();
    }

    public void init(Model modelParam) {

        if (model.getLockActive()){
            pinAnchorPane.toFront();
        }

        lockActive();
        ArrayList<IMood> moods = new ArrayList<>();
        ArrayList<ECondition> eConditions = new ArrayList<>();
        model.setDefaultDate(datePicker);

        if (model.getPosts().get(model.getCurrentDate()) == null) {
            model.makePost(currentDate,"",0, model.getAllTags(), moods, eConditions);
        }

        populatePost(currentDate);
        //password = changePassword();
        newCodePasswordField.setText(password);
    }

    private void onGradeChanged(int grade){
        System.out.println(grade);
        if (grade == 0){
            oneRatingRadioButton.setSelected(false);
            twoRatingRadioButton.setSelected(false);
            threeRatingRadioButton.setSelected(false);
            fourRatingRadioButton.setSelected(false);
            fiveRatingRadioButton.setSelected(false);
        }
        else if (grade == 1){
            oneRatingRadioButton.setSelected(true);
        }
        else if (grade == 2){
            twoRatingRadioButton.setSelected(true);
        }
        else if (grade == 3){
            threeRatingRadioButton.setSelected(true);
        }
        else if (grade == 4){
            fourRatingRadioButton.setSelected(true);
        }
        else if (grade == 5){
            fiveRatingRadioButton.setSelected(true);
        }

    }

    @FXML public void populatePost(LocalDate chosenDate){
        if (model.getPosts().get(chosenDate) == null){
            ArrayList<IMood> moods = new ArrayList<>();
            ArrayList<ECondition> eConditions = new ArrayList<>();
            model.makePost(chosenDate,"",0, model.getAllTags(), moods, eConditions);
        }

        IDay temp = model.getPosts().get(chosenDate);

        if (temp.getText().trim().length() > 0){
            this.dailyPostTextField.setText(temp.getText());
        } else {
            this.dailyPostTextField.setText("");
        }
        datePicker.setValue(chosenDate);
        onGradeChanged(model.getPosts().get(chosenDate).getGrade());
        System.out.println(model.getPosts().get(chosenDate).getDate());
    }

    public void lockActive(){
        if (model.getLockActive()){
            activeLockCheckBox.fire();
        }
        else{
            activeLockCheckBox.setSelected(false);
        }
    }
    @FXML public void changeLockActive(){
        model.setLockActive(activeLockCheckBox.isSelected());
    }

    public int isGrade(){
        int grade;
        if (oneRatingRadioButton.isSelected()){
            grade = 1;
            System.out.println(grade);
            return grade;
        }
        else if(twoRatingRadioButton.isSelected()){
            grade=2;
            System.out.println(grade);
            return grade;
        }
        else if(threeRatingRadioButton.isSelected()){
            grade=3;
            System.out.println(grade);
            return grade;
        }
        else if(fourRatingRadioButton.isSelected()){
            grade=4;
            System.out.println(grade);
            return grade;
        }
        else if(fiveRatingRadioButton.isSelected()){
            grade=5;
            System.out.println(grade);
            return grade;
        }
        else {
            grade = 0;
            System.out.println(grade);
            return grade;
        }
    }

    @FXML public void changePassword(){
        password = newCodePasswordField.getText();
        System.out.println(password);
    }

    @FXML public void nextDay(){
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());
        currentDate = currentDate.plusDays(1);
        populatePost(currentDate);
    }
    @FXML public void previousDay(){
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());
        currentDate = currentDate.minusDays(1);
        populatePost(currentDate);
    }
    @FXML public void pickedDate(){
        currentDate = datePicker.getValue();
        populatePost(currentDate);
    }

    @FXML public void goToMoods() {
        moodAnchorPane.toFront();
    }
    @FXML public void goToTags(){
        tagsAnchorPane.toFront();
    }
    @FXML public void closeMoods(){
        moodAnchorPane.toBack();
    }
    @FXML public void closeTags(){
        tagsAnchorPane.toBack();
    }
    @FXML public void closePin(){
        pinAnchorPane.toBack();
    }
    @FXML public void goToStats(){
        statisticsAnchorPane.toFront();
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());
    }
    @FXML public void goToPrefs(){
        preferencesAnchorPane.toFront();
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());
    }
    @FXML public void goToPosts(){
        dailyPostAnchorPane.toFront();
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());

    }
    @FXML public void pinButtonPushed(){
        System.out.println(pinPasswordField.getText());
        if (pinPasswordField.getText().equals(password)){
            pinAnchorPane.toBack();
            dailyPostAnchorPane.toFront();
        }
    }

    private void startUp(){
        this.model = new Model();

        if(model.getPosts().size() != 0){
            populatePost(model.getPosts().get(LocalDate.now()).getText());
        }
    }

    //matching shutdown-method goes here
    public void shutdown(){
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());
        model.shutdown();
    }

    public Model getModel() {
        return model;
    }

    public void populatePost(String post) {
        dailyPostTextField.setText(post);
    }

    //-----------------------Statistics logic start-----------------------

    LineChart moodChart;
    LineChart dayRatingChart;
    PieChart tagsChart;
    PieChart conditionChart;

    /*final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    XYChart.Series series3 = new XYChart.Series();
    XYChart.Series series4 = new XYChart.Series();*/
    /*public static void main(String[] args) {
        Controller c = new Controller();
        //c.getModel().makePost("a",3,new ArrayList<>(), new ArrayList<>(Arrays.asList(new Mood("MISCONTENTTOCONTENT",10),new Mood("SADTOHAPPY",12),new Mood("SCAREDTOSAFE",25),new Mood("DISGUSTEDTOSURPRISED",57))),new ArrayList<>());
        //c.getModel().makePost(LocalDate.now().minusDays(1),"a",3,new ArrayList<>(), new ArrayList<>(Arrays.asList(new Mood("MISCONTENTTOCONTENT",20),new Mood("SADTOHAPPY",20),new Mood("SCAREDTOSAFE",10),new Mood("DISGUSTEDTOSURPRISED",38))),new ArrayList<>());
        c.populateGradeChart();

    }*/
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

    //This method returns a hashmap with the correct dates and values that will be in the x and y axis of the chart respectively.
    Pair<ArrayList<LocalDate>,ArrayList<HashMap<String,Integer>>>  intervalToDataMap(ETimeInterval ti){
         HashMap<LocalDate,HashMap<String,Integer>> dates = new HashMap<>();
         Pair<ArrayList<LocalDate>,ArrayList<HashMap<String,Integer>>> dataPair = new Pair<>(new ArrayList<>(),new ArrayList<>());
        if(ti==null) return dataPair;
        switch (ti){
            case WEEK -> {
                LocalDate currentDate = LocalDate.now();
                for (int i = 0; i < 7; i++) {
                    LocalDate d = currentDate.minusDays(i);
                    DailyPost dailyPost = (DailyPost) model.getPosts().get(d);
                    if (dailyPost == null){
                        dataPair.getKey().add(d);
                        dataPair.getValue().add(null);
                        continue;
                    }
                    HashMap<String,Integer> tmpMap = new HashMap<>();
                    dailyPost.getActiveMoods().forEach(e->{
                        tmpMap.put(e.getMoodName(),e.getMoodRating());
                    });
                    dates.put(d,tmpMap);
                    dataPair.getKey().add(d);
                    dataPair.getValue().add(tmpMap);
                }

                break;
            }
            case MONTH -> {
                LocalDate currentDate = LocalDate.now();
                for (int i = 0; i < currentDate.getMonth().length(currentDate.isLeapYear()); i++) {
                    LocalDate d = currentDate.minusDays(i);
                    DailyPost dailyPost = (DailyPost) model.getPosts().get(d);
                    if (dailyPost == null){
                        dataPair.getKey().add(d);
                        dataPair.getValue().add(null);
                        continue;
                    }
                    HashMap<String, Integer> tmpMap= new HashMap<>();
                    model.getPosts().get(d).getActiveMoods().forEach(e->{
                        tmpMap.put(e.getMoodName(),e.getMoodRating());
                    });

                    dates.put(d,tmpMap);
                    dataPair.getKey().add(d);
                    dataPair.getValue().add(tmpMap);
                }
                break;
            }
            case YEAR -> {

                for (int i = 0; i < 52; i++) {
                    LocalDate d = LocalDate.now().minusWeeks(i);
                    /*HashMap<EMood,IMood> weeklyMoodMap;*/
                    HashMap<String, Integer> weeklyMoodMap = new HashMap<>() ;
                    HashMap<String, Pair<Integer,Integer>> tmpMoodMap = new HashMap<>() ;
                    IMood m = null;
                    for (int j = 0; j < 7; j++) {
                       LocalDate currentDay =  d.minusDays(d.getDayOfWeek().getValue()-1 - j);
                        DailyPost dailyPost = (DailyPost) model.getPosts().get(currentDay);
                        if (dailyPost == null){

                            continue;
                        }

                        dailyPost.getActiveMoods().forEach(e -> {
                            tmpMoodMap.putIfAbsent(e.getMoodName(),new Pair<>(0,0));
                            Pair<Integer, Integer> p = tmpMoodMap.get(e.getMoodName());
                            tmpMoodMap.put(e.getMoodName(),
                                    new Pair<>(p.getKey()+1,p.getValue().intValue()+e.getMoodRating())) ;
                        });

                    }
                    tmpMoodMap.forEach((k,v)-> weeklyMoodMap.put(k,v.getValue()/v.getKey()));
                    dates.put(d, weeklyMoodMap);
                    dataPair.getKey().add(d);
                    dataPair.getValue().add(weeklyMoodMap);
                    System.out.println(weeklyMoodMap );
                }
                System.out.println(dates);
                break;
            }


        }

         System.out.println("dataDates: " + dataPair.getValue().size()+ " dataValues: " + dataPair.getKey().size());

        return dataPair;
    }
    private void populateMoodChart(){
        ETimeInterval t = ETimeInterval.YEAR;
        Pair<ArrayList<LocalDate>,ArrayList<HashMap<String,Integer>>> data = intervalToDataMap(t);

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
    public void populateGradeChart(){
        ETimeInterval ti = ETimeInterval.MONTH;
        Pair<ArrayList<LocalDate>,ArrayList<Integer>> pair =intervalToGradeData(ti);


    }
    Pair<ArrayList<LocalDate>,ArrayList<Integer>>  intervalToGradeData(ETimeInterval ti){
        HashMap<LocalDate,HashMap<String,Integer>> dates = new HashMap<>();
        Pair<ArrayList<LocalDate>,ArrayList<Integer>> dataPair = new Pair<>(new ArrayList<>(),new ArrayList<>());
        if(ti==null) return dataPair;
        switch (ti){
            case WEEK -> {
                LocalDate currentDate = LocalDate.now();
                for (int i = 0; i < 7; i++) {
                    LocalDate d = currentDate.minusDays(i);
                    DailyPost dailyPost = (DailyPost) model.getPosts().get(d);
                    if (dailyPost == null){
                        dataPair.getKey().add(d);
                        dataPair.getValue().add(null);
                        continue;
                    }

                    dataPair.getKey().add(d);
                    dataPair.getValue().add(dailyPost.getGrade());
                }

                break;
            }
            case MONTH -> {
                LocalDate currentDate = LocalDate.now();
                for (int i = 0; i < currentDate.getMonth().length(currentDate.isLeapYear()); i++) {
                    LocalDate d = currentDate.minusDays(i);
                    DailyPost dailyPost = (DailyPost) model.getPosts().get(d);
                    if (dailyPost == null){
                        dataPair.getKey().add(d);
                        dataPair.getValue().add(null);
                        continue;
                    }
                    dataPair.getKey().add(d);
                    dataPair.getValue().add(dailyPost.getGrade());


                }
                break;
            }
            case YEAR -> {

                for (int i = 0; i < 52; i++) {
                    LocalDate d = LocalDate.now().minusWeeks(i);
                    /*HashMap<EMood,IMood> weeklyMoodMap;*/
                    Integer weeklyRating = null;
                    Pair<Integer,Integer> tmpWeeklyRating = new Pair<>(0,0);
                    IMood m = null;
                    for (int j = 0; j < 7; j++) {

                        LocalDate currentDay =  d.minusDays(d.getDayOfWeek().getValue()-1 - j);
                        DailyPost dailyPost = (DailyPost) model.getPosts().get(currentDay);
                        if (dailyPost == null || dailyPost.getGrade() == 0){
                            continue;
                        }

                        tmpWeeklyRating = new Pair<>(tmpWeeklyRating.getKey()+1,tmpWeeklyRating.getValue()+ dailyPost.getGrade());


                    }
                    if (tmpWeeklyRating.getKey()==0){
                        dataPair.getKey().add(d);
                        dataPair.getValue().add(0);
                    }
                    weeklyRating = tmpWeeklyRating.getValue()/tmpWeeklyRating.getKey();

                    dataPair.getKey().add(d);
                    dataPair.getValue().add(weeklyRating);
                }
                break;
            }


        }

        System.out.println("dataDates: " + dataPair.getValue()+ " dataValues: " + dataPair.getKey());

        return dataPair;
    }
   /* public void populateDayRatingChart(){
        //this code below
        *//*Map<Integer, Long> chartData = model.getPosts().values().stream()
                .collect(Collectors.groupingBy(p -> p.getGrade(),
                        Collectors.counting()));
        for (int x = 1; x <= 5; x++){
            chartData.putIfAbsent(x,0L);
        }*//*

        ArrayList<Integer> intlist= new ArrayList<>();
        intlist.add(1);intlist.add(1);intlist.add(2);intlist.add(4);intlist.add(4);intlist.add(4);intlist.add(5);

        Map<Integer, Long> counters = intlist.stream()
                .collect(Collectors.groupingBy(p -> p.intValue(),
                        Collectors.counting()));
        for (int x = 1; x <= 5; x++){
            counters.putIfAbsent(x,0L);
        }

        System.out.println(counters);

    }*/
    private void populateTagsChart(){
        ArrayList<ITag> tags = new ArrayList<>(model.getAllTags());
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
