package src.MVC;

import src.Data.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Adam Wikström
 * @author Anna Borgh
 * @author Wilma Nordlund
 *
 * TODO
 */
public class Model {
    /**
     * General variables.
     */
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

    /**
     * General variables.
     * Variables for date and persistence.
     */
    public Model() {
        currentDate = LocalDate.now();
        persistence = new Persistence();
        init();
    }

    /**
     * @author Anna Borgh
     *
     * A method to load posts.
     */
    private void init(){
        //load posts
        posts = persistence.loadPosts();
    }

    /**
     * @author Anna Borgh
     *
     * A method to save posts.
     */
    protected void shutdown(){
        //save posts
        persistence.savePosts(posts);
        posts.clear();
    }

    /**
     * @author Anna Borgh
     *
     * A method to make a new post.
     *
     * @param text  A String which contains the text.
     * @param grade An int which contains the grade.
     * @param tags  An ArrayList with tag objects.
     * @param moods An ArrayList with the mood objects.
     * @param EConditions   An ArrayList with the EConditions.
     */
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

    public ILock getLockType() {
        return lockType;
    }

    public List<ITag> getAllTags() {
        return this.allTags;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public HashMap<LocalDate, IDay> getPosts() {
        return posts;
    }

    /**
     * @author Adam Wikström
     * @author Wilma Nordlund
     *
     * Getter for the lock.
     *
     * @return A PinLock which contains the lock.
     */
    public PinLock getLock(){
        return this.lock;
    }

    /**
     * @author Adam Wikström
     * @author Wilma Nordlund
     *
     * Getter for the lock state.
     *
     * @return A boolean which contains the LockState on the lock.
     */
    public boolean getLockState(){
        return this.lockState;
    }

    //-----------------------"Lock model" start--------------------

    /**
     * @author Adam Wikström
     * @author Wilma Nordlund
     *
     * Method to create the pin lock.
     *
     * @param pinCode   The pin code that is to be assigned to the pin lock.
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
     * @author Adam Wikström
     * @author Wilma Nordlund
     *
     * Method to check if attempted pincode for new lock is valid,
     * i.e. only contains numbers.
     *
     * @param inp   The input given.
     * @return A boolean symbolizing if input is valid.
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
     * @author Adam Wikström
     * @author Wilma Nordlund
     *
     * Method to unlock the lock. To unlock the lock,
     * the input must be the same as the pin code.
     * If the input is correct,
     * lock state is turned off - the lock unlocks.
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
     * @author Adam Wikström
     * @author Wilma Nordlund
     *
     * Method to lock the lock.
     */
    public void lockLock(){
        if(lockActive) {
            lockState = true;
        }
    }

    //-----------------------"Lock model" end-----------------------


}
