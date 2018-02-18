package in.co.shuklarahul.onthisday.hiztoryxml.model;

import in.co.shuklarahul.onthisday.hiztoryxml.Event;
import in.co.shuklarahul.onthisday.hiztoryxml.Status;

/**
 * Created by trivalent on 18/02/18
 * for OnThisDay
 */

public class birth implements BaseModel {
    private Status mStatus;
    private Event mEvent;

    @Override
    public Event getEvent() {
        return mEvent;
    }

    @Override
    public Status getStatus() {
        return mStatus;
    }

    @Override
    public void setStatus(int code, String message) {
        mStatus = new Status();
        mStatus.code = code;
        mStatus.message = message;
    }

    @Override
    public void setEvent(String date, String content) {
        mEvent = new Event();
        mEvent.content = content;
        mEvent.date = date;
    }

    @Override
    public String getType() {
        return BIRTH;
    }
}
