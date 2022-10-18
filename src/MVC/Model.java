package src.MVC;

import src.Data.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model {

    private final List<ITag> allTags = new ArrayList<>();

    private ILock lockType;
    private HashMap<LocalDate, IDay> posts = new HashMap<>();
    private final LocalDate currentDate;
    private final IPersistence persistence;

    /**
     * Variables for the lock.
     */
    public PinLock lock;
    private boolean lockActive = false;
    private boolean lockState = true;

    public Model() {
        posts = new HashMap<>();
        currentDate = LocalDate.now();
        persistence = new Persistence();
        init();
    }

    public void makePost(String text, int grade, List<ITag> tags, List<IMood> moods, List<ECondition> EConditions) {
    }
    private void init(){
        //load posts
        posts = persistence.loadPosts();
    }

    protected void shutdown(){
        //save posts
        persistence.savePosts(posts);
        posts.clear();
    }

    public void makePost(String text, int grade, ArrayList<ITag> tags, ArrayList<IMood> moods, ArrayList<ECondition> EConditions){
        IDay post = new DailyPost();

        post.setDate(currentDate);
        post.setText(text);
        post.setGrade(grade);
        post.setTags(tags);
        post.setActiveMoods(moods);
        post.setConditions(EConditions);

        posts.put(currentDate, post);
    }
    public void makePost(LocalDate d,String text, int grade, ArrayList<ITag> tags, ArrayList<IMood> moods, ArrayList<ECondition> EConditions){
        IDay post = new DailyPost();

        post.setDate(d);
        post.setText(text);
        post.setGrade(grade);
        post.setTags(tags);
        post.setActiveMoods(moods);
        post.setConditions(EConditions);

        posts.put(d, post);
    }

    public ILock getLockType() {
        return lockType;
    }



    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public HashMap<LocalDate, IDay> getPosts() {
        return posts;
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

<<<<<<< HEAD
    //create directories
    private void createAppDirectory(){
        File directory = new File(getAppDirectoryPath());
        if (!directory.exists()){
            directory.mkdirs();
            System.out.println("App directory created");
        } else {
            System.out.println("App directory already exists");
        }
    }
    private void createPostsDirectory(){
        File directory = new File(getPostsDirectoryPath());
        if (!directory.exists()){
            directory.mkdirs();
            System.out.println("Posts directory created");
        } else {
            System.out.println("Posts directory already exists");
            System.out.println(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
        }
    }
    //-----------------------"Lock model" end-----------------------


}
