package src.Data;

import java.util.Date;

public class DailyPost implements IDay{
    private Date date;

    public DailyPost(Date date){
        this.date = date;
    }

    public Date getDate(){
        return date;
    }
}
