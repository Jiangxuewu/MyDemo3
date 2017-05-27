package com.bb_sz.auto.info;

import android.text.TextUtils;

import com.bb_sz.auto.cmd.CMD;
import com.bb_sz.auto.http.Api;
import com.bb_sz.auto.util.FileUtil;
import com.bb_sz.easyinfo.http.requstdata.DeviceInfo;
import com.bb_sz.lib.http.HttpResponse;
import com.bb_sz.lib.http.HttpResponseBody;
import com.bb_sz.lib.http.IHttpCallback;
import com.bb_sz.lib.log.L;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/4/12.
 */

public class InfoManager {
    public static final String FILE_PATH = "/data/local/tmp/test.prop";
    public static final String FILE_PATH_RESET = "/data/local/tmp/reset.prop";
    private static final String TAG = "SkyInfo";
    private static InfoManager mInstance;

    public static InfoManager getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new InfoManager();
            }
            return mInstance;
        }
    }

    private InfoManager() {
    }

    @Deprecated
    public void updateInfo(int id) {
        updateInfo(id, new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String json = body.getString();
                        if (!TextUtils.isEmpty(json)) {
                            DeviceInfo infos = new Gson().fromJson(json, DeviceInfo.class);
                            saveInfo(infos);
                        }
                    }
                }
            }
        });
    }

    public void updateInfo(int id, IHttpCallback callback) {
        if (id == 0) {
            CMD.doSuExec("cat " + FILE_PATH_RESET + " > " + FILE_PATH);
            return;
        }
        Api.getInstance().getInfo(id, Api.GET_DEVICE_INFO, callback);
    }

    public void saveInfo(DeviceInfo info) {
        if (null == info) return;
        FileUtil.writeContentToFile(info.toString(), FILE_PATH);
        L.e(TAG, "save info over");
    }
}
