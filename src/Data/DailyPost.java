package src.Data;

import java.time.LocalDate;
import java.util.ArrayList;

public class DailyPost implements IDay{

    private LocalDate date;
    private String text = "";
    private int grade = 0;
    private ArrayList<IMood> activeMoods = new ArrayList<>();
    private ArrayList<ITag> tags = new ArrayList<>();

    public DailyPost(){

    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int getGrade() {
        return grade;
    }


    public ArrayList<IMood> getActiveMoods() {
        return activeMoods;
    }

    @Override
    public ArrayList<ITag> getTags() {
        return tags;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }

    public void setActiveMoods(ArrayList<IMood> moods){
        this.activeMoods.addAll(moods);
    }

    public void setTags(ArrayList<ITag> tags){
        this.tags.addAll(tags);
    }

    @Override
    public void addActiveMood(IMood mood) {

    }

    @Override
    public void removeActiveMood(IMood mood) {

    }

    @Override
    public void addTag(ITag tag) {
        this.tags.add(tag);
    }

    @Override
    public void removeTag(ITag tag) {
        for (int i = 0; i < tags.size(); i++){
            if (this.tags.get(i) == tag){
                this.tags.remove(i);
                break;
            }
        }

    }
}