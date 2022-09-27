package src.Data;

import java.time.LocalDate;
import java.util.ArrayList;

    private LocalDate date;
    private String text = "";
    private int grade = 0;
    private ArrayList<IMood> activeMoods = new ArrayList<>();
    private ArrayList<ITag> tags = new ArrayList<>();
    
    @Override
    public LocalDate getDate() {
    }

    @Override
    public String getText() {
    }

    @Override
    public int getGrade() {
    }

    @Override
    public ArrayList<ITag> getTags() {
    }

    public void setText(String text){
        this.text = text;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }

    }

    @Override
    public void removeActiveMood(IMood mood) {

    }

    @Override
    public void addTag(ITag tag) {

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
