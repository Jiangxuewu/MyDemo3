package com.bb_sz.auto.accessibility;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bb_sz.auto.cmd.CMD;
import com.bb_sz.auto.ip.VPNHelper;
import com.bb_sz.auto.manager.RunManager;
import com.bb_sz.lib.log.L;

import java.util.List;


/**
 * Created by Administrator on 2017/5/12.
 */

public class AccessibilityService extends android.accessibilityservice.AccessibilityService {
    private static final String TAG = "AutoAccess";
    private static final String ACT_ADD_SET = "com.android.settings/.Settings$VpnSettingsActivity";
    private static final String ACT_ADD_SET2 = "com.android.settings.vpn2.VpnDialog";

    private final boolean debug = true;

    private String currentActivityName;


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        watchList(event);
    }

    private void watchList(AccessibilityEvent event) {
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED://32
                L.d(TAG, "watchList,...PackageName is " + event.getPackageName().toString());
                L.d(TAG, "watchList,...ClassName is " + event.getClassName().toString());
                ComponentName componentName = new ComponentName(event.getPackageName().toString(), event.getClassName().toString());
                try {
                    getPackageManager().getActivityInfo(componentName, 0);
                    currentActivityName = componentName.flattenToShortString();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    currentActivityName = event.getClassName().toString();
                }
                L.d(TAG, "watchList,...currentActivityName is " + currentActivityName);
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED://2048
//                AccessibilityNodeInfo eventSource = event.getSource();
//                if (null == eventSource) break;
//                List<AccessibilityNodeInfo> list = eventSource.findAccessibilityNodeInfosByText("停止运行");
//                L.d(TAG, "watchList, list size is " + (null == list ? "null" : list.size()));
//                if (null == list || list.isEmpty()) break;

//                if (VPNHelper.getInstance().STATE_RUN != VPNHelper.NONE) {
//                    return;
//                }
//                VPNHelper.getInstance().STATE_RUN = VPNHelper.RUN_RUNING;
                if (ACT_ADD_SET.equals(currentActivityName) || ACT_ADD_SET2.equals(currentActivityName)) {
                    switch (VPNHelper.getInstance().actionType) {
                        case VPNHelper.ACTION_TYPE_ADD_VPN:
                            //add vpn info
                            editVpnConfigFile(event, VPNHelper.getInstance().name, VPNHelper.getInstance().server);
                            break;
                        case VPNHelper.ACTION_TYPE_LOGIN:
                            //login
                            loginVpn(event, VPNHelper.getInstance().userName, VPNHelper.getInstance().password);
                            break;
                        case VPNHelper.ACTION_TYPE_LOGOUT://com.android.vpndialogs.ManageDialog
                            logoutVpn(event);
                            break;
                        case VPNHelper.ACTION_TYPE_DEL_VPN:
                            break;
                    }
                } else {
                    // 断开连接

//                    AccessibilityNodeInfo eventSource = event.getSource();
//                    if (null != eventSource)
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//                            setButtonClick(eventSource, "断开连接");
//                        }
                }

                break;
            default:
                break;
        }
    }

    private void logoutVpn(AccessibilityEvent event) {
        if (debug)
            L.v(TAG, "add loginVpn STATE_VPN_LOGOUT = " + VPNHelper.getInstance().STATE_VPN_LOGOUT);
        AccessibilityNodeInfo eventSource = event.getSource();
        if (null == eventSource) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && VPNHelper.getInstance().STATE_VPN_LOGOUT == VPNHelper.NONE) {
            VPNHelper.getInstance().STATE_VPN_LOGOUT = VPNHelper.LOGOUT_ING;
            //click list item.
            //VPNHelper.getInstance().name
            clickItemList(event, VPNHelper.getInstance().name);

//            // 断开连接
            eventSource = getRootInActiveWindow();
            if (null != eventSource)
                setButtonClick(eventSource, "断开连接");


            if (VPNHelper.getInstance().STATE_VPN_LOGOUT == VPNHelper.LOGOUT_ING) {
                VPNHelper.getInstance().STATE_VPN_LOGOUT = VPNHelper.NONE;
            } else {
                toActivity();
            }
        }
    }

    private void clickItemList(AccessibilityEvent event, String txt) {
        L.d(TAG, "clickItemList(), txt = " + txt);
        AccessibilityNodeInfo eventSource = event.getSource();
        if (null == eventSource) return;
        List<AccessibilityNodeInfo> listName = eventSource.findAccessibilityNodeInfosByText(txt);
        if (debug)
            L.d(TAG, "clickItemList, list size is " + (null == listName ? "null" : listName.size()));
        if (null == listName || listName.isEmpty()) return;
        String cls = "";
        for (AccessibilityNodeInfo item : listName) {
            if (null != item) {
                cls = (String) item.getClassName();
                if (debug) L.d(TAG, "clickItemList, cls = " + cls);
                if (TextView.class.getName().equals(cls)) {
                    item.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
    }

    private boolean exist(AccessibilityEvent event, String name) {
        AccessibilityNodeInfo eventSource = event.getSource();
        if (null == eventSource) return false;
        List<AccessibilityNodeInfo> listName = eventSource.findAccessibilityNodeInfosByText(name);
        L.d(TAG, "exist, name = " + name + ", list size is " + (null == listName ? "null" : listName.size()));
        if (null == listName || listName.isEmpty()) return false;
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void clickVpnCreate(AccessibilityEvent event, String id) {
        AccessibilityNodeInfo eventSource = event.getSource();
        if (null == eventSource) return;
        List<AccessibilityNodeInfo> listName = eventSource.findAccessibilityNodeInfosByViewId(id);
        if (debug)
            L.d(TAG, "clickVpnCreate, list size is " + (null == listName ? "null" : listName.size()));
        if (null == listName || listName.isEmpty()) return;
        String cls = "";
        for (AccessibilityNodeInfo item : listName) {
            if (null != item) {
                cls = (String) item.getClassName();
                if (debug) L.d(TAG, "clickVpnCreate, cls = " + cls);
                if (TextView.class.getName().equals(cls)) {
                    item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }

    }

    private void loginVpn(AccessibilityEvent event, String userName, String password) {
        if (debug)
            L.v(TAG, "add loginVpn STATE_VPN_LOGIN = " + VPNHelper.getInstance().STATE_VPN_LOGIN);
        AccessibilityNodeInfo eventSource = event.getSource();
        if (null == eventSource) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && VPNHelper.getInstance().STATE_VPN_LOGIN == VPNHelper.NONE) {
            VPNHelper.getInstance().STATE_VPN_LOGIN = VPNHelper.LOGIN_ING;
            //click list item.
            //VPNHelper.getInstance().name
            clickItemList(event, VPNHelper.getInstance().name);

            // set userName
            setEditContent(eventSource, "com.android.settings:id/username", userName);
            // set password
            setEditContent(eventSource, "com.android.settings:id/password", password);
            //set 保存账号信息
            if (RunManager.getInstance().getSelPhoneType() != RunManager.P780)
                setCheckBoxClick(eventSource, "com.android.settings:id/save_login");
            // 连接
            if (RunManager.getInstance().getSelPhoneType() == RunManager.P780) {
                if (usernameFlag && passwordFlag)
                    setButtonClick(eventSource, "连接");
            } else {
                setButtonClick(eventSource, "连接");
            }


            if (VPNHelper.getInstance().STATE_VPN_LOGIN == VPNHelper.LOGIN_ING) {
                VPNHelper.getInstance().STATE_VPN_LOGIN = VPNHelper.NONE;
            } else {
                toActivity();
            }
        }
    }

    boolean nameFlag = false;
    boolean serverFlag = false;
    boolean usernameFlag = false;
    boolean passwordFlag = false;

    private void editVpnConfigFile(AccessibilityEvent event, String name, String server) {
        L.v(TAG, "add editVpnConfigFile STATE_VPN_EDIT = " + VPNHelper.getInstance().STATE_VPN_EDIT + ", Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT);

        AccessibilityNodeInfo eventSource = event.getSource();
        if (null == eventSource) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && VPNHelper.getInstance().STATE_VPN_EDIT == VPNHelper.NONE) {
            VPNHelper.getInstance().STATE_VPN_EDIT = VPNHelper.EDIT_ING;
            //check exists
//            if (exist(event, VPNHelper.getInstance().name)) {
//                return;
//            }
            //click vpn_create
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                clickVpnCreate(event, "com.android.settings:id/vpn_create");
            }
            // set name
            setEditContent(eventSource, "com.android.settings:id/name", name);
            // set server
            setEditContent(eventSource, "com.android.settings:id/server", server);
            //set ppp加密 不勾选
            setCheckBoxClick(eventSource, "com.android.settings:id/mppe");
            // save btn
            if (RunManager.NX511J == RunManager.getInstance().getSelPhoneType()) {
//                try {
//                    Thread.sleep(1000);
//                    CMD.doSuExec("input tap 955 152");
//                    VPNHelper.getInstance().STATE_VPN_EDIT = VPNHelper.EDIT_DONE;
//                } catch (InterruptedException ignored) {
//                }
            } else {
                if (RunManager.P780 == RunManager.getInstance().getSelPhoneType()) {
                    if (nameFlag && serverFlag) setButtonClick(eventSource, "保存");
                } else
                    setButtonClick(eventSource, "保存");
            }
            if (VPNHelper.getInstance().STATE_VPN_EDIT == VPNHelper.EDIT_ING) {
                VPNHelper.getInstance().STATE_VPN_EDIT = VPNHelper.NONE;
            } else {
                toActivity();
            }
        }
    }

    private void toActivity() {
//        VPNHelper.getInstance().getActivity().startActivity(new Intent(VPNHelper.getInstance().getActivity(), RunActivity.class));
        CMD.doSuExec("input keyevent 4");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void setButtonClick(AccessibilityNodeInfo eventSource, String txt) {
        List<AccessibilityNodeInfo> listName = eventSource.findAccessibilityNodeInfosByText(txt);
        if (debug)
            L.d(TAG, "setButtonClick, list size is " + (null == listName ? "null" : listName.size()));
        if (null == listName || listName.isEmpty()) return;
        String cls = "";
        for (AccessibilityNodeInfo item : listName) {
            if (null != item) {
                cls = (String) item.getClassName();
                if (debug) L.d(TAG, "setButtonClick, cls = " + cls);
                if (Button.class.getName().equals(cls)) {
                    item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    if ("保存".equals(txt)) {
                        VPNHelper.getInstance().STATE_VPN_EDIT = VPNHelper.EDIT_DONE;
                    } else if ("连接".equals(txt)) {
                        VPNHelper.getInstance().STATE_VPN_LOGIN = VPNHelper.LOGIN_DONE;
                    } else if ("断开连接".equals(txt)) {
                        VPNHelper.getInstance().STATE_VPN_LOGOUT = VPNHelper.LOGOUT_DONE;
                    }
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void setCheckBoxClick(AccessibilityNodeInfo eventSource, String id) {
        List<AccessibilityNodeInfo> listName = eventSource.findAccessibilityNodeInfosByViewId(id);
        if (debug)
            L.d(TAG, "setCheckBoxClick, list size is " + (null == listName ? "null" : listName.size()));
        if (null == listName || listName.isEmpty()) return;
        String cls = "";
        for (AccessibilityNodeInfo item : listName) {
            if (null != item) {
                cls = (String) item.getClassName();
                if (CheckBox.class.getName().equals(cls)) {
                    item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    VPNHelper.getInstance().STATE_VPN_EDIT = VPNHelper.EDIT_DONE;
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void setEditContent(AccessibilityNodeInfo eventSource, String id, String content) {
        L.i(TAG, "setEditContent(), 1 id = " + id + ", content = " + content);
        if (id.endsWith("name"))
            nameFlag = false;
        else if (id.endsWith("server"))
            serverFlag = false;
        else if (id.endsWith("username"))
            usernameFlag = false;
        else if (id.endsWith("password"))
            passwordFlag = false;

        List<AccessibilityNodeInfo> listName = eventSource.findAccessibilityNodeInfosByViewId(id);
        if (debug)
            L.d(TAG, "setEditContent, list size is " + (null == listName ? "null" : listName.size()));
        if (null == listName || listName.isEmpty()) return;
        String cls = "";
        for (AccessibilityNodeInfo item : listName) {
            if (null != item) {
                cls = (String) item.getClassName();
                if (EditText.class.getName().equals(cls)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Bundle arguments = new Bundle();
                        arguments.putCharSequence(
                                AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, content);
                        item.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            ClipboardManager clipboard = (ClipboardManager) getBaseContext().getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("", content);
                            clipboard.setPrimaryClip(clip);
                            item.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                            item.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                        }
                    }
                    L.i(TAG, "setEditContent(), 1 id = " + id);
                    if (id.endsWith("name"))
                        nameFlag = true;
                    else if (id.endsWith("server"))
                        serverFlag = true;
                    else if (id.endsWith("username"))
                        usernameFlag = true;
                    else if (id.endsWith("password"))
                        passwordFlag = true;
                }
            }
        }
    }

    @Override
    public void onInterrupt() {
        L.d(TAG, "onInterrupt,...");
    }
}
