package src.Data;

public interface IDay {

    String getDate();

    String getText();

    int getGrade();

    IMood getMood();

    ITag getTags();

    void addTag(String str);

    void removeTag(ITag tag);

}
