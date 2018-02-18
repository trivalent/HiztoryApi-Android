package in.co.shuklarahul.onthisday;

import android.util.Log;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import in.co.shuklarahul.onthisday.hiztoryxml.model.BaseModel;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    CountDownLatch lock = new CountDownLatch(1);
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        OnThisDay thisDay = OnThisDay.getInstance();
        thisDay.getRandomBirth(new OnThisDayDataAvailable() {
            @Override
            public void onData(BaseModel data) {
                //Log.e("Result -> ", data);
                System.out.println("result = " + data.getEvent().content);
                lock.countDown();
            }

            @Override
            public void onFailed(Throwable t) {
                System.out.println(t.getMessage());
                lock.countDown();
            }
        });
        lock.await(10000, TimeUnit.SECONDS);
    }
}