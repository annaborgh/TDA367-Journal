package src.MVC;

import src.Data.*;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

/**
 * Class that handles persistent data management for the Journal.
 *
 * @author Anna Borgh
 */
public class Persistence implements IPersistence{
    private final Charset charsetLatin = ISO_8859_1;
    private final String typeSeparator = ";";
    private final String inlineSeparator = ",";

    /**
     * Constructor of Persistence.
     * The constructor is empty since the class only uses attributes with constant values.
     */
    public Persistence() {
    }

    /**
     * A method to save posts to text files in directory created by {@link #createPostsDirectory()}.
     *
     * @param posts A HashMap of LocalDate and DailyPost objects,
     *              that is the posts to be saved to text files.
     *
     * @author Anna Borgh
     */
    public void savePosts(HashMap<LocalDate, IDay> posts) {
        createAppDirectory();
        createPostsDirectory();

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
                    line = tag.getTitle() + inlineSeparator + tag.getTagID() + "\n";
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

    /**
     *
     * A method to load posts from text files located in directory specified by {@link #getPostsDirectoryPath()}.
     *
     * @return A hashmap containing the loaded DailyPost objects as values and their dates as keys.
     *
     * @author Anna Borgh
     */
    //loads posts from text files in MyDocuments
    public HashMap<LocalDate, IDay> loadPosts(){
        HashMap<LocalDate, IDay> posts = new HashMap<>();
        File postDirectory = new File(getPostsDirectoryPath());

        if (postDirectory.isDirectory()){
            File[] files = postDirectory.listFiles();

            assert files != null;
            for (File currentFile : files) {
                if (currentFile.getName().endsWith(".txt")) {
                    IDay post = loadPost(currentFile);
                    posts.put(post.getDate(), post);
                }
            }
        }

        return posts;
    }

    /**
     * A method that loads each individual file and creates a DailyPost object from its data.
     * Was private, changed to protected for testing purposes.
     *
     * @param file  A File to be loaded.
     * @return The loaded DailyPost.
     *
     * @author Anna Borgh
     */
    protected IDay loadPost(File file){

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
            while (!Objects.equals(line, typeSeparator)){
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
            inputStream.close();
            return post;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A method that determines whether a line in a file contains a ";", which is considered invalid
     * and results in a new line is picked, or a valid form of data, which is simply immediately returned.
     *
     * @param   reader A BufferReader.
     * @param   line  A String.
     * @return The line which consists of a String.
     * @throws IOException, see findNewLine.
     *
     * @author Anna Borgh
     */
    private String findNewLine(BufferedReader reader, String line) throws IOException {
        line = reader.readLine();
        if (Objects.equals(line, typeSeparator)){
            line = reader.readLine();
        }
        return line;
    }

    /**
     *  The method creates the program's main directory, if it does not already exist. The directory is named "TDA367_Journal"
     *  and is located in My Documents or equivalent. The path is specified by {@link #getAppDirectoryPath()}.
     *
     * @author Anna Borgh
     */
    private void createAppDirectory(){
        File directory = new File(getAppDirectoryPath());
        if (!directory.exists()){
            directory.mkdirs();
            System.out.println("App directory created");
        } else {
            System.out.println("App directory already exists");
        }
    }

    /**
     * The method creates the "Posts" directory, if it does not already exist. Its location is specified by
     * {@link #getPostsDirectoryPath()}. By default, this is inside a directory called TDA367_Journal located in My
     * Documents or equivalent.
     *
     * @author Anna Borgh
     */
    private void createPostsDirectory(){
        File directory = new File(getPostsDirectoryPath());
        if (!directory.exists()){
            directory.mkdirs();
            System.out.println("Posts directory created");
        } else {
            System.out.println("Posts directory already exists");
        }
    }

    /**
     * Finds and returns the path of the program's main directory in My Documents or equivalent.
     *
     * @return A String that specifies the directory path.
     *
     * @author Anna Borgh
     */
    private String getAppDirectoryPath(){
        return FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separatorChar + "TDA367_Journal";
    }

    /**
     * Finds and returns the path of the subdirectory "Posts" to the main program directory. This is where posts are
     * saved as text files in {@link #savePosts(HashMap)}.
     *
     * @return A String that specifies the directory path.
     *
     * @author Anna Borgh
     */
    private String getPostsDirectoryPath(){
        return this.getAppDirectoryPath() + File.separatorChar + "Posts";
    }
}
