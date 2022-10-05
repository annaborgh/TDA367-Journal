package src.MVC;

import src.Data.*;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class Model {
    private ArrayList<ITag> allTags = new ArrayList<>();
    private ILock lockType;
    private HashMap<LocalDate, IDay> posts;
    private final LocalDate currentDate;
    private final Charset charsetLatin;

    public Model() {
        posts = new HashMap<>();
        currentDate = LocalDate.now();
        charsetLatin = ISO_8859_1;
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

    //saves posts to text files
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

                // text
                line = post.getText() + "\n";
                outputWriter.write(line);

                // grade
                line = post.getGrade() + "\n";
                outputWriter.write(line);

                // moods
                ArrayList<IMood> moods = post.getActiveMoods();
                for (IMood mood : moods) {
                    line = mood.getMoodName() + "|" + mood.getMoodRating() + "\n";
                    outputWriter.write(line);
                }
                outputWriter.write(separator);

                // tags
                ArrayList<ITag> tags = post.getTags();
                for (ITag tag : tags){
                    line = tag.getTagID() + "\n";
                    outputWriter.write(line);
                }
                outputWriter.write(separator);

                // conditions
                ArrayList<ECondition> conditions = post.getConditions();
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

    private void loadPosts(){
        File postDirectory = new File(getPostsDirectoryPath());

        if (postDirectory.isDirectory()){
            File[] files = postDirectory.listFiles();

            assert files != null;
            for (File currentFile : files) {
                if (currentFile.getName().endsWith(".txt")) {
                    loadPost(currentFile);
                }
            }
        }
    }

    private void loadPost(File file){
        try {
            FileInputStream fileInput = new FileInputStream(file);
            InputStreamReader inputStream = new InputStreamReader(fileInput);
            BufferedReader reader = new BufferedReader(inputStream);

            DailyPost post = new DailyPost();
            String line;

            line = reader.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
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
}
