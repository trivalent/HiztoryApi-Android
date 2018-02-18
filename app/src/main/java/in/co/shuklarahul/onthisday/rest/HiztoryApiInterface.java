package in.co.shuklarahul.onthisday.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by trivalent on 18/02/18
 * for OnThisDay
 */

public interface HiztoryApiInterface {
    String BASE_URL = "http://api.hiztory.org/";

    @GET
    Call<ResponseBody> getRandomHistory(@Url String endPoint);
}
