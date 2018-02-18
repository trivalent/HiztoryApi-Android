package in.co.shuklarahul.onthisday;

import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import in.co.shuklarahul.onthisday.hiztoryxml.model.BaseModel;
import in.co.shuklarahul.onthisday.hiztoryxml.model.birth;
import in.co.shuklarahul.onthisday.hiztoryxml.model.death;
import in.co.shuklarahul.onthisday.hiztoryxml.model.event;
import in.co.shuklarahul.onthisday.rest.HiztoryApiInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by trivalent on 18/02/18
 * for OnThisDay
 */

public class OnThisDay implements Callback<ResponseBody> {

    private static OnThisDay mInstance;
    private HiztoryApiInterface hiztoryApiInterface;
    private XmlPullParser xmlPullParser;
    private OnThisDayDataAvailable callback;
    public static OnThisDay getInstance() {
        if(mInstance == null) {
            mInstance = new OnThisDay();
            try {
                mInstance.init();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        return mInstance;
    }

    private void init() throws XmlPullParserException {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(HiztoryApiInterface.BASE_URL)
                .build();
        hiztoryApiInterface = mRetrofit.create(HiztoryApiInterface.class);
        xmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
    }

    public void getRandomBirth(OnThisDayDataAvailable callback) {
        Call<ResponseBody> call = hiztoryApiInterface
                .getRandomHistory("/random/birth.xml");
        this.callback = callback;
        call.enqueue(this);
    }

    public void getRandomEvent(final OnThisDayDataAvailable callback) {
        Call<ResponseBody> call = hiztoryApiInterface.getRandomHistory("random/event.xml");
        this.callback = callback;
        call.enqueue(this);
    }

    public void getRandomDeath(final OnThisDayDataAvailable callback) {
        Call<ResponseBody> call = hiztoryApiInterface.getRandomHistory("random/death.xml");
        this.callback = callback;
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        String data = null;
        try {
            data = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(data == null) {
            callback.onFailed(new IllegalArgumentException("No data received"));
            return;
        }
        BaseModel model =  null;
        InputStream stream = new ByteArrayInputStream(data.getBytes());
        try {
            xmlPullParser.setInput(stream, null);
            int eventType = xmlPullParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if("birth".equals(tagName)) {
                            model = new birth();
                        }else if(model == null && "event".equals(tagName)) {
                            model = new event();
                        }else if("death".equals(tagName)) {
                            model = new death();
                        }else if("status".equals(tagName)) {
                            String code = xmlPullParser.getAttributeValue(0);
                            String message = xmlPullParser.getAttributeValue(1);
                            model.setStatus(Integer.valueOf(code), message);
                        }else if("event".equals(tagName)) {
                            String date = xmlPullParser.getAttributeValue(0);
                            String content = xmlPullParser.getAttributeValue(1);
                            model.setEvent(date, content);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
            callback.onData(model);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        callback.onFailed(t);
    }
}
