package com.bb_sz.easyinfo.http.model;

import com.bb_sz.easyinfo.http.requstdata.DeviceInfo;
import com.bb_sz.lib.http.BaseModel;
import com.bb_sz.lib.http.Http;
import com.bb_sz.lib.http.HttpEntry;
import com.bb_sz.lib.http.IHttpCallback;

/**
 * Created by Administrator on 2017/4/11.
 */

public class DeviceModel extends BaseModel {
    private final IHttpCallback callback;
    private DeviceInfo data;

    public DeviceModel(IHttpCallback callback, DeviceInfo data) {
        this.callback = callback;
        this.data = data;
    }

    public IHttpCallback getCallback() {
        return callback;
    }

    @Override
    public HttpEntry toHttpEntry() {
        HttpEntry httpEntry = new HttpEntry();
        httpEntry.setCallback(callback);
        httpEntry.setType(Http.POST);
        httpEntry.setBody(toMap(data));
        httpEntry.setBaseUrl("add_device_info.php");
        return httpEntry;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
