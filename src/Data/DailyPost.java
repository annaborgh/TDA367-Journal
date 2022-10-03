package src.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DailyPost implements IDay {

    private LocalDate date;
    private String text = "";
    private int grade = 0;
    private List<IMood> activeMoods = new ArrayList<>();
    private List<ITag> tags = new ArrayList<>();
    private List<ECondition> conditions = new ArrayList<>();

    public DailyPost(){

    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public int getGrade() {
        return this.grade;
    }


    public List<IMood> getActiveMoods() {
        return this.activeMoods;
    }

    @Override
    public List<ITag> getTags() {
        return this.tags;
    }

    public List<ECondition> getConditions(){
        return this.conditions;
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

    public void setActiveMoods(List<IMood> moods){
        this.activeMoods.addAll(moods);
    }

    public void setTags(List<ITag> tags){
        this.tags.addAll(tags);
    }

    @Override
    public void setConditions(ArrayList<ECondition> conditions) {

    }

    public void setConditions(List<ECondition> contitions){
        this.conditions.addAll(contitions);
    }

    @Override
    public void addActiveMood(IMood mood) {
    }

    @Override
    public void removeActiveMood(IMood mood) {
    }

    @Override
    public ITag createTag(String tagName){
        ITag Tag = new Tag(tagName, this.tags);
        this.tags.add(Tag);
        return Tag;
    }

    @Override
    public void addTag(ITag tag) {
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        }
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