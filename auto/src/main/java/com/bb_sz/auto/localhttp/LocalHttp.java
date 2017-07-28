package com.bb_sz.auto.localhttp;

import android.text.TextUtils;

import com.bb_sz.auto.cmd.CMD;
import com.bb_sz.lib.log.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/28.
 */

public class LocalHttp {
    private static final String TAG = LocalHttp.class.getSimpleName();
    private static final String HTTP_LOCAL_HOST = "/sdcard/TM";
    private static LocalHttp mInstance;

    public static LocalHttp getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new LocalHttp();
            }
            return mInstance;
        }
    }

    private LocalHttp() {
    }

    /**
     * @param url http://10.150.15.143/170714/3c6e924cb4b0446bd5a548464d151688/com.think.game.qh_302.apk
     */
    public void init(String url) {
        if (TextUtils.isEmpty(url)) return;
        if (!url.startsWith("http://") || !url.endsWith(".apk")) return;
        String localPath = getLocalPath(url);
        if (new File(localPath).exists()) return;
        try {
            FileUtils.createFile(localPath);
        } catch (IOException ignored) {
        }
        CMD.doSuExec("wget " + url + " > " + localPath);
    }

    public boolean exists(String url) {
        if (TextUtils.isEmpty(url)) return false;
        if (!url.startsWith("http://") || !url.endsWith(".apk")) return false;
        String localPath = getLocalPath(url);
        return new File(localPath).exists();
    }

    private String getLocalPath(String url) {
        url = url.substring("http://".length());
        String[] fileStr = url.split("/");
        StringBuilder result = new StringBuilder(HTTP_LOCAL_HOST);
        int length = fileStr.length;
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                result.append("/").append(fileStr[i]);
            }
        }
        return result.toString();
    }
}
