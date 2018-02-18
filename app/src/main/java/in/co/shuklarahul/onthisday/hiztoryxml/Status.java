package in.co.shuklarahul.onthisday.hiztoryxml;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Attribute;

/**
 * Created by trivalent on 18/02/18
 * for OnThisDay
 */

public class Status {
    @Attribute
    @SerializedName("@code")
    public int code;
    @Attribute
    @SerializedName("@message")
    public String message;
}
