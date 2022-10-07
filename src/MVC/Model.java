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
    private ArrayList<ITag> allTags = new ArrayList<>();
    private ILock lockType;
    private HashMap<LocalDate, IDay> posts;
    private final LocalDate currentDate;
    private final Charset charsetLatin;

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

    public ILock getLockType() {
        return lockType;
    }

    public ArrayList<ITag> getAllTags() {
        return allTags;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public HashMap<LocalDate, IDay> getPosts() {
        return posts;
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

    public PinLock pinLock;
    private String pinCode;


    public void lockLogic(){
        String code = "";
        boolean lockValid;

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter pin: ");

        code = reader.nextLine();
        PinLock pinLock = new PinLock(code, 0);

        System.out.println(pinLock.getPinCode());



    }

    public void createPinLock(){
        boolean lockValid = false;
        int tempPinID = 0;
        while (!lockValid) {

            lockValid = true;
            // Manual input to the console
            // code = reader.nextLine();
            /*lockValid = Model.checkValidInput(pinCode);
            if (!lockValid) {
                System.out.println("Invalid code!");
            }
             */
        }
        if (this.pinLock != null){
            tempPinID = this.pinLock.getLockId() + 1;
        {
        this.pinLock = new PinLock(pinCode, tempPinID);
    }

    public static boolean checkValidInput(String codeLen){
        for (int i = 0; i < codeLen.length(); i++) {
            if (Character.isLetter(codeLen.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //-----------------------"Lock model" end-----------------------

}
