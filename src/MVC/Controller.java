package src.MVC;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import src.Data.*;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class Controller {
    private Model model;
    private Charset charsetLatin;

    public Controller() {
        this.startUp();
    }

    private void startUp(){
        this.model = new Model();
        this.charsetLatin = ISO_8859_1;
        createAppDirectory();
        createPostsDirectory();

    }

    //matching shutdown-method goes here
    private void shutdown(){
        savePosts();
    }

    public Model getModel() {
        return model;
    }

    //functionality for saving orders goes here
    public void savePosts() {
        HashMap<LocalDate, IDay> posts = model.getPosts();

        for (IDay post : posts.values()){
            // create file name
            String filename = getPostsDirectoryPath() + File.separatorChar + "post_" + post.getDate() + ".txt";

            try{
                FileOutputStream outputStream = new FileOutputStream(filename);
                OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream, charsetLatin);
                String line;

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

                // tags
                ArrayList<ITag> tags = post.getTags();
                for (ITag tag : tags){
                    line = tag.getTagID() + "\n";
                    outputWriter.write(line);
                }

                // conditions
                // TODO getConditions not available, fix during meeting (scared of merge conflicts :/)

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
}
