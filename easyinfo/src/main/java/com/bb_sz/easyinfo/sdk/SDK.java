package com.bb_sz.easyinfo.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.bb_sz.easyinfo.http.Api;
import com.bb_sz.easyinfo.http.model.AppModel;
import com.bb_sz.easyinfo.http.model.BuildModel;
import com.bb_sz.easyinfo.http.model.DeviceModel;
import com.bb_sz.easyinfo.http.model.InstanceStoreModel;
import com.bb_sz.easyinfo.http.model.NetWorkModel;
import com.bb_sz.easyinfo.http.model.ScreenModel;
import com.bb_sz.easyinfo.http.model.SimModel;
import com.bb_sz.easyinfo.http.model.WifiModel;
import com.bb_sz.easyinfo.http.requstdata.AppInfo;
import com.bb_sz.easyinfo.http.requstdata.BuildInfo;
import com.bb_sz.easyinfo.http.requstdata.DeviceInfo;
import com.bb_sz.easyinfo.http.requstdata.InstanceStoreInfo;
import com.bb_sz.easyinfo.http.requstdata.NetWorkInfo;
import com.bb_sz.easyinfo.http.requstdata.ScreenInfo;
import com.bb_sz.easyinfo.http.requstdata.SimInfo;
import com.bb_sz.easyinfo.http.requstdata.WifiInfo;
import com.bb_sz.easyinfo.http.response.CommResponse;
import com.bb_sz.lib.device.DeviceConfig;
import com.bb_sz.lib.http.HttpResponse;
import com.bb_sz.lib.http.HttpResponseBody;
import com.bb_sz.lib.http.IHttpCallback;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/4/11.
 */
public class SDK {

    private static final String TAG = "SDK";
    private static SDK mInstance;
    private Activity context;
    private AppModel appModel;
    private BuildModel buildModel;
    private NetWorkModel netWorkModel;
    private ScreenModel screenModel;
    private SimModel simModel;
    private WifiModel wifiModel;
    private DeviceModel deviceModel;
    private CommResponse appResponse;
    private CommResponse buildResponse;
    private CommResponse netWorkResponse;//当前网络信息 wifi or 3g
    private CommResponse screenResponse;
    private CommResponse simResponse;
    private CommResponse wifiResponse;
    private DeviceInfo deviceInfo;

    public static SDK getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new SDK();
            }
            return mInstance;
        }
    }

    public void init(Activity context) {
        this.context = context;
        initDeviceInfos();
    }

    public static void main(String[] arg){

    }

    private void initDeviceInfos() {
        deviceInfo = new DeviceInfo();
        //AppModel
        initApp();

        //BuildModel
        initBuild();

        //NetWorkModel
        initNetWork();
        //ScreenModel
        initScreenModel();
        //SimModel
        initSimModel();
        //WifiModel
        initWifiModel();

        initDeviceModel();
        //show tip
//        upload();
    }

    private void initDeviceModel() {
        IHttpCallback callback = new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
//                if (null != httpResponse && httpResponse.code == 200) {
//                    HttpResponseBody body = httpResponse.getBody();
//                    if (null != body) {
//                        String json = body.getString();
//                        if (!TextUtils.isEmpty(json)) {
//                        }
//                    }
//                }
            }
        };
        deviceModel = new DeviceModel(callback, deviceInfo);
    }

    private void initApp() {
        IHttpCallback callback = new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String json = body.getString();
                        if (!TextUtils.isEmpty(json)) {
                            appResponse = new Gson().fromJson(json, CommResponse.class);
                            uploadResult();
                        }
                    }
                }
            }
        };
        AppInfo appInfo = new AppInfo();
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        appInfo.setFirst_install_time("" + info.firstInstallTime);
        deviceInfo.setFirst_install_time("" + info.firstInstallTime);
        appModel = new AppModel(callback, appInfo);
    }

    private void uploadResult() {
        if (null != appResponse && null != buildResponse && null != screenResponse && null != simResponse) {
            if (null != wifiResponse || null != netWorkResponse) {
                saveInstanceStore();
            }
        }
    }

    private void saveInstanceStore() {
        InstanceStoreInfo data = new InstanceStoreInfo();
        InstanceStoreModel model = new InstanceStoreModel(new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {

            }
        }, data);
        data.setApp_id(appResponse.get_id());
        data.setBuild_id(buildResponse.get_id());
        data.setScreen_id(screenResponse.get_id());
        data.setSim_id(simResponse.get_id());
        if (null != wifiResponse) {
            data.setWifi_id(wifiResponse.get_id());
        }
        if (null != netWorkResponse) {
            data.setNet_work_id(netWorkResponse.get_id());
        }
        Api.getInstance().request(model);
    }

    private void initBuild() {
        IHttpCallback callback = new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String json = body.getString();
                        if (!TextUtils.isEmpty(json)) {
                            buildResponse = new Gson().fromJson(json, CommResponse.class);
                            uploadResult();
                        }
                    }
                }
            }
        };
        BuildInfo data = new BuildInfo();
        data.init();
        deviceInfo.init();
        String android_id = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        data.setAndroid_id(android_id);
        deviceInfo.setAndroid_id(android_id);
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null != tm){
            data.setImei(tm.getDeviceId());
            deviceInfo.setImei(data.getImei());
        }

        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）

        data.setWidth(width);
        data.setHeight(height);
        data.setDpi(densityDpi);
        data.setDensity(density);

        deviceInfo.setWidth(width);
        deviceInfo.setHeight(height);
        deviceInfo.setDpi(densityDpi);
        deviceInfo.setDensity(density);

        buildModel = new BuildModel(callback, data);
    }

    private void initWifiModel() {
        IHttpCallback callback = new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String json = body.getString();
                        if (!TextUtils.isEmpty(json)) {
                            wifiResponse = new Gson().fromJson(json, CommResponse.class);
                            uploadResult();
                        }
                    }
                }
            }
        };
        WifiInfo data = new WifiInfo();
        DeviceConfig.getInstance().init(context);
        data.setMac(DeviceConfig.getInstance().getMac());
        data.setIp(DeviceConfig.getInstance().getIP());
        data.setSsid(DeviceConfig.getInstance().getSSID());
        deviceInfo.setMac(DeviceConfig.getInstance().getMac());
        deviceInfo.setIp(DeviceConfig.getInstance().getIP());
        deviceInfo.setSsid(DeviceConfig.getInstance().getSSID());
        wifiModel = new WifiModel(callback, data);

    }

    private void initSimModel() {
        IHttpCallback callback = new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String json = body.getString();
                        if (!TextUtils.isEmpty(json)) {
                            simResponse = new Gson().fromJson(json, CommResponse.class);
                            uploadResult();
                        }
                    }
                }
            }
        };
        SimInfo data = new SimInfo();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null != tm) {
            data.setSim_country_iso(tm.getSimCountryIso());
            data.setSim_operator(tm.getSimOperator());
            data.setSim_operator_name(tm.getSimOperatorName());
            data.setSim_state("" + tm.getSimState());
            data.setSim_serial_number(tm.getSimSerialNumber());
            data.setSubscriber_id(tm.getSubscriberId());
            data.setLine1_number(tm.getLine1Number());
            data.setNetwork_country_iso(tm.getNetworkCountryIso());
            data.setNetwork_operator(tm.getNetworkOperator());
            data.setNetwork_operator_name(tm.getNetworkOperatorName());
            data.setNetwork_type(tm.getNetworkType());

            deviceInfo.setSim_country_iso(tm.getSimCountryIso());
            deviceInfo.setSim_operator(tm.getSimOperator());
            deviceInfo.setSim_operator_name(tm.getSimOperatorName());
            deviceInfo.setSim_state("" + tm.getSimState());
            deviceInfo.setSim_serial_number(tm.getSimSerialNumber());
            deviceInfo.setSubscriber_id(tm.getSubscriberId());
            deviceInfo.setLine1_number(tm.getLine1Number());
            deviceInfo.setNetwork_country_iso(tm.getNetworkCountryIso());
            deviceInfo.setNetwork_operator(tm.getNetworkOperator());
            deviceInfo.setNetwork_operator_name(tm.getNetworkOperatorName());
            deviceInfo.setNetwork_type(tm.getNetworkType());
        }

        simModel = new SimModel(callback, data);
    }

    @Deprecated
    private void initScreenModel() {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）

        IHttpCallback callback = new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String json = body.getString();
                        if (!TextUtils.isEmpty(json)) {
                            screenResponse = new Gson().fromJson(json, CommResponse.class);
                            uploadResult();
                        }
                    }
                }
            }
        };
        ScreenInfo data = new ScreenInfo();
        data.setWidth(width);
        data.setHeight(height);
        data.setDpi(densityDpi);
        data.setDensity(density);
        screenModel = new ScreenModel(callback, data);
    }

    private void initNetWork() {
        IHttpCallback callback = new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {

                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String json = body.getString();
                        if (!TextUtils.isEmpty(json)) {
                            netWorkResponse = new Gson().fromJson(json, CommResponse.class);
                            uploadResult();
                        }
                    }
                }
            }
        };
        NetWorkInfo data = new NetWorkInfo();
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (null != mNetworkInfo) {
            data.setNet_extrainfo(mNetworkInfo.getExtraInfo());
            data.setNet_subtype(mNetworkInfo.getSubtype());
            data.setNet_subtype_name(mNetworkInfo.getSubtypeName());
            data.setNet_type(mNetworkInfo.getType());
            data.setNet_type_name(mNetworkInfo.getTypeName());

            deviceInfo.setNet_extrainfo(mNetworkInfo.getExtraInfo());
            deviceInfo.setNet_subtype(mNetworkInfo.getSubtype());
            deviceInfo.setNet_subtype_name(mNetworkInfo.getSubtypeName());
            deviceInfo.setNet_type(mNetworkInfo.getType());
            deviceInfo.setNet_type_name(mNetworkInfo.getTypeName());
        }
        netWorkModel = new NetWorkModel(callback, data);
    }

    private void upload() {
        Api.getInstance().request(deviceModel);

//        Api.getInstance().request(appModel);
//        Api.getInstance().request(buildModel);
//        Api.getInstance().request(screenModel);
//        Api.getInstance().request(simModel);
//        String[] mode = DeviceConfig.getInstance().getNetworkAccessMode();
//        if (null != mode && "Wi-Fi".equals(mode[0])) {
//            //wifi
//            Api.getInstance().request(wifiModel);
//        } else {
//            //3g
//            Api.getInstance().request(netWorkModel);
//        }
    }

    @Override
    public String toString() {
        return appModel.toString()// + "\n"
                + buildModel.toString()// + "\n"
                + netWorkModel.toString()// + "\n"
//                + screenModel.toString() + "\n"
                + simModel.toString()// + "\n"
                + wifiModel.toString();
    }
}
