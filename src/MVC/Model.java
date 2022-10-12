package src.MVC;

import src.Data.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class Model {
    public ArrayList<ITag> getAllTags() {
        return allTags;
    }

    private ArrayList<ITag> allTags = new ArrayList<>();
    private ILock lockType;
    private HashMap<LocalDate, IDay> posts = new HashMap<>();
    private final LocalDate currentDate;
    private final Charset charsetLatin;
    private final String typeSeparator = ";";
    private final String inlineSeparator = ",";

    public Model() {
        posts = new HashMap<>();
        currentDate = LocalDate.now();
        this.charsetLatin = ISO_8859_1;
        init();
    }

    public void makePost(String text, int grade, List<ITag> tags, List<IMood> moods, List<ECondition> EConditions) {
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



    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public HashMap<LocalDate, IDay> getPosts() {
        return posts;
    }

    //saves posts to text files
    public void savePosts() {
        for (IDay post : posts.values()){
            String newline = "\n";
            // create file name
            String filename = getPostsDirectoryPath() + File.separatorChar + "post_" + post.getDate() + ".txt";

            try{
                FileOutputStream outputStream = new FileOutputStream(filename);
                OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream, charsetLatin);
                String line;

                // date
                line = post.getDate() + "\n";
                outputWriter.write(line);
                outputWriter.write(typeSeparator + newline);

                // text
                line = post.getText() + "\n";
                outputWriter.write(line);
                outputWriter.write(typeSeparator + newline);

                // grade
                line = post.getGrade() + "\n";
                outputWriter.write(line);
                outputWriter.write(typeSeparator + newline);

                // moods
                List<IMood> moods = post.getActiveMoods();
                for (IMood mood : moods) {
                    line = mood.getMoodName() + inlineSeparator + mood.getMoodRating() + "\n";
                    outputWriter.write(line);
                }
                outputWriter.write(typeSeparator + newline);

                // tags
                List<ITag> tags = post.getTags();
                for (ITag tag : tags){
                    line = tag.getTagID() + "\n";
                    outputWriter.write(line);
                }
                outputWriter.write(typeSeparator + newline);

                // conditions
                List<ECondition> conditions = post.getConditions();
                for (ECondition condition : conditions){
                    line = condition.name() + "\n";
                    outputWriter.write(line);
                }
                outputWriter.write(typeSeparator + newline);

                outputWriter.flush();
                outputWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void loadPosts(){
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

    public void loadPost(File file){
        try {
            FileInputStream fileInput = new FileInputStream(file);
            InputStreamReader inputStream = new InputStreamReader(fileInput, charsetLatin);
            BufferedReader reader = new BufferedReader(inputStream);

            DailyPost post = new DailyPost();
            String line;

            line = reader.readLine();

            //date
            if (line != null){
                LocalDate date;
                date = LocalDate.parse(line);
                post.setDate(date);
            }

            line = findNewLine(reader, line);

            //text
            StringBuilder stringBuilder = new StringBuilder();
            while (line != null && !Objects.equals(line, typeSeparator)){
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
            post.setText(stringBuilder.toString());

            line = findNewLine(reader,line);

            //grade
            if (line != null){
                post.setGrade(Integer.parseInt(line));
            }

            line = findNewLine(reader, line);

            //moods
            while (line != null && !Objects.equals(line, typeSeparator)){
                IMood mood = new Mood();
                String[] tokens = line.split(inlineSeparator);
                //name
                mood.setName(tokens[0]);
                //rating
                mood.setMoodRating(Integer.parseInt(tokens[1]));
                post.addActiveMood(mood);

                line = reader.readLine();
            }

            line = findNewLine(reader,line);

            //tags
            while (line != null && !Objects.equals(line, typeSeparator)){
                String tagName;
                int tagID;

                String[] tokens = line.split(inlineSeparator);
                tagName = tokens[0];
                tagID = Integer.parseInt(tokens[1]);

                ITag tag = new Tag(tagName,tagID);
                post.addTag(tag);

                line = reader.readLine();
            }

            line = findNewLine(reader, line);

            //conditions
            while (line != null && !Objects.equals(line, typeSeparator)){
                ECondition condition;
                condition = Enum.valueOf(ECondition.class, line);
                post.addCondition(condition);
                line = reader.readLine();
            }

            //finish
            posts.put(post.getDate(), post);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String findNewLine(BufferedReader reader, String line) throws IOException {
        line = reader.readLine();
        if (Objects.equals(line, typeSeparator)){
            line = reader.readLine();
        }
        return line;
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
            System.out.println(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
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
