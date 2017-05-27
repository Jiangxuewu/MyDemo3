package com.bb_sz.easyinfo.http;

import com.bb_sz.lib.http.BaseModel;
import com.bb_sz.lib.http.Http;
import com.bb_sz.lib.http.HttpEntry;

/**
 * Created by Administrator on 2017/4/10.
 */

public class Api {

    private final String url = "http://www.bb-sz.com/SL/";
    private static final String TAG = "SkyDeviceApi";
    private static Api mInstance;

    public static Api getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new Api();
            }
            return mInstance;
        }
    }

    public Api() {
    }

    public void request(BaseModel model) {
        if (null == model) return;
        HttpEntry entry = model.toHttpEntry();
        if (null == entry) return;
        if (entry.getType() == Http.GET) {
            entry.setBaseUrl(url + entry.getBaseUrl() + "?" + Http.mapToString(entry.getBody()));
            entry.setBody(null);
        } else
            entry.setBaseUrl(url + entry.getBaseUrl() + "?");
        Http.getInstance().request(entry);
    }
}
