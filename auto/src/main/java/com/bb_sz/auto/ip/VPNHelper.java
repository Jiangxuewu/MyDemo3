package com.bb_sz.auto.ip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.bb_sz.auto.cmd.CMD;
import com.bb_sz.auto.helper.check.CheckHelp;
import com.bb_sz.auto.manager.RunManager;
import com.bb_sz.lib.log.L;

/**
 * Created by Administrator on 2017/5/17.
 */

public class VPNHelper {

    private static final String TAG = VPNHelper.class.getSimpleName();
    private static final int CHECK_STATE_NONE = 0;
    private static final int CHECK_STATE_RUNING = 1;
    private static VPNHelper mInstance;
    private Activity mActivity;
    private Context mContext;
    private int checkState = CHECK_STATE_NONE;

    public static VPNHelper getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new VPNHelper();
            }
        }
        return mInstance;
    }

    private VPNHelper() {
    }

    public void init(Activity activity) {
        mActivity = activity;
    }

    public String name;
    public String userName;
    public String password;
    public String server;

    public static final int ACTION_TYPE_ADD_VPN = 0;
    public static final int ACTION_TYPE_LOGIN = 1;
    public static final int ACTION_TYPE_LOGOUT = 2;
    public static final int ACTION_TYPE_DEL_VPN = 3;
    public int actionType = ACTION_TYPE_LOGOUT;

    public static final int NONE = 0;
    public static final int EDIT_ING = 1;
    public static final int EDIT_DONE = 2;
    public static final int LOGIN_ING = 1;
    public static final int LOGIN_DONE = 2;
    public static final int LOGOUT_ING = 1;
    public static final int LOGOUT_DONE = 2;
    public static final int RUN_RUNING = 1;

    public int STATE_VPN_EDIT = EDIT_DONE;
    public int STATE_VPN_LOGIN = LOGIN_DONE;
    public int STATE_VPN_LOGOUT = LOGOUT_DONE;
    public int STATE_RUN = RUN_RUNING;

    public void addVpn(Context context, String name, String server) {
        L.d(TAG, "addVpn, name = " + name + ", server = " + server);
        this.name = name;
        this.mContext = context;
        this.server = server;
        actionType = ACTION_TYPE_ADD_VPN;
        STATE_VPN_EDIT = NONE;
        STATE_RUN = NONE;
        Intent vpnIntent = new Intent();
        vpnIntent.setAction("android.net.vpn.SETTINGS");
        mActivity.startActivity(vpnIntent);
        checkOther();
    }

    private void checkOther() {
        L.d(TAG, "checkOther, checkState = " + checkState + ", name = " + name);
        if (checkState != CHECK_STATE_NONE) {
            return;
        }
        checkState = CHECK_STATE_RUNING;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        String cur = CheckHelp.getInstance().getCurrentActivityName(mContext);
                        if ("com.android.settings.vpn2.VpnSettingsAddActivity".equals(cur) && (actionType == ACTION_TYPE_ADD_VPN)) {//add
                            if (RunManager.getInstance().getSelPhoneType() == RunManager.NX511J) {
                                CMD.doSuExec("input tap 955 152");
                                Thread.sleep(1000);
                                CMD.doSuExec("input keyevent 4");
                            }
                        } else if ("com.android.vpndialogs.ManageDialog".equals(cur) && (actionType == ACTION_TYPE_LOGOUT)) {//logout
                            if (RunManager.getInstance().getSelPhoneType() == RunManager.P780) {
                                CMD.doSuExec("input tap 520 880");
                            } else {
                                CMD.doSuExec("input tap 202 1269");
                            }
                            Thread.sleep(1000);
                            CMD.doSuExec("input keyevent 4");
                            Thread.sleep(1000);
                            CMD.doSuExec("input keyevent 4");
                        } else if ("com.kingroot.kinguser.advance.install.ui.SilentInstallDialogActivity".equals(cur)) {
                            String[] cmdss = {"input tap 222 1243", "input tap 736 1399"};
                            CMD.doSuExec(cmdss);
                        } else if ("com.android.settings.Settings$VpnSettingsActivity".equals(cur) && (actionType == ACTION_TYPE_LOGOUT)) {
                            CMD.doSuExec("input keyevent 4");
                            Thread.sleep(1000);
                            CMD.doSuExec("input keyevent 4");
                        }
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }).start();
    }

    public void login(String name, String userName, String password) {
        L.d(TAG, "login, name = " + name + ", userName = " + userName + ", password = " + password);
        this.name = name;
        this.userName = userName;
        this.password = password;
        actionType = ACTION_TYPE_LOGIN;
        STATE_VPN_LOGIN = NONE;
        Intent vpnIntent = new Intent();
        vpnIntent.setAction("android.net.vpn.SETTINGS");
        mActivity.startActivity(vpnIntent);
    }

    public void logout(String name) {
        L.d(TAG, "logout, name = " + name);
        this.name = name;
        actionType = ACTION_TYPE_LOGOUT;
        STATE_VPN_LOGOUT = NONE;
        Intent vpnIntent = new Intent();
        vpnIntent.setAction("android.net.vpn.SETTINGS");
        mActivity.startActivity(vpnIntent);
        checkOther();
    }

    public Activity getActivity() {
        return mActivity;
    }
}
