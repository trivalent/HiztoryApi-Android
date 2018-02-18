package in.co.shuklarahul.onthisday;

import in.co.shuklarahul.onthisday.hiztoryxml.model.BaseModel;

/**
 * Created by trivalent on 18/02/18
 * for OnThisDay
 */

public interface OnThisDayDataAvailable {
    void onData(BaseModel data);
    void onFailed(Throwable t);
}
