package in.co.shuklarahul.onthisday.hiztoryxml;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;

/**
 * Created by trivalent on 18/02/18
 * for OnThisDay
 */

public class Event {
    @Attribute
    @SerializedName("@date")
    public String date;
    @Attribute
    @SerializedName("@content")
    public String content;
}
