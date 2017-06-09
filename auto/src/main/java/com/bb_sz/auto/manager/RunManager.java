package com.bb_sz.auto.manager;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.bb_sz.auto.cmd.CMD;
import com.bb_sz.auto.exception.RunException;
import com.bb_sz.auto.helper.sh.ShHelper;
import com.bb_sz.auto.helper.sh.ShRunCallback;
import com.bb_sz.auto.http.Api;
import com.bb_sz.auto.info.InfoManager;
import com.bb_sz.auto.ip.VPNHelper;
import com.bb_sz.auto.market.App;
import com.bb_sz.auto.market.DataType;
import com.bb_sz.auto.market.IP;
import com.bb_sz.auto.market.VpnInfo;
import com.bb_sz.auto.test.BuildInfo;
import com.bb_sz.auto.test.SysHelper;
import com.bb_sz.auto.thread.RunThread;
import com.bb_sz.easyinfo.http.requstdata.DeviceInfo;
import com.bb_sz.lib.http.HttpResponse;
import com.bb_sz.lib.http.HttpResponseBody;
import com.bb_sz.lib.http.IHttpCallback;
import com.bb_sz.lib.log.L;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/4/13.
 */

public class RunManager {

    /**
     * 本地数据运行
     */
    public static final int RUN_TYPE_LOCAL = 0;
    /**
     * 网络数据运行
     */
    public static final int RUN_TYPE_NET = 1;

    private static final String TAG = RunManager.class.getSimpleName();
    private static RunManager mInstance;
    private int type;

    public static final int HMNOTE1LTE = 0;
    public static final int Q505T = 1;
    public static final int P780 = 2;
    public static final int NX511J = 3;

    public static final int yyb = 0;
    public static final int qihoo = 1;
    public static final int runType = qihoo;


    private boolean isNeedVpn = true;

    public static RunManager getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new RunManager();
            }
            return mInstance;
        }
    }

    private RunManager() {
    }


    /**
     * 开始运行
     */
    public void start(final Context context) {
        L.d(TAG, "start() state = " + state);
        if (state == STATE_RUNING) return;
        state = STATE_RUNING;
        if (!isNeedVpn){
            initDeviceInfoTypeAndStart(context);
            return;
        }
        Api.getInstance().getVpnInfo(new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String str = body.getString();
                        if (!TextUtils.isEmpty(str)) {
                            VpnInfo vpnInfo = new Gson().fromJson(str, VpnInfo.class);
                            if (null != vpnInfo && null != vpnInfo.name) {
                                L.i(TAG, "start() vpnInfo : " + vpnInfo.toString());
                                SP.getInstance().setStringValue(Contants.VPN_USER_NAME, vpnInfo.userName);
                                SP.getInstance().setStringValue(Contants.VPN_PASSWORD, vpnInfo.password);
                                if (!vpnInfo.name.equals(getVpnName())) {
                                    SP.getInstance().setStringValue(Contants.VPN_NAME, vpnInfo.name);
                                    VPNHelper.getInstance().addVpn(context, vpnInfo.name, vpnInfo.server);
                                }
                            }
                        }
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000 * 10);
                                String[] cmds = {"am force-stop " + ShHelper.yybPkg,
                                        "pm clear " + ShHelper.yybPkg,
                                        "am force-stop " + ShHelper.qihooPkg,
                                        "pm clear " + ShHelper.qihooPkg
                                };
                                CMD.doSuExec(cmds);
                            } catch (InterruptedException ignored) {
                            }
                            checkVPNAndStart(context);
                        }
                    }).start();
                }
            }
        });

    }

    public void checkVPNAndStart(final Context context) {
        Api.getInstance().getIp(new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String str = body.getString();
                        if (!TextUtils.isEmpty(str)) {
                            L.d(TAG, "checkVPNAndStart() str = " + str);
                            IP ip = new Gson().fromJson(str, IP.class);
                            if (null != ip) {
                                if (vpnIsClose(ip.ip)) {
                                    try {
                                        Thread.sleep(1000 * 6);
                                    } catch (InterruptedException ignored) {
                                    }
                                    loginVpnAndStart(context);
                                    return;
                                } else {
                                    VPNHelper.getInstance().logout(getVpnName());
                                    try {
                                        Thread.sleep(1000 * 9);
                                    } catch (InterruptedException ignored) {
                                    }
                                    checkVPNAndStart(context);
                                    return;
                                }
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(1000 * 10);
                } catch (InterruptedException ignored) {
                }
                checkVPNAndStart(context);
            }
        });
    }

    private boolean vpnIsClose(String ip) {
        L.d(TAG, "vpnIsClose ip = " + ip);
        return "210.79.81.18".equals(ip);
    }

    private String curIp = "210.79.81.18";

    private boolean isNeedChangeIP(String ip) {
        L.d(TAG, "isNeedChangeIP ip = " + ip);
        boolean res = curIp.equals(ip);
        if (res) {
            return true;
        } else {
            curIp = ip;
            return false;
        }
    }

    public void loginVpnAndStart(final Context context) {
        L.d(TAG, "loginVpnAndStart() state = " + state);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //check ip
                VPNHelper.getInstance().login(getVpnName(), getUserName(), getPassword());
                try {
                    Thread.sleep(1000 * 10);
                } catch (InterruptedException ignored) {
                }
                checkIpAndStart(context);
            }
        }).start();
    }

    private void checkIpAndStart(final Context context) {
        Api.getInstance().getIp(new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String str = body.getString();
                        if (!TextUtils.isEmpty(str)) {
                            L.d(TAG, "checkIpAndStart str = " + str);
                            IP ip = new Gson().fromJson(str, IP.class);
                            if (null != ip) {
                                if (!isNeedChangeIP(ip.ip)) {
                                    try {
                                        Thread.sleep(1000 * 6);
                                    } catch (InterruptedException ignored) {
                                    }
                                    initDeviceInfoTypeAndStart(context);
                                    return;
                                } else {
                                    checkVPNAndStart(context);
                                    return;
                                }
                            }
                        }
                    }
                    return;
                }
                checkIpAndStart(context);
            }
        });
    }

    public void initDeviceInfoTypeAndStart(final Context context) {
        L.d(TAG, "initDeviceInfoAndStart() state = " + state);
        getDeviceInfoType(context, false, new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String str = body.getString();
                        if (!TextUtils.isEmpty(str)) {
                            DataType dataType = new Gson().fromJson(str, DataType.class);
                            if (null != dataType) {
                                boolean b = dataType.m_type == 1;
                                Setting.isRunNetData = b;
                                SP.getInstance().setBooleanValue(Contants.SETTING_IS_RUN_NET_DATA_KEY, b);
                                //
                                Setting.COUNT = dataType.m_start;
                                SP.getInstance().setIntValue(Contants.SETTING_COUNT_START, Setting.COUNT);
                                updateUI(context);
                                getDeviceInfo(context, dataType.m_type == 0 ? RUN_TYPE_LOCAL : RUN_TYPE_NET);
                                return;
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(10000);
                    start(context);
                } catch (InterruptedException ignored) {
                }
            }
        });
    }

    private String getPassword() {
        return SP.getInstance().getStringValue(Contants.VPN_PASSWORD);
    }

    private String getUserName() {
        return SP.getInstance().getStringValue(Contants.VPN_USER_NAME);
    }

    private String getVpnName() {
        return SP.getInstance().getStringValue(Contants.VPN_NAME);
    }

    public void initDeviceInfoType(final Context context) {
        getDeviceInfoType(context, true, new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String str = body.getString();
                        if (!TextUtils.isEmpty(str)) {
                            DataType dataType = new Gson().fromJson(str, DataType.class);
                            if (null != dataType) {
                                boolean b = dataType.m_type == 1;
                                Setting.isRunNetData = b;
                                SP.getInstance().setBooleanValue(Contants.SETTING_IS_RUN_NET_DATA_KEY, b);
                                //
                                Setting.COUNT = dataType.m_start;
                                SP.getInstance().setIntValue(Contants.SETTING_COUNT_START, Setting.COUNT);
                                updateUI(context);
                                return;
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(10000);
                    initDeviceInfoType(context);
                } catch (InterruptedException ignored) {
                }
            }
        });
    }


    /**
     * 从服务器获取设备使用的数据类型， 是使用服务器的设备信息，还是使用本地的设备信息。
     *
     * @param context
     */
    private void getDeviceInfoType(final Context context, final boolean test, IHttpCallback callback) {
        //switch ip
//        VPNHelper.getInstance().switchIP("湖南-郴州-电信1", "czz.8866.org");

        //get data type
        Api.getInstance().getDataType(callback);
    }

    private void updateUI(Context context) {
        context.sendBroadcast(new Intent(Contants.UPDATEUI));
    }

    public void getDeviceInfo(Context context, int type) {
        L.d(TAG, "getDeviceInfo() type = " + type);
        this.type = type;
        final int phoneType = getSelPhoneType();
        L.d(TAG, "getDeviceInfo() phoneType = " + phoneType);
        if (type == RUN_TYPE_LOCAL) {
            getLocalDeviceInfo(context, phoneType);
        } else if (type == RUN_TYPE_NET) {
            getNetDeviceInfo(context, phoneType);
        } else {
            throw new RunException("not type");
        }
    }

    private synchronized void getNetDeviceInfo(final Context context, final int phoneType) {
        L.d(TAG, "run(), runNet start get net info...");
        InfoManager.getInstance().updateInfo(IDManager.getInstance().getID(), new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                L.d(TAG, "run(), runNet result is " + (httpResponse == null ? "null" : httpResponse.code));
                if (null != httpResponse && httpResponse.code == 200) {
//                    int id = IDManager.getInstance().getID();
//                    if (id % 10 == 0) {
//                        new HenJuIP().reCall();
//                    }
                    IDManager.getInstance().setID();
                    HttpResponseBody body = httpResponse.getBody();
                    if (null != body) {
                        String json = body.getString();
                        if (!TextUtils.isEmpty(json)) {
                            DeviceInfo info = new Gson().fromJson(json, DeviceInfo.class);
//                            IDManager.getInstance().setID(Integer.valueOf(info.get_id()));
                            if (phoneType == RunManager.P780) {
//                                if ("19".equals(info.getApi())) {
                                    readyRun(context, info);
//                                } else {
//                                    try {
//                                        Thread.sleep(10000);
//                                    } catch (Exception ignored) {
//                                    }
//                                    getNetDeviceInfo(context, phoneType);
//                                }
                            } else if (phoneType == RunManager.HMNOTE1LTE) {
//                                String name = getPhoneName();
//                                if ("19".equals(info.getApi()) || "20".equals(info.getApi()) || "20".equals(info.getApi())) {
                                    readyRun(context, info);
//                                } else {
//                                    try {
//                                        Thread.sleep(10000);
//                                    } catch (Exception ignored) {
//                                    }
//                                    getNetDeviceInfo(context, phoneType);
//                                }
                            } else if (phoneType == RunManager.NX511J) {
//                                if ("21".equals(info.getApi()) || "22".equals(info.getApi())) {
                                    readyRun(context, info);
//                                } else {
//                                    try {
//                                        Thread.sleep(10000);
//                                    } catch (Exception ignored) {
//                                    }
//                                    getNetDeviceInfo(context, phoneType);
//                                }
                            } else {
                                readyRun(context, info);
                            }
                            return;
                        }
                    }
                }
                try {
                    Thread.sleep(10000);
                } catch (Exception ignored) {
                }
                getNetDeviceInfo(context, phoneType);
            }
        });
    }

    private void getLocalDeviceInfo(Context context, int phoneType) {
        SysHelper s = new SysHelper(null);
        DeviceInfo info = new DeviceInfo();
        info.setId(s.getBuildInfo().getId());
        info.setModel(s.getBuildInfo().getModel());
        info.setSerial(s.getBuildInfo().getSerial());
        info.setVersion(s.getBuildInfo().getVersion());
        info.setApi(s.getBuildInfo().getApi());
        info.setManufacturer(s.getBuildInfo().getManufacturer());
        info.setBrand(s.getBuildInfo().getBrand());
        info.setProduct(s.getBuildInfo().getProduct());
        info.setDevice(s.getBuildInfo().getDevice());
        info.setBoard(s.getBuildInfo().getBoard());
        info.setHardware(s.getBuildInfo().getHardware());
        info.setCpuabi(s.getBuildInfo().getCpuabi());
        info.setCpuabi2(s.getBuildInfo().getCpuabi2());
        info.setAndroid_id(s.getBuildInfo().getAndroid_id());

        info.setImei(s.getBuildInfo().getImei());

        info.setWidth(s.getScreenInfo().getWidth());
        info.setHeight(s.getScreenInfo().getHeight());
        info.setDensity(s.getScreenInfo().getDensity());
        info.setDpi(s.getScreenInfo().getDensityDpi());

        info.setFirst_install_time(s.getAppInfo().getFirst_install_time() + "");


        info.setNet_extrainfo(s.getNetWorkInfo().getNet_extrainfo());
        info.setNet_subtype(s.getNetWorkInfo().getNet_subtype());
        info.setNet_subtype_name(s.getNetWorkInfo().getNet_subtype_name());
        info.setNet_type(s.getNetWorkInfo().getNet_type());
        info.setNet_type_name(s.getNetWorkInfo().getNet_type_name());


        info.setSim_country_iso(s.getSimInfo().getSim_country_iso());
        info.setSim_operator(s.getSimInfo().getSim_operator());
        info.setSim_operator_name(s.getSimInfo().getSim_operator_name());
        info.setSubscriber_id(s.getSimInfo().getSubscriber_id());
        info.setSim_serial_number(s.getSimInfo().getSim_serial_number());
        info.setSim_state(s.getSimInfo().getSim_state());
        info.setLine1_number(s.getSimInfo().getLine1_number());
        info.setNetwork_country_iso(s.getSimInfo().getNetwork_country_iso());
        info.setNetwork_operator(s.getSimInfo().getNetwork_operator());
        info.setNetwork_operator_name(s.getSimInfo().getNetwork_operator_name());
        info.setNetwork_type(s.getSimInfo().getNetwork_type());

        info.setIp(s.getWifiInfo().getIp());
        info.setMac(s.getWifiInfo().getMac());
        info.setSsid(s.getWifiInfo().getSsid());
        int api;
        if (phoneType == RunManager.P780) {
            api = 19;
            info.setApi("" + api);
            info.setVersion(BuildInfo.getSDKVersion(api));
        } else if (phoneType == RunManager.HMNOTE1LTE) {
            api = 21 + new Random().nextInt(3);
            info.setApi("" + api);
            info.setVersion(BuildInfo.getSDKVersion(api));
        } else if (phoneType == RunManager.NX511J) {
            api = 21 + new Random().nextInt(2);
            info.setApi("" + api);
            info.setVersion(BuildInfo.getSDKVersion(api));
        }

        L.i(TAG, "local info:" + info.toString());
        readyRun(context, info);
    }

    private void readyRun(Context context, DeviceInfo info) {
        //update device info
        InfoManager.getInstance().saveInfo(info);
        //run times++
        SP.getInstance().RunTimesAdd();
        //start run
        run(context);
    }

    private static final int STATE_NONE = 0;
    private static final int STATE_RUNING = 1;
    private int state = STATE_NONE;

    private void run(Context context) {
        L.d(TAG, "run.... really run.start.");
        continueRun(context);
    }


    public void stop() {
        state = STATE_NONE;
        SP.getInstance().stop();
    }

    public void start() {
        SP.getInstance().start();
    }

    private void continueRun(Context context) {
        boolean runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
        L.i(TAG, "continueRun()......runFlag = " + runFlag);
        if (runFlag) {
            readyForStart(context);
        }
    }

    private void readyForStart(final Context context) {
        L.i(TAG, "readyForStart()....");
        RunThread.run(new Runnable() {
            @Override
            public void run() {
                L.i(TAG, "readyForStart()....start getApp.");
                Api.getInstance().getApp(new IHttpCallback() {
                    @Override
                    public void result(HttpResponse httpResponse) {
                        if (null != httpResponse && httpResponse.code == 200) {
                            HttpResponseBody body = httpResponse.getBody();
                            if (null != body) {
                                String json = body.getString();
                                if (!TextUtils.isEmpty(json)) {
                                    App app = new Gson().fromJson(json, App.class);
                                    if (null != app) {
                                        L.i(TAG, "readyForStart()....start getApp. success..");
                                        int phoneType = getSelPhoneType();
                                        if (phoneType == Q505T) {
                                            _runLast(context, app);
                                        } else if (phoneType == P780) {
                                            runLast(context, app);
                                        } else {
                                            runLast(context, app);
                                        }
                                        return;
                                    }
                                }
                            }
                        }
                        L.i(TAG, "readyForStart()....start getApp. failed..");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        readyForStart(context);
                    }
                }, runType == yyb ? 1 : 0);
            }
        });
    }

    //Q505T
    private void _runLast(final Context context, App app) {
        L.i(TAG, "runLast()....app is " + app.toString());
        ShHelper.getInstance().searchShFile_ZTE_Q505T(context, new ShRunCallback() {
            @Override
            public void result(int code, String msg) {
                state = STATE_NONE;
                if (code == 0) {
                    L.e(TAG, "sh result:" + msg);
                    start(context);
                } else {
                    boolean runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
                    L.e(TAG, "sh result:" + msg + ", runFlag = " + runFlag);
                    if (runFlag) {
                        RunManager.getInstance().reset();
                        CMD.doSuExec("reboot");
                    }
                }
            }
        }, app);
    }

    //HM Note 1 LTE
    private synchronized void runLast(final Context context, App app) {
        L.i(TAG, "runLast()....app is " + app.toString());
        ShHelper.getInstance().searchShFile_HM_NOTE(context, new ShRunCallback() {
            @Override
            public void result(int code, String msg) {
                state = STATE_NONE;
                if (code == 0) {
                    L.e(TAG, "sh result:" + msg);
                    start(context);
                } else if (code == 1){
                    L.e(TAG, "sh result:" + msg);
                    start(context);
                } else {
                    boolean runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
                    L.e(TAG, "sh result:" + msg + ", runFlag = " + runFlag);
                    if (runFlag) {
                        RunManager.getInstance().reset();
                        CMD.doSuExec("reboot");
                    }
                }
            }
        }, app);
    }

    public void reset() {
        InfoManager.getInstance().updateInfo(0, null);
    }


    public void uploadToServer(Context context, String packageName, int type) {
        Api.getInstance().saveInstall(type, packageName, new IHttpCallback() {
            @Override
            public void result(HttpResponse httpResponse) {
                if (null != httpResponse && httpResponse.code == 200) {
                    //success
                } else {
                    //failed
                }
            }
        });
    }


    public List<String> getPhoneType() {
        List<String> list = new ArrayList<>();
        list.add("HM NOTE 1LTE 0");
        list.add("HM NOTE 1LTE 1");
        list.add("HM NOTE 1LTE 2");
        list.add("HM NOTE 1LTE 3");
        list.add("HM NOTE 1LTE 4");
        list.add("ZTE Q505T");
        list.add("Lenovo P780");
        list.add("Nubia NX511J");
        list.add("Nubia NX511J 1");
        list.add("Nubia NX511J 2");
        list.add("Nubia NX511J 3");
        list.add("Nubia NX511J 4");
        return list;
    }

    public int getSelPhoneType() {
        String name = getPhoneName();
        if (null != name && name.startsWith("HM NOTE 1LTE")) {
            return HMNOTE1LTE;
        } else if (null != name && name.startsWith("ZTE Q505T")) {
            return Q505T;
        } else if (null != name && name.startsWith("Lenovo P780")) {
            return P780;
        } else if (null != name && name.startsWith("Nubia NX511J")) {
            return NX511J;
        }
        return HMNOTE1LTE;
    }

    public String getPhoneName() {
        return getPhoneType().get(SP.getInstance().getIntValue(Contants.KEY_PHONE_TYPE_SELECTED));
    }
}
