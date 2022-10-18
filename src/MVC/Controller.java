package src.MVC;

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


    public void Model(Model model) {
        this.model = model;
    }

    public void init(Model model) {
        ArrayList<IMood> moods = new ArrayList<>();
        ArrayList<ECondition> eConditions = new ArrayList<>();
        model.setDefaultDate(datePicker);

        if (model.getPosts().get(model.getCurrentDate()) == null) {
            model.makePost(currentDate,"",0, model.getAllTags(), moods, eConditions);
        }

        populatePost(currentDate);


        /*this.dp = model.getPosts().get(model.getCurrentDate());
        this.dp.setText("Halloj worlden");
        System.out.println(dp.getText());
        this.dailyPostTextField.setText(dp.getText());*/
    }

    private void onGradeChanged(int grade){
        switch (grade){
            case 0: Grade.selectToggle(null);
            case 1: oneRatingRadioButton.fire();
            case 2: twoRatingRadioButton.fire();
            case 3: threeRatingRadioButton.fire();
            case 4: fourRatingRadioButton.fire();
            case 5: fiveRatingRadioButton.fire();
        }
    }

    private void populatePost(LocalDate chosenDate){

        onGradeChanged(0);
        if (model.getPosts().get(chosenDate) == null){
            ArrayList<IMood> moods = new ArrayList<>();
            ArrayList<ECondition> eConditions = new ArrayList<>();
            model.makePost(chosenDate,"",0, model.getAllTags(), moods, eConditions);
        }

        IDay temp = model.getPosts().get(chosenDate);
        //temp.setDate(currentDate);
        this.dailyPostTextField.setText(temp.getText());
        datePicker.setValue(chosenDate);
        onGradeChanged(temp.getGrade());
    }

    @FXML public void nextDay(){
        currentDate = currentDate.plusDays(1);
        populatePost(currentDate);
    }

    @FXML public void previousDay(){
        currentDate = currentDate.minusDays(1);
        populatePost(currentDate);
    }

    @FXML public void somethingDate(){
        datePicker.setPromptText("Hejsan!");
    }

    @FXML
    public void goToMoods() {
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
    @FXML public void goToStats(){
        statisticsAnchorPane.toFront();
    }
    @FXML public void goToPrefs(){
        preferencesAnchorPane.toFront();
    }
    @FXML public void goToPosts(){
        dailyPostAnchorPane.toFront();
    }
    //private Model model;

    public Controller() {
        this.startUp();
    }

    private void startUp(){
        this.model = new Model();
        model.makePost(currentDate,"hej", 3, model.getAllTags(), new ArrayList<IMood>(),new ArrayList<ECondition>());
        populatePost(model.getPosts().get(LocalDate.now()).getText());
    }

    //matching shutdown-method goes here
    public void shutdown(){
        model.shutdown();
    }

    public Model getModel() {
        return model;
    }

    public void populatePost(String post) {
        dailyPostTextField.setText(post);

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
