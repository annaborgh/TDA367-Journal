package src.MVC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
/*
Mood sliders
1:
2:
3:
4:
* */
    @FXML private Slider moodSliderOne;
    @FXML private Slider moodSliderTwo;
    @FXML private Slider moodSliderThree;
    @FXML private Slider moodSliderFour;
    @FXML private ToggleGroup Lock;
    @FXML private ToggleGroup Grade;



    @FXML
    private TabPane statisticsTabPane;
    @FXML
    private Tab statisticsGradeTab;
    @FXML
    private LineChart<LocalDate, Number> statisticsGradeTabChart;
    @FXML
    private Button statisticsGradeTabWeekBtn;
    @FXML
    private Button statisticsGradeTabMonthBtn;
    @FXML
    private Button statisticsGradeTabYearBtn;
    @FXML
    private Tab statisticsMoodTab;
    @FXML
    private LineChart<LocalDate, Number> statisticsMoodTabChart;
    @FXML
    private Button statisticsMoodTabWeekBtn;
    @FXML
    private Button statisticsMoodTabMonthBtn;
    @FXML
    private Button statisticsMoodTabYearBtn;
    @FXML
    private Tab statisticsConditionTab;
    @FXML
    private PieChart statisticsConditionTabChart;
    @FXML
    private Tab statisticsTagTab;
    @FXML
    private PieChart statisticsTagTabChart;





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
        xAxis.setLabel("Date");
        statisticsGradeTabChart = new LineChart<LocalDate,Number>((Axis)xAxis,yAxis);
        statisticsMoodTabChart = new LineChart<LocalDate,Number>((Axis)xAxis,yAxis);;
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



    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    XYChart.Series mood1series = new XYChart.Series();
    XYChart.Series mood2series = new XYChart.Series();
    XYChart.Series mood3series = new XYChart.Series();
    XYChart.Series mood4series = new XYChart.Series();

    XYChart.Series gradeSeries = new XYChart.Series();

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

    private void populateMoodChart(){
        ETimeInterval t = ETimeInterval.YEAR;
        Pair<ArrayList<LocalDate>,ArrayList<HashMap<String,Integer>>> data = model.intervalToDataMap(t);

    }
    private void populateConditionChart(){
        Map<ECondition, Integer> conditionCountMap = model.getConditionData();

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();

        conditionCountMap.keySet().forEach(
                k->pieChartData.
                        add(new PieChart.Data(k.toString(),conditionCountMap.get(k)))
        );
        statisticsConditionTabChart.getData().setAll(pieChartData);
        statisticsConditionTabChart.setTitle("Tillstånd");

    }
    public void populateGradeChart(){
        ETimeInterval ti = ETimeInterval.MONTH;
        Pair<ArrayList<LocalDate>,ArrayList<Integer>> pair =model.intervalToGradeData(ti);
        pair.getKey().forEach(e -> {gradeSeries.getData().add(new XYChart.Data(e, pair.getValue().get(pair.getKey().indexOf(e))));});
        statisticsGradeTabChart.getData().clear();
        statisticsGradeTabChart.getData().add(gradeSeries);

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
        HashMap<Object,Long> tagsCountMap = new HashMap<>(model.getTagData());
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();

        tagsCountMap.keySet().forEach(
                k->pieChartData.
                        add(new PieChart.Data(k.toString(),tagsCountMap.get(k)))
        );
        statisticsTagTabChart.getData().setAll(pieChartData);
        statisticsTagTabChart.setTitle("Taggar");

    }
    //-----------------------Statistics logic end-----------------------
}
