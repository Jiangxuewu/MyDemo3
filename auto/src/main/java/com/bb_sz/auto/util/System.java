package com.bb_sz.auto.util;

import android.text.TextUtils;

import com.bb_sz.auto.http.Api;
import com.bb_sz.auto.http.response.AppInfo;
import com.bb_sz.auto.http.response.InstanceStoreInfo;
import com.bb_sz.lib.http.HttpResponse;
import com.bb_sz.lib.http.HttpResponseBody;
import com.bb_sz.lib.http.IHttpCallback;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/4/11.
 */

public class System {

    public void init(int id) {
        Api.getInstance().getInfo(id, Api.GET_INSTANCE_STORE_INFO, new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String json = body.getString();
                        if (!TextUtils.isEmpty(json)) {
                            InstanceStoreInfo storeInfo = new Gson().fromJson(json, InstanceStoreInfo.class);
                            if (null != storeInfo) {
                                Api.getInstance().getInfo(storeInfo.getApp_id(), Api.GET_APP_INFO, new IHttpCallback() {
                                    @Override
                                    public void result(HttpResponse httpResponse) {
                                        if (null != httpResponse && httpResponse.code == 200) {
                                            HttpResponseBody body = httpResponse.getBody();
                                            if (null != body) {
                                                String json = body.getString();
                                                if (!TextUtils.isEmpty(json)) {
                                                    AppInfo appInfo = new Gson().fromJson(json, AppInfo.class);
                                                }
                                            }
                                        }
                                    }
                                });
                                Api.getInstance().getInfo(storeInfo.getBuild_id(), Api.GET_BUILD_INFO, new IHttpCallback() {
                                    @Override
                                    public void result(HttpResponse httpResponse) {

                                    }
                                });
                                Api.getInstance().getInfo(storeInfo.getNet_work_id(), Api.GET_NET_WORK_INFO, new IHttpCallback() {
                                    @Override
                                    public void result(HttpResponse httpResponse) {

                                    }
                                });
                                Api.getInstance().getInfo(storeInfo.getScreen_id(), Api.GET_SCREEN_INFO, new IHttpCallback() {
                                    @Override
                                    public void result(HttpResponse httpResponse) {

                                    }
                                });
                                Api.getInstance().getInfo(storeInfo.getSim_id(), Api.GET_SIM_INFO, new IHttpCallback() {
                                    @Override
                                    public void result(HttpResponse httpResponse) {

                                    }
                                });
                                Api.getInstance().getInfo(storeInfo.getWifi_id(), Api.GET_WIFI_INFO, new IHttpCallback() {
                                    @Override
                                    public void result(HttpResponse httpResponse) {

                                    }
                                });
                            }
                        }
                    }
                }
            }
        });


    }
}
