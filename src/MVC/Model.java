package src.MVC;

import javafx.scene.control.DatePicker;
import javafx.util.Pair;
import src.Data.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static src.Data.EMood.*;

/**
 * @author Adam Wikström
 * @author Anna Borgh
 * @author Julia Ekeblad
 * @author Tarek Chorfi
 * @author Wilma Nordlund
 */
public class Model {
    private static Model instance = null;
    private final List<ITag> allTags = new ArrayList<>();
    private ILock lockType;
    private HashMap<LocalDate, IDay> posts = new HashMap<>();
    private LocalDate currentDate;
    private final IPersistence persistence;

    /**
     * Variables for the lock.
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    public PinLock lock;
    private boolean lockActive = true;
    private boolean lockState = true;

    public Model() {
        currentDate = LocalDate.now();
        persistence = new Persistence();
        init();
    }

    /**
     * @author Anna Borgh
     */
    private void init(){
        //load posts
        posts = persistence.loadPosts();
    }

    /**
     * @author Anna Borgh
     */
    public void shutdown(){
        //save posts
        persistence.savePosts(posts);
        posts.clear();
    }

    /**
     * A method for making posts.
     *
     * @param date A LocalDate.
     * @param text A String.
     * @param grade An int.
     * @param tags A List of ITags.
     * @param moods An ArrayList of IMood.
     * @param EConditions An ArrayList of ECondition.
     *
     * @author Adam Wikström
     * @author Anna Borgh
     */
    public void makePost(LocalDate date, String text, int grade, List<ITag> tags, ArrayList<IMood> moods, ArrayList<ECondition> EConditions){
        IDay post = new DailyPost();

        post.setDate(date);
        post.setText(text);
        post.setGrade(grade);
        post.setTags(tags);
        post.setActiveMoods(moods);
        post.setConditions(EConditions);

        posts.put(date, post);
    }

    /**
     * A getter for the type of lock.
     *
     * @return The type of lock (ILock).
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    public ILock getLockType() {
        return lockType;
    }

    /**
     * A getter for all the tags.
     *
     * @return A List of ITags.
     *
     * @author Adam Wikström
     */
    public List<ITag> getAllTags() {
        return allTags;
    }


    /**
     * A getter for the current date.
     *
     * @return the current date, which is a LocalDate.
     *
     * @author Adam Wikström
     */
    public LocalDate getCurrentDate() {
        return currentDate;
    }

    /**
     * A getter for the posts.
     *
     * @return The posts, which are a HashMap of LocalDate and IDay.
     *
     * @author Adam Wikström
     */
    public HashMap<LocalDate, IDay> getPosts() {
        return posts;
    }

    /**
     * A getter for the specific lock.
     *
     * @return The lock, which is a PinLock.
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    public PinLock getLock(){
        return this.lock;
    }

    /**
     * A getter for the lock state, which can either be true or false.
     *
     * @return The lock state, which is a boolean.
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    public boolean getLockState(){
        return this.lockState;
    }

    /**
     * A getter for active lock.
     *
     * @return Boolean.
     *
     * @author Adam Wikström
     * @author Julia Ekeblad
     * @author Wilma Nordlund
     */
    public boolean getLockActive(){
        return this.lockActive;
    }

    /**
     * A setter for active lock.
     *
     * @param lockActive    Boolean.
     *
     * @author Adam Wikström
     * @author Julia Ekeblad
     * @author Wilma Nordlund
     */
    public void setLockActive(Boolean lockActive){
        this.lockActive = lockActive;
    }

    //-----------------------"Lock model" start--------------------

    /**
     * Method to create the pin lock.
     *
     * @param pinCode The pin code that is to be assigned to the pin lock.
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    public void createPinLock(String pinCode) {
        if(lock == null) {
            if (checkValidInput(pinCode)) {
                this.lock = new PinLock(pinCode);
                lockActive = true;
            }
        }
    }

    /**
     * Method to check if attempted pincode for new lock is valid, i.e. only contains numbers.
     *
     * @param inp   The input given.
     * @return A boolean symbolizing if input is valid.
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    private boolean checkValidInput(String inp){
        for (int i = 0; i < inp.length(); i++){
            if(!Character.isDigit(inp.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to unlock the lock. To unlock the lock, the input must be the same as the pin code.
     * If the input is correct, lock state is turned off - the lock unlocks.
     *
     * @param inp   The input given.
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    public void unlockLock(String inp){
        if (lockActive && lock != null) {
            if (inp == lock.getPinCode()) {
                lockState = false;
            }
        }
    }

    /**
     * Method to lock the lock.
     *
     * @author Adam Wikström
     * @author Wilma Nordlund
     */
    public void lockLock(){
        if(lockActive) {
            lockState = true;
        }
    }



    //-----------------------"Lock model" end-----------------------

    /**
     * Quite self explanatory, this method populates the model with a lot of sample data.
     * Nothing too fancy, just making sure every field has a valid non-null value.
     * This method’s purpose is just for displaying that the statistics logic works and also the graphical representation in the controller/view.
     *
     * @author Tarek Chorfi
     */
    public void makeLotsOfPosts(){
        ArrayList<ITag> alltags = new ArrayList<>(Arrays.asList(new Tag("Tag1",1),new Tag("Tag2",2),new Tag("Tag3",3),new Tag("Tag4",4),new Tag("Tag5",5)));
        LocalDate currDate =  LocalDate.now();
        //Populate recent month
        int tagAmount = alltags.size();
        for (int i = 0; i < currDate.getMonth().length(currDate.isLeapYear()); i++) {
            ITag tag =  alltags.get((int) Math.floor(Math.random()*tagAmount));
            ECondition eCondition = ECondition.values()[(int) Math.floor(Math.random()*ECondition.values().length)];
            ArrayList<IMood> moods = new ArrayList<>(Arrays.asList(
                    new Mood(MISCONTENTTOCONTENT.toString(), (int) Math.round(Math.random()*100)),
                    new Mood(SADTOHAPPY.toString(), (int) Math.round(Math.random()*100)),
                    new Mood(SCAREDTOSAFE.toString(), (int) Math.round(Math.random()*100)),
                    new Mood(DISGUSTEDTOSURPRISED.toString(), (int) Math.round(Math.random()*100))
            ));
            int randomGrade =(int) Math.round(5*Math.random());
            makePost(currDate.minusDays(i),"Journal log for day"+currDate.toString()+" today has been...", randomGrade,new ArrayList<>(Arrays.asList(tag)),moods,new ArrayList<>(Arrays.asList(eCondition)));
        }
        for (int i = 0; i < 52; i++) {
            ITag tag =  alltags.get((int) Math.floor(Math.random()*tagAmount));
            ECondition eCondition = ECondition.values()[(int) Math.floor(Math.random()*ECondition.values().length)];
            ArrayList<IMood> moods = new ArrayList<>(Arrays.asList(
                    new Mood(MISCONTENTTOCONTENT.toString(), (int) Math.round(Math.random()*100)),
                    new Mood(SADTOHAPPY.toString(), (int) Math.round(Math.random()*100)),
                    new Mood(SCAREDTOSAFE.toString(), (int) Math.round(Math.random()*100)),
                    new Mood(DISGUSTEDTOSURPRISED.toString(), (int) Math.round(Math.random()*100))
            ));
            int randomGrade =(int) Math.round(5*Math.random());
            makePost(currDate.minusWeeks(i),"Journal log for day"+currDate.toString()+" today has been...", randomGrade,new ArrayList<>(Arrays.asList(tag)),moods,new ArrayList<>(Arrays.asList(eCondition)));
        }

    }
    //-----------------------Statistics logic start-----------------

    /**
     * This method takes an ETimeInterval and returns a pair of lists where one list represents the dates,
     * and one list represents the mood entries for each day.
     *
     * @param ti An ETimeInterval.
     * @return A Pair of lists: one represents dates, and the other the mood entries for each day.
     *
     * @author Tarek Chorfi
     */
    public Pair<ArrayList<LocalDate>,ArrayList<HashMap<String,Integer>>>  intervalToDataMap(ETimeInterval ti){
        HashMap<LocalDate,HashMap<String,Integer>> dates = new HashMap<>();
        Pair<ArrayList<LocalDate>,ArrayList<HashMap<String,Integer>>> dataPair = new Pair<>(new ArrayList<>(),new ArrayList<>());
        if(ti==null) return dataPair;
        switch (ti){
            case WEEK -> {
                LocalDate currentDate = LocalDate.now();
                for (int i = 0; i < 7; i++) {
                    LocalDate d = currentDate.minusDays(i);
                    DailyPost dailyPost = (DailyPost) getPosts().get(d);
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
                    ;
                    if (getPosts().get(d) == null){
                        dataPair.getKey().add(d);
                        dataPair.getValue().add(null);
                        continue;
                    }
                    DailyPost dailyPost = (DailyPost) getPosts().get(d);
                    HashMap<String, Integer> tmpMap= new HashMap<>();
                    dailyPost.getActiveMoods().forEach(e->{
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
                        DailyPost dailyPost = (DailyPost) getPosts().get(currentDay);
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
                }
                break;
            }


        }

        return dataPair;
    }

    /**
     * This method takes an ETimeInterval and returns a pair of lists,
     * where one list represents the dates and one list represents the grade for each day.
     *
     * @param ti An ETimeInterval.
     * @return A pair of lists: one for the dates and one for the grades.
     *
     * @author Tarek Chorfi
     */
    public Pair<ArrayList<LocalDate>,ArrayList<Integer>> intervalToGradeData(ETimeInterval ti){
        HashMap<LocalDate,HashMap<String,Integer>> dates = new HashMap<>();
        Pair<ArrayList<LocalDate>,ArrayList<Integer>> dataPair = new Pair<>(new ArrayList<>(),new ArrayList<>());
        if(ti==null) return dataPair;
        switch (ti){
            case WEEK -> {
                LocalDate currentDate = LocalDate.now();
                for (int i = 0; i < 7; i++) {
                    LocalDate d = currentDate.minusDays(i);
                    DailyPost dailyPost = (DailyPost) getPosts().get(d);
                    if (dailyPost == null || dailyPost.getGrade() == 0){
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
                    DailyPost dailyPost = (DailyPost) getPosts().get(d);
                    if (dailyPost == null|| dailyPost.getGrade() == 0){
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
                        DailyPost dailyPost = (DailyPost) getPosts().get(currentDay);
                        if (dailyPost == null || dailyPost.getGrade() == 0){
                            continue;
                        }

                        tmpWeeklyRating = new Pair<>(tmpWeeklyRating.getKey()+1,tmpWeeklyRating.getValue()+ dailyPost.getGrade());


                    }
                    if (tmpWeeklyRating.getKey()==0){
                        dataPair.getKey().add(d);
                        dataPair.getValue().add(0);
                    }
                    int divisor = tmpWeeklyRating.getKey();
                    if(divisor==0)divisor=1;
                    weeklyRating = tmpWeeklyRating.getValue()/divisor;

                    //weeklyRating = tmpWeeklyRating.getValue()/tmpWeeklyRating.getKey();

                    dataPair.getKey().add(d);
                    dataPair.getValue().add(weeklyRating);
                }
                break;
            }


        }



        return dataPair;
    }

    /**
     * Streams through the models posts and counts the occurrence of every condition,
     * and returns it as a Map of conditions as keys and integer values that represent the occurrence
     * of that condition through all posts.
     *
     * @return A Map that represents the data from the conditions.
     *
     * @author Tarek Chorfi
     */
    public Map<ECondition, Integer> getConditionData() {
        Map<ECondition, Integer> conditionCountMap = new HashMap<>();
        for (ECondition e:ECondition.values()) {
            conditionCountMap.put(e,0);
        }


        getPosts().values().forEach(e->{
            List<ECondition> conditions = e.getConditions();
            for (ECondition eCondition: conditions) {
                int x = conditionCountMap.get(eCondition).intValue();
                conditionCountMap.put(eCondition, ++x);
            }
        });
        return conditionCountMap;

    }

    /**
     * Streams through the models posts and counts the occurrence of every tag,
     * and returns it as a Map of tags as keys and integer values that represent the occurrence	of that tag through all posts.
     *
     * @return A Map that represents the data from the tags.
     *
     * @author Tarek Chorfi
     */
    public Map<Object, Long> getTagData(){
        ArrayList<ITag> tags = new ArrayList<>(getAllTags());
        /*HashMap<LocalDate, ArrayList<IMood>> tagsMap = model.getTagsMap();*/
        Map<Object, Long> chartData = getPosts().values().stream()
                .collect(Collectors.groupingBy(p -> p.getTags(),
                        Collectors.counting()));
        for (int x = 0; x < tags.size(); x++){
            chartData.putIfAbsent(tags.get(x).getTitle(),0L);
        }
        return chartData;
    }
    //-----------------------Statistics logic end-----------------

}
