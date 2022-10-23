package src.MVC;

import javafx.scene.control.DatePicker;
import javafx.util.Pair;
import src.Data.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Model {
    private static Model instance = null;
    private final List<ITag> allTags = new ArrayList<>();
    private ILock lockType;
    private HashMap<LocalDate, IDay> posts = new HashMap<>();
    private LocalDate currentDate;
    private final IPersistence persistence;

    /**
     * Variables for the lock.
     */
    public PinLock lock;
    private boolean lockActive = true;
    private boolean lockState = true;

    public Model() {
        currentDate = LocalDate.now();
        persistence = new Persistence();
        init();
    }

    private void init(){
        //load posts
        posts = persistence.loadPosts();
    }

    public void shutdown(){
        //save posts
        persistence.savePosts(posts);
        posts.clear();
    }

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

    public ILock getLockType() {
        return lockType;
    }

    public List<ITag> getAllTags() {
        return allTags;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public HashMap<LocalDate, IDay> getPosts() {
        return posts;
    }

    public void setDefaultDate(DatePicker datePicker){
        datePicker.setValue(currentDate);

    }

    /**
     *
     * @return
     */
    public PinLock getLock(){
        return this.lock;
    }

    /**
     *
     * @return
     */
    public boolean getLockState(){
        return this.lockState;
    }
    /**
     *
     * @return
     */
    public boolean getLockActive(){
        return this.lockActive;
    }

    public void setLockActive(Boolean lockActive){
        this.lockActive = lockActive;
    }

    //-----------------------"Lock model" start--------------------

    /**
     * Method to create the pin lock.
     *
     * @param pinCode The pin code that is to be assigned to the pin lock.
     */
    public void createPinLock(String pinCode) {
        if(lock != null) {
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
     * @return      A boolean symbolizing if input is valid.
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
     */
    public void unlockLock(String inp){
        if (lockActive) {
            if (inp == lock.getPinCode()) {
                lockState = false;
            }
        }
    }

    /**
     * Method to lock the lock.
     */
    public void lockLock(){
        if(lockActive) {
            lockState = true;
        }
    }



    //-----------------------"Lock model" end-----------------------
    //-----------------------Statistics logic start-----------------
    Pair<ArrayList<LocalDate>,ArrayList<HashMap<String,Integer>>>  intervalToDataMap(ETimeInterval ti){
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
                    DailyPost dailyPost = (DailyPost) getPosts().get(d);
                    if (dailyPost == null){
                        dataPair.getKey().add(d);
                        dataPair.getValue().add(null);
                        continue;
                    }
                    HashMap<String, Integer> tmpMap= new HashMap<>();
                    getPosts().get(d).getActiveMoods().forEach(e->{
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
    Pair<ArrayList<LocalDate>,ArrayList<Integer>> intervalToGradeData(ETimeInterval ti){
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
                    weeklyRating = tmpWeeklyRating.getValue()/tmpWeeklyRating.getKey();

                    dataPair.getKey().add(d);
                    dataPair.getValue().add(weeklyRating);
                }
                break;
            }


        }



        return dataPair;
    }
    public Map<ECondition, Integer> getConditionData() {
        Map<ECondition, Integer> conditionCountMap = new HashMap<>();
        for (ECondition e:ECondition.values()) {
            conditionCountMap.put(e,0);
        }
        getPosts().values().stream().map(e->{
            List<ECondition> conditions = e.getConditions();
            for (ECondition eCondition: conditions) {
                int x = conditionCountMap.get(eCondition).intValue();
                conditionCountMap.put(eCondition, x++);
            }
            return conditionCountMap;
        });
        return conditionCountMap;

    }
    public Map<Object, Long> getTagData(){
        ArrayList<ITag> tags = new ArrayList<>(getAllTags());
        /*HashMap<LocalDate, ArrayList<IMood>> tagsMap = model.getTagsMap();*/
        Map<Object, Long> chartData = getPosts().values().stream()
                .collect(Collectors.groupingBy(p -> p.getTags(),
                        Collectors.counting()));
        for (int x = 0; x < tags.size(); x++){
            chartData.putIfAbsent(tags.get(x),0L);
        }
        System.out.println(tags);
        return chartData;
    }
    //-----------------------Statistics logic end-----------------

}
