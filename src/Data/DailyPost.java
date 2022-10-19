package src.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Wikström
 * @author Julia Ekeblad
 *
 * Class that creates objects of type DailyPost.
 */
public class DailyPost implements IDay {

    private LocalDate date;
    private String text = "";
    private int grade = 0;
    private List<IMood> activeMoods = new ArrayList<>();
    private List<ITag> tags = new ArrayList<>();
    private List<ECondition> conditions = new ArrayList<>();

    /**
     * Constructor of DailyPost.
     *
     * It is empty due to the fact that at the moment,
     * the object's variables are set through the setters.
     */
    public DailyPost(){

    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to get the date of the object.
     *
     * @return A LocalDate.
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to get the text assigned to the object.
     *
     * @return A String which contains the text of the object.
     */
    @Override
    public String getText() {
        return this.text;
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to get the grade assigned to the object.
     *
     * @return An int which contains the object's grade.
     */
    @Override
    public int getGrade() {
        return this.grade;
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to get a list of the moods assigned to the object.
     *
     * @return A List of IMoods.
     */
    public List<IMood> getActiveMoods() {
        return this.activeMoods;
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to get a list of the tags assigned to the object.
     *
     * @return A List of ITags.
     */
    @Override
    public List<ITag> getTags() {
        return this.tags;
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to get the conditions assigned to the object.
     *
     * @return A List of EConditions.
     */
    public List<ECondition> getConditions(){
        return this.conditions;
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to set the date of the object.
     *
     * @param date  A LocalDate which is the new date.
     */
    public void setDate(LocalDate date){
        this.date = date;
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to set the text of the object.
     *
     * @param text  A String which is the new text.
     */
    public void setText(String text){
        this.text = text;
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to set the grade of the object.
     *
     * @param grade An int which is the new grade.
     */
    public void setGrade(int grade){
        this.grade = grade;
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to set/add moods to the object.
     *
     * @param moods A List of IMoods.
     */
    public void setActiveMoods(List<IMood> moods){
        this.activeMoods.addAll(moods);
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to set/add tags to the object.
     *
     * @param tags  A List of ITags.
     */
    public void setTags(List<ITag> tags){
        this.tags.addAll(tags);
    }

    /**
     * @author Adam Wikström
     *
     * A method to set/add conditions to the object.
     *
     * @param conditions    A List of EConditions.
     */
    @Override
    public void setConditions(List<ECondition> conditions){
        this.conditions.addAll(conditions);
    }

    public void addCondition(ECondition condition){
        this.conditions.add(condition);
    }

    /**
     * @author Adam Wikström
     *
     * A method to add a mood to the list ActiveMoods.
     *
     * @param mood  An IMood.
     */
    @Override
    public void addActiveMood(IMood mood) {
        activeMoods.add(mood);
    }

    /**
     * @author Adam Wikström
     *
     * A method to remove IMoods from the list ActiveMoods.
     *
     * @param mood  An IMood.
     */
    @Override
    public void removeActiveMood(IMood mood) {
        activeMoods.remove(mood);
    }

    /**
     * @author Adam Wikström
     *
     * A method to create a new tag.
     *
     * @param tagName   A String which is the new tag name.
     * @return  A Tag which implements ITag. #TODO
     */
    @Override
    public ITag createTag(String tagName){
        ITag Tag = new Tag(tagName, this.tags.size()+1);
        this.tags.add(Tag);
        return Tag;
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to add a tag to the list of ITags.
     *
     * @param tag   An ITag that is to be added to the list of ITags.
     */
    @Override
    public void addTag(ITag tag) {
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    /**
     * @author Adam Wikström
     * @author Anna Borgh
     *
     * A method to remove tags from the list ITags.
     *
     * @param tag   An ITag.
     */
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