package src.MVC;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Pair;
import src.Data.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static src.Data.EMood.*;

/**
 * The controller class.
 *
 * @author Adam Wikström
 * @author Anna Borgh
 * @author Julia Ekeblad
 * @author Tarek Chorfi
 * @author Wilma Nordlund
 */
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
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxisMood = new NumberAxis();
    final NumberAxis yAxisGrade = new NumberAxis();

    @FXML
    private Tab statisticsGradeTab;

    @FXML
    private LineChart<String, Number> statisticsGradeTabChart= new LineChart<String,Number>(xAxis,yAxisGrade);;
    @FXML
    private Button statisticsGradeTabWeekBtn;
    @FXML
    private Button statisticsGradeTabMonthBtn;
    @FXML
    private Button statisticsGradeTabYearBtn;
    @FXML
    private Tab statisticsMoodTab;
    @FXML
    private LineChart<String, Number> statisticsMoodTabChart = new LineChart<String,Number>(xAxis,yAxisMood);
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

    /**
     * @author Julia Ekeblad
     * @author Tarek Chorfi
     */
    public Controller() {
        this.startUp();
        model.makeLotsOfPosts();
    }

    /**
     * Adds correct labels to the mood sliders on the mood modal panel.
     * Adds event listeners to the time interval buttons for the line charts in the statistics anchorpane.
     * Adds event listeners for tab changes in statistics.
     * The yAxis bound setters don’t appear to work.
     *
     * @author Julia Ekeblad
     * @author Tarek Chorfi
     */
    @FXML
    public void initialize() {

        moodSliderOneLabel.setText(MISCONTENTTOCONTENT.toString());
        moodSliderTwoLabel.setText(SADTOHAPPY.toString());
        moodSliderThreeLabel.setText(SCAREDTOSAFE.toString());
        moodSliderFourLabel.setText(DISGUSTEDTOSURPRISED.toString());
        statisticsGradeTabWeekBtn.setOnAction(event -> populateGradeChart(ETimeInterval.WEEK));
        statisticsGradeTabMonthBtn.setOnAction(event -> populateGradeChart(ETimeInterval.MONTH));
        statisticsGradeTabYearBtn.setOnAction(event -> populateGradeChart(ETimeInterval.YEAR));
        statisticsMoodTabWeekBtn.setOnAction(event -> populateMoodChart(ETimeInterval.WEEK));
        statisticsMoodTabMonthBtn.setOnAction(event -> populateMoodChart(ETimeInterval.MONTH));
        statisticsMoodTabYearBtn.setOnAction(event -> populateMoodChart(ETimeInterval.YEAR));
        yAxisGrade.setLowerBound(1);
        yAxisGrade.setUpperBound(5);
        yAxisGrade.setAutoRanging(false);
        yAxisMood.setLowerBound(1);
        yAxisMood.setAutoRanging(false);
        yAxisMood.setUpperBound(100);
        xAxis.setLabel("Date");

        statisticsGradeTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                populateGradeChart(ETimeInterval.YEAR);
            }
        });
        statisticsConditionTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                populateConditionChart();
            }
        });
        statisticsTagTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                populateTagsChart();
            }
        });
        statisticsMoodTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                populateMoodChart(ETimeInterval.YEAR);
            }
        });
    }

    /**
     * The init method.
     *
     * @param modelParam A Model.
     */
    public void init(Model modelParam) {

        if (model.getLockActive()){
            pinAnchorPane.toFront();
        }

        lockActive();
        ArrayList<IMood> moods = new ArrayList<>();
        ArrayList<ECondition> eConditions = new ArrayList<>();
        datePicker.setValue(LocalDate.now());

        if (model.getPosts().get(model.getCurrentDate()) == null) {
            model.makePost(currentDate,"",0, model.getAllTags(), moods, eConditions);
        }

        populatePost(currentDate);
        //password = changePassword();
        newCodePasswordField.setText(password);
    }

    /**
     * Method for changing a grade.
     *
     * @param grade An int.
     *
     * @author Julia Ekeblad
     */
    private void onGradeChanged(int grade){
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

    /**
     * A method to populate the post.
     *
     * @param chosenDate A LocalDate.
     *
     * @author Julia Ekeblad
     */
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

    /**
     * A method to see if the lock is active.
     *
     * @author Julia Ekeblad
     */
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

    /**
     * A method for getting a grade.
     *
     * @return The grade.
     *
     * @author Julia Ekeblad
     */
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

    /**
     * Method for changing password.
     *
     * @author Julia Ekeblad
     */
    @FXML public void changePassword(){
        password = newCodePasswordField.getText();
        System.out.println(password);
    }

    /**
     * A method for the next day.
     *
     * @author Julia Ekeblad
     */
    @FXML public void nextDay(){
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());
        currentDate = currentDate.plusDays(1);
        populatePost(currentDate);
    }

    /**
     * A method for previous day.
     * Which means that it is similar to nextDay, apart from "minusDays"/"plusDays".
     *
     * @author Julia Ekeblad
     */
    @FXML public void previousDay(){
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());
        currentDate = currentDate.minusDays(1);
        populatePost(currentDate);
    }

    /**
     * A method for the date picker.
     *
     * @author Julia Ekeblad
     */
    @FXML public void pickedDate(){
        currentDate = datePicker.getValue();
        populatePost(currentDate);
    }

    /**
     * Opens the current entry’s mood slider modal panel.
     * The different sliders show the correct value but manipulation is not yet implemented.
     *
     * @author Julia Ekeblad
     * @author Tarek Chorfi
     */
    @FXML public void goToMoods() {

        int mood1 =0;
        int mood2 =0;
        int mood3 =0;
        int mood4 =0;
        for (IMood m : model.getPosts().get(currentDate).getActiveMoods()) {
            if(m.getMoodName().equals(MISCONTENTTOCONTENT.toString())) mood1 = m.getMoodRating();
            if(m.getMoodName().equals(SADTOHAPPY.toString()))          mood2 = m.getMoodRating();
            if(m.getMoodName().equals(SCAREDTOSAFE.toString()))        mood3 = m.getMoodRating();
            if(m.getMoodName().equals(DISGUSTEDTOSURPRISED.toString()))mood4 = m.getMoodRating();
        }
        moodSliderOne.setValue(mood1);
        moodSliderTwo.setValue(mood2);
        moodSliderThree.setValue(mood3);
        moodSliderFour.setValue(mood4);
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

    /**
     * Opens the statistics anchorpane and populates the grade chart.
     * Also saves the current post.
     *
     * @author Julia Ekeblad
     * @author Tarek Chorfi
     */
    @FXML public void goToStats(){
        statisticsAnchorPane.toFront();
        populateGradeChart(ETimeInterval.YEAR);
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());
    }

    /**
     * A method to go to preferences/settings.
     *
     * @author Julia Ekeblad
     */
    @FXML public void goToPrefs(){
        preferencesAnchorPane.toFront();
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());
    }

    /**
     * A method to go to the posts.
     *
     * @author Julia Ekeblad
     */
    @FXML public void goToPosts(){
        dailyPostAnchorPane.toFront();
        model.makePost(currentDate,dailyPostTextField.getText(),isGrade(),model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());

    }

    /**
     * A method for the pin password input.
     *
     * @author Julia Ekeblad
     */
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
        model.shutdown();
    }

    public Model getModel() {
        return model;
    }

    /**
     * A method to populate the post.
     *
     * @param post A String.
     *
     * @author Julia Ekeblad
     */
    public void populatePost(String post) {
        dailyPostTextField.setText(post);
    }

    //-----------------------Statistics logic start-----------------------


    /**
     * This method calls on intervalToDataMap from the model to get the relevant data,
     * and then goes through each element and adds it to 4 different series that represent the 4 different moods
     * which is then added to the mood chart on the mood tab in the statistics anchorpane and thus statistical data is shown.
     *
     * The data sent to the chart was in the reversed order for some reason so a reversion of the chart data was needed.
     * For some reason there was a duplication error shown if I reversed the series and then added it to the chart.
     * So I had to make a new series with the reversed data from the previous series.
     * That’s why the ending looks a bit cluttered.
     *
     * @param t An ETimeInterval.
     *
     * @author Tarek Chorfi
     */
    private void populateMoodChart(ETimeInterval t){
        XYChart.Series mood1series = new XYChart.Series(); //MISCONTENTTOCONTENT
        XYChart.Series mood2series = new XYChart.Series(); //SADTOHAPPY
        XYChart.Series mood3series = new XYChart.Series(); //SCAREDTOSAFE
        XYChart.Series mood4series = new XYChart.Series(); //DISGUSTEDTOSURPRISED
        Pair<ArrayList<LocalDate>,ArrayList<HashMap<String,Integer>>> data = model.intervalToDataMap(t);
        ArrayList<LocalDate> dates = data.getKey();
        ArrayList<HashMap<String,Integer>> moods = data.getValue();
        XYChart.Series<String,Number> gradeSeries = new XYChart.Series<String,Number>();
        for (int i = 0; i < dates.size(); i++) {

                Integer inte = moods.get(i).get(MISCONTENTTOCONTENT.toString());
                Integer inte2 = moods.get(i).get(SADTOHAPPY.toString());
                Integer inte3 = moods.get(i).get(SCAREDTOSAFE.toString());
                Integer inte4 = moods.get(i).get(DISGUSTEDTOSURPRISED.toString());


                if(inte==null ) inte = 0;
                if(inte2==null ) inte2 = 0;
                if(inte3==null ) inte3 = 0;
                if(inte4==null ) inte4 = 0;


            mood1series.getData().add(new XYChart.Data<>(dates.get(i).toString(),inte));
            mood2series.getData().add(new XYChart.Data<>(dates.get(i).toString(),inte2));
            mood3series.getData().add(new XYChart.Data<>(dates.get(i).toString(),inte3));
            mood4series.getData().add(new XYChart.Data<>(dates.get(i).toString(),inte4));

        }
        ArrayList ar1 = new ArrayList(mood1series.getData());
        ArrayList ar2 = new ArrayList(mood2series.getData());
        ArrayList ar3 = new ArrayList(mood3series.getData());
        ArrayList ar4 = new ArrayList(mood4series.getData());
        statisticsMoodTabChart.getData().clear();
        Collections.reverse(ar1);
        Collections.reverse(ar2);
        Collections.reverse(ar3);
        Collections.reverse(ar4);
        XYChart.Series mood1srs = new XYChart.Series(); //MISCONTENTTOCONTENT //not sure why this fixes the problem???
        XYChart.Series mood2srs = new XYChart.Series();//SADTOHAPPY
        XYChart.Series mood3srs = new XYChart.Series();//SCAREDTOSAFE
        XYChart.Series mood4srs = new XYChart.Series();//DISGUSTEDTOSURPRISED
        mood1srs.getData().addAll(ar1);
        mood2srs.getData().addAll(ar2);
        mood3srs.getData().addAll(ar3);
        mood4srs.getData().addAll(ar4);
        statisticsMoodTabChart.getData().add(mood1srs);
        statisticsMoodTabChart.getData().add(mood2srs);
        statisticsMoodTabChart.getData().add(mood3srs);
        statisticsMoodTabChart.getData().add(mood4srs);
        statisticsMoodTabChart.setVisible(true);
    }

    /**
     * Calls the method getConditionData from the model,
     * and then adds the data to the condition pie chart.
     *
     * @author Tarek Chorfi
     */
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

    /**
     * This method calls on intervalToGradeData from the model to get the relevant data and then goes through each element,
     * and adds it to a series object that represent the grade for each day which is then added to the grade chart on the grade tab in the statistics anchorpane,
     * and thus statistical data is shown.
     * Same duplication error as populateMoodChart, hence the cluttered code in the ending.
     *
     * @param ti An ETimeInterval.
     *
     * @author Tarek Chorfi
     */
    public void populateGradeChart(ETimeInterval ti){
        XYChart.Series<String,Number> gradeSeries = new XYChart.Series<String,Number>();

        Pair<ArrayList<LocalDate>,ArrayList<Integer>> pair = model.intervalToGradeData(ti);

        pair.getKey().forEach(e -> {
            Integer inte = pair.getValue().get(pair.getKey().indexOf(e));

            if(inte==null ) inte = 0;

            gradeSeries.getData().add(
                    new XYChart.Data(e.toString(), inte )
            );
        });

        ArrayList ar1 = new ArrayList(gradeSeries.getData());
        statisticsGradeTabChart.getData().clear();
        Collections.reverse(ar1);
        XYChart.Series gradeSeriesReversed = new XYChart.Series();
        gradeSeriesReversed.getData().addAll(ar1);
        statisticsGradeTabChart.getData().add(gradeSeriesReversed);




        statisticsGradeTabChart.setVisible(true);


    }


    /**
     * Calls the method getTagsData from the model and then adds the data to the tags pie chart.
     *
     * @author Tarek Chorfi
     */
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