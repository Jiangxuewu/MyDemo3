package com.bb_sz.auto.http;

import android.text.TextUtils;

import com.bb_sz.auto.manager.RunManager;
import com.bb_sz.lib.http.Http;
import com.bb_sz.lib.http.HttpEntry;
import com.bb_sz.lib.http.IHttpCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Api {
    public static final String GET_APP_INFO = "get_app_info.php";
    public static final String GET_BUILD_INFO = "get_build_info.php";
    public static final String GET_INSTANCE_STORE_INFO = "get_instance_store_info.php";
    public static final String GET_NET_WORK_INFO = "get_net_work_info.php";
    public static final String GET_SCREEN_INFO = "get_screen_info.php";
    public static final String GET_SIM_INFO = "get_sim_info.php";
    public static final String GET_WIFI_INFO = "get_wifi_info.php";
    public static final String GET_DEVICE_INFO = "get_device_info.php";
    private final String url = "http://www.bb-sz.com/SL/";
    private static final String TAG = "SkyAutoApi";
    private static Api mInstance;

    public static Api getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new Api();
            }
            return mInstance;
        }
    }

    private Api() {
    }

    public void getInfo(int id, String method, IHttpCallback callback) {
        if (id <= 0) return;
        HttpEntry entry = new HttpEntry();
        entry.setType(Http.GET);
        entry.setBaseUrl(url + method + "?_id=" + id + "&phoneName=" + RunManager.getInstance().getPhoneName());
        entry.setCallback(callback);
        Http.getInstance().request(entry);
    }

    public void saveInstall(int type, String pkg, IHttpCallback callback) {
        if (TextUtils.isEmpty(pkg)) return;
        if (pkg.startsWith("package:")) {
            pkg = pkg.replace("package:", "");
        }
        HttpEntry entry = new HttpEntry();
        entry.setType(Http.POST);
        entry.setBaseUrl(url + "add_install_info.php?");
        entry.setCallback(callback);

        Map<String, String> body = new HashMap<>();
        body.put("type", String.valueOf(type));
        body.put("pkg", pkg);
        body.put("phoneName", RunManager.getInstance().getPhoneName());
        entry.setBody(body);

        Http.getInstance().request(entry);
    }

    public void ipHenJu(HttpEntry entry){
        Http.getInstance().request(entry);
    }

    public void initMarkets(IHttpCallback callback) {
        HttpEntry entry = new HttpEntry();
        entry.setType(Http.GET);
        entry.setBaseUrl(url + "markets.php?" + "phoneName=" + RunManager.getInstance().getPhoneName());
        entry.setCallback(callback);
        Http.getInstance().request(entry);
    }

    public void getApp(IHttpCallback callback) {
        getApp(callback, 0);
    }

    public void getApp(IHttpCallback callback, int type) {
        HttpEntry entry = new HttpEntry();
        entry.setType(Http.GET);
        entry.setBaseUrl(url + "runApps.php?type=" + type + "&phoneName=" + RunManager.getInstance().getPhoneName());
        entry.setCallback(callback);
        Http.getInstance().request(entry);
    }

    public void getDataType(IHttpCallback callback){
        HttpEntry entry = new HttpEntry();
        entry.setType(Http.GET);
        entry.setBaseUrl(url + "dataType.php?phoneName=" + RunManager.getInstance().getPhoneName());
        entry.setCallback(callback);
        Http.getInstance().request(entry);
    }

    public void getIp(IHttpCallback callback) {
        HttpEntry entry = new HttpEntry();
        entry.setType(Http.GET);
        entry.setBaseUrl("http://www.bb-sz.com/ip.php?phoneName=" + RunManager.getInstance().getPhoneName());
        entry.setCallback(callback);
        Http.getInstance().request(entry);
    }

    public void getVpnInfo(IHttpCallback callback) {
        HttpEntry entry = new HttpEntry();
        entry.setType(Http.GET);
        entry.setBaseUrl(url + "vpnInfo.php?phoneName=" + RunManager.getInstance().getPhoneName());
        entry.setCallback(callback);
        Http.getInstance().request(entry);
    }
}
