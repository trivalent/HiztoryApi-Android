package in.co.shuklarahul.onthisday.hiztoryxml.model;

import in.co.shuklarahul.onthisday.hiztoryxml.Event;
import in.co.shuklarahul.onthisday.hiztoryxml.Status;

/**
 * Created by trivalent on 18/02/18
 * for OnThisDay
 */

public interface BaseModel {
    String BIRTH = "Birth";
    String EVENT = "Event";
    String DEATH = "DEATH";

    public Event getEvent();
    public Status getStatus();
    public void setStatus(int code, String message);
    public void setEvent(String date, String content);
    public String getType();
}