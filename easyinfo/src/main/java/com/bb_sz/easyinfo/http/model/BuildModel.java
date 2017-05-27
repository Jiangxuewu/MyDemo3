package com.bb_sz.easyinfo.http.model;

import com.bb_sz.easyinfo.http.requstdata.BuildInfo;
import com.bb_sz.lib.http.BaseModel;
import com.bb_sz.lib.http.Http;
import com.bb_sz.lib.http.HttpEntry;
import com.bb_sz.lib.http.IHttpCallback;

/**
 * Created by Administrator on 2017/4/10.
 */

public class BuildModel extends BaseModel {
    private final IHttpCallback callback;
    private BuildInfo data;

    public BuildModel(IHttpCallback callback, BuildInfo data) {
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
        httpEntry.setBaseUrl("add_build_info.php");
        return httpEntry;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
