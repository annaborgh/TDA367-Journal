package src.MVC;
import src.Data.*;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class Model {

    /**
     * General variables.
     */
    private ArrayList<ITag> allTags = new ArrayList<>();
    private ILock lockType;
    private HashMap<LocalDate, IDay> posts;
    private final LocalDate currentDate;
    private final Charset charsetLatin;

    /**
     * Variables for the lock.
     */
    public PinLock lock;
    private boolean lockActive = false;
    private boolean lockState = true;

    public Model() {
        posts = new HashMap<>();
        currentDate = LocalDate.now();
        this.charsetLatin = ISO_8859_1;
        init();
    }

    private void init(){
        // load posts
        createAppDirectory();
        createPostsDirectory();
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

    /**
     *
     * @return
     */
    public ILock getLockType() {
        return lockType;
    }

    /**
     *
     * @return
     */
    public ArrayList<ITag> getAllTags() {
        return allTags;
    }

    /**
     *
     * @return
     */
    public LocalDate getCurrentDate() {
        return currentDate;
    }

    /**
     *
     * @return
     */
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

    //functionality for saving orders goes here
    public void savePosts() {
        for (IDay post : posts.values()){
            // create file name
            String filename = getPostsDirectoryPath() + File.separatorChar + "post_" + post.getDate() + ".txt";

            try{
                FileOutputStream outputStream = new FileOutputStream(filename);
                OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream, charsetLatin);
                String line;
                String separator = ";" + "\n";

                // date
                line = post.getDate() + "\n";
                outputWriter.write(line);
                outputWriter.write(separator);

                // text
                line = post.getText() + "\n";
                outputWriter.write(line);
                outputWriter.write(separator);

                // grade
                line = post.getGrade() + "\n";
                outputWriter.write(line);
                outputWriter.write(separator);

                // moods
                List<IMood> moods = post.getActiveMoods();
                for (IMood mood : moods) {
                    line = mood.getMoodName() + "|" + mood.getMoodRating() + "\n";
                    outputWriter.write(line);
                }
                outputWriter.write(separator);

                // tags
                List<ITag> tags = post.getTags();
                for (ITag tag : tags){
                    line = tag.getTagID() + "\n";
                    outputWriter.write(line);
                }
                outputWriter.write(separator);

                // conditions
                List<ECondition> conditions = post.getConditions();
                for (ECondition condition : conditions){
                    line = condition.name() + "\n";
                    outputWriter.write(line);
                }
                outputWriter.write(separator);

                outputWriter.flush();
                outputWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


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
        }
    }

    //create directory & file names
    private String getAppDirectoryPath(){
        return FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal";
    }
    private String getPostsDirectoryPath(){
        return this.getAppDirectoryPath() + File.separatorChar + "Posts";
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


}