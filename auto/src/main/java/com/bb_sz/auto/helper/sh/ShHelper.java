package com.bb_sz.auto.helper.sh;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.bb_sz.auto.cmd.CMD;
import com.bb_sz.auto.helper.check.CheckHelp;
import com.bb_sz.auto.manager.Contants;
import com.bb_sz.auto.manager.RunManager;
import com.bb_sz.auto.manager.SP;
import com.bb_sz.auto.market.App;
import com.bb_sz.lib.log.L;

import java.util.Random;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ShHelper {
    private static final String TAG = ShHelper.class.getSimpleName();
    private static ShHelper mInstance;

    public static final String yybPkg = "com.tencent.android.qqdownloader";
    public static final String yybLauncher = "com.tencent.assistant.activity.SplashActivity";//com.tencent.android.qqdownloader/
    //yyb7.0
//    private static final String yybLauncher = "com.tencent.pangu.link.SplashActivity";//com.tencent.android.qqdownloader/

    public static final String qihooPkg = "com.qihoo.appstore";
    public static final String qihooLauncher = "com.qihoo.appstore.home.LauncherActivity";

    public static ShHelper getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new ShHelper();
            }
            return mInstance;
        }
    }

    public ShHelper() {
    }

//    public void init() {
//        Api.getInstance().initMarkets(new IHttpCallback() {
//            @Override
//            public void result(HttpResponse httpResponse) {
//                if (null != httpResponse && httpResponse.code == 200) {
//                    HttpResponseBody body = httpResponse.getBody();
//                    if (null != body) {
//                        String json = body.getString();
//                        if (!TextUtils.isEmpty(json)) {
//                            Market markets = new Gson().fromJson(json, Market.class);
//                            if (null != markets) {
//                                //start check local markets.
//                                //TODO
//                            }
//                        }
//                    }
//                }
//            }
//        });
//    }


    public void searchShFile_HM_NOTE(Context context, ShRunCallback callback, App app) {
        if (app.m_type == 0) {
            searchShFile_HM_NOTE_QiHoo(context, callback, app);
        } else if (app.m_type == 1) {
            searchShFile_HM_NOTE_YYB(context, callback, app);
        } else if (app.m_type == 2) {
            runTest(app, callback);
        }
    }

    private void runTest(App app, ShRunCallback callback) {
        final String testPkg = "com.bb_sz.deviceinfo";
        String testLauncher = "com.bb_sz.deviceinfo.MainActivity";
        sleep(app.before_start_sleep * 1000);
        String[] cmds = {"am force-stop " + testPkg
        };
        CMD.doSuExec(cmds);
        sleep(app.clear_app_sleep * 1000);
        //清空SDcard
        clearSD();
        sleep(app.clear_sd_sleep * 1000);
        CMD.doSuExec("am start -n " + testPkg + "/" + testLauncher + " -a android.intent.action.MAIN -c android.intent.category.LAUNCHER ");
        sleep(app.w_play * 1000);
        callback.result(0, "success");
    }

    public void clear() {
        String[] cmds = {"am force-stop " + yybPkg,
                "pm clear " + yybPkg,
                "am force-stop " + qihooPkg,
                "pm clear " + qihooPkg,
        };
        CMD.doSuExec(cmds);
    }

    //360手机助手，运行脚本
    public synchronized String searchShFile_HM_NOTE_QiHoo(final Context context, ShRunCallback callback, App app) {
        final String needSearchPkg = app.pkg;
        long shStartTime = System.currentTimeMillis();
        sleep(app.before_start_sleep * 1000);
        String[] cmds = {"am force-stop " + yybPkg,
                "pm clear " + yybPkg,
                "am force-stop " + qihooPkg,
                "pm clear " + qihooPkg,
                "pm uninstall " + needSearchPkg
        };

        CMD.doSuExec(cmds);
        sleep(app.clear_app_sleep * 1000);
        //清空SDcard
        clearSD();
        sleep(app.clear_sd_sleep * 1000);

        boolean launched = false;
        String tmp = null;
        int count = 0;
        while (!launched) {
            CMD.doSuExec("am start -n " + qihooPkg + "/" + qihooLauncher + " -a android.intent.action.MAIN -c android.intent.category.LAUNCHER ");
            sleep(app.launch_market_sleep * 1000);
            tmp = CheckHelp.getInstance().getCurrentActivityName(context);
            launched = tmp.contains("com.qihoo");
            if (!launched && count >= 10) {
                callback.result(0, "Next Phone");
                return "";
            }
            L.i(TAG, "launched = " + launched + ", count = " + count++);
        }

        boolean isInstallSuccess = false;
        String cur = null;
        long homePageStartTimeStart = 0;
        long installPageStartTime = 0;
        long appPageStartTime = 0;
        long searchPageStartTime = 0;
        long webPageStartTime = 0;
        boolean isOpen = false;
        boolean runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
        sendBroad(context, app.input, app.name, "SearchActivity", "GenericWordCategoryActivity", app.qh360typekey, app.qh360type, app.qh360typeindex);// 输入关键字并开始搜索
        final int phoneType = RunManager.getInstance().getSelPhoneType();
        int downTimeCount = 0;
        while (!isInstallSuccess && runFlag) {
            sleep(app.run_blank);
            runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
            if (shStartTime > 0 && System.currentTimeMillis() - shStartTime > app.time_out * 1000) {//5分钟内未完成，则为失败
                L.i(TAG, "shStartTime = " + shStartTime + ", b = " + (System.currentTimeMillis() - shStartTime));
                break;
            }
            tmp = CheckHelp.getInstance().getCurrentActivityName(context);
            if (null == cur || !cur.equals(tmp)) {
                cur = tmp;
                L.v(TAG, "cur = " + cur);
            }
            if ("com.qihoo.personPortrait.PersonPortraitGuideActivity".equals(cur)) {//选择性别页面
                selectSex(phoneType);
            } else if ("com.qihoo.appstore.guide.ThemeTransitActivity".equals(cur)) {

            } else if ("com.qihoo.appstore.webview.WebViewActivity".equals(cur)) {//安装他们 优惠不断
                if (phoneType == RunManager.P780) {
                    if (webPageStartTime == 0) {
                        webPageStartTime = System.currentTimeMillis();
                    }
                    if (System.currentTimeMillis() - webPageStartTime > 5 * 1000) {//
                        doBackBtn();
                        webPageStartTime = 0;
                    }
                } else {
                    doBackBtn();
                }
            } else if ("com.qihoo.appstore.home.MainActivity".equals(cur)) {//首页l
                searchPageStartTime = 0;
                sendBroad(context, app.input, app.name, "SearchActivity", "GenericWordCategoryActivity", app.qh360typekey, app.qh360type, app.qh360typeindex);// 输入关键字并开始搜索
                if (homePageStartTimeStart == 0) {
                    homePageStartTimeStart = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - homePageStartTimeStart > app.w_main * 1000) {//
                    if (phoneType == RunManager.NX511J) {
                        input(470, 166);//点击搜索框
                    } else {
                        input(338, 100);//点击搜索框
                    }
                    homePageStartTimeStart = 0;
                }
            } else if ("com.qihoo.appstore.search.SearchActivity".equals(cur) || "com.qihoo.appstore.search.GenericWordCategoryActivity".equals(cur)) {//搜索页面 1
                homePageStartTimeStart = 0;
                if (searchPageStartTime == 0) {
                    searchPageStartTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - searchPageStartTime < app.w_search * 1000) {
                    continue;
                }
                if (app.qh360type == 1 && "com.qihoo.appstore.search.GenericWordCategoryActivity".equals(cur)) {
                } else if (app.qh360type == 1 && "com.qihoo.appstore.search.SearchActivity".equals(cur)) {
                    sleep(5000);
                } else if (app.qh360type == 0 && "com.qihoo.appstore.search.SearchActivity".equals(cur)) {
                }
                if (phoneType == RunManager.P780) {
                    swipe(100, 1000, 100, 10, app.swipe);
                } else if (phoneType == RunManager.NX511J) {
                    swipe(100, 1000, 100, 10, app.swipe);
                } else {
                    swipe(100, 600, 100, 10, app.swipe);
                }
                //滚动超时， 有时候滚动过了，没找到app的时候返回前一页
                if (System.currentTimeMillis() - searchPageStartTime > 1000 * app.swipe_time_out) {
                    searchPageStartTime = 0;
                    doBackBtn();
                    if (app.qh360type == 1) {
                        doBackBtn();
                    }
                }
            } else if ("com.qihoo.appstore.appinfopage.AppInfoActivity".equals(cur)) {//app 详情页面
                if (appPageStartTime == 0) {
                    appPageStartTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - appPageStartTime > app.w_infos * 1000) {//
                    if (phoneType == RunManager.NX511J) {
                        if (downTimeCount <= app.down_times) {
                            input(541, 1833);
                        }
                        downTimeCount++;
                    } else {
                        if (downTimeCount <= app.down_times) {
                            input(206, 1230);
                        }
                        downTimeCount++;
                    }
                    appPageStartTime = 0;
                    if (downTimeCount > app.down_times) {// 下载超时，算成功
                        if (!TextUtils.isEmpty(app.local_name)) {
                            CMD.doSuExec("pm install /sdcard/TM/" + app.local_name);
                            sleep(5000);
                            // next times
                            isInstallSuccess = true;
                            home(context);
                        }
                    }
                }

            } else if ("com.qihoo.appstore.install.NormalInstallTransferActivity".equals(cur)) {
            } else if ("com.android.packageinstaller.PackageInstallerActivity".equals(cur)) {
                if (installPageStartTime == 0) {
                    installPageStartTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - installPageStartTime > app.install_ready_time * 1000) {//
                    if (phoneType == RunManager.NX511J && downTimeCount <= 5) {
                        input(807, 1823);//点击安装按钮
                    } else
                        input(546, 1230);//点击安装按钮
                    installPageStartTime = 0;
                }
            } else if ("com.android.packageinstaller.InstallAppProgress".equals(cur)) {
                sleep(app.w_install * 1000);
                int k = new Random().nextInt(100);
                if (k >= app.open) {//100% open app
                    isOpen = true;
                    if (phoneType == RunManager.NX511J && downTimeCount <= 5) {
                        input(807, 1823);//打开
                    } else
                        input(546, 1230);//打开
                } else {
                    isOpen = false;
                    if (phoneType == RunManager.NX511J) {
                        input(274, 1823);//完成
                    } else {
                        input(176, 1230);//完成
                    }
                    home(context);
                }
                // next times
                isInstallSuccess = true;
            } else if ("com.android.vpndialogs.ConfirmDialog".equals(cur)) {
                if (phoneType == RunManager.NX511J && downTimeCount <= 5) {
                    input(675, 1302);//Need Test
                } else
                    input(450, 868);
            } else if ("com.qihoo.appstore.base.BaseDialogActivity".equals(cur)) {
                if (phoneType == RunManager.NX511J && downTimeCount <= 5) {
                    input(460, 1769);
                } else
                    input(206, 1183);
            } else if ("com.android.launcher3.Launcher".equals(cur)
                    || "com.bb_sz.auto.RunActivity".equals(cur)
                    || "com.tencent.qlauncher.home.Launcher".equals(cur)
                    || "com.kingroot.kinguser.activitys.SuNotifyActivity".equals(cur)) {
            } else if ("com.kingroot.kinguser.advance.install.ui.SilentInstallDialogActivity".equals(cur)) {
            } else {
                doBackBtn();
            }
        }

        if (isOpen) {
            sleep(app.open_ready_time * 1000);
            if (phoneType == RunManager.NX511J && downTimeCount <= 5) {
                input(870, 1093);
            } else
                input(580, 729);
        }

        sleep(app.w_play * 1000);
        if (null != callback) {
            callback.result(isInstallSuccess ? 0 : -1, isInstallSuccess ? "success" : "failed");
        }
        StringBuffer sb = new StringBuffer();
        return sb.toString();
    }

    //360手机助手，选择性别页面的操作
    private void selectSex(int phoneType) {
        int x, y;
        int i = new Random().nextInt(100) % 2;
        L.i(TAG, "i = " + i);
        if (i == 1) {// man
            if (phoneType == RunManager.NX511J) {
                input(340, 770);
            } else {
                input(215, 508);
            }
        } else { //women
            if (phoneType == RunManager.NX511J) {
                input(740, 770);
            } else {
                input(491, 509);
            }
        }
        i = new Random().nextInt(100) % 8;
        if (i <= 3) {
            y = phoneType == RunManager.NX511J ? 1287 : 841;
        } else {
            y = phoneType == RunManager.NX511J ? 1454 : 972;
        }
        switch (i) {
            case 0:
            case 4:
                x = phoneType == RunManager.NX511J ? 195 : 125;
                break;
            case 1:
            case 5:
                x = phoneType == RunManager.NX511J ? 423 : 285;
                break;
            case 2:
            case 6:
                x = phoneType == RunManager.NX511J ? 633 : 427;
                break;
            case 3:
            case 7:
            default:
                x = phoneType == RunManager.NX511J ? 852 : 571;
                break;
        }

        input(x, y);
        if (phoneType == RunManager.NX511J) {
            input(548, 1697);
        } else {
            input(373, 1129);
        }
    }


    public String searchShFile_ZTE_Q505T_YYB(Context context, ShRunCallback callback, App app) {
        String needSearchPkg = app.pkg;
        long shStartTime = System.currentTimeMillis();
        sleep(2000);
        String[] cmds = {"am force-stop " + yybPkg,
                "pm clear " + yybPkg,
                "am force-stop " + qihooPkg,
                "pm clear " + qihooPkg,
                "pm uninstall " + needSearchPkg
        };

        CMD.doSuExec(cmds);
        sleep(1000);
        //清空SDcard
        clearSD();
        sleep(2000);

        boolean launched = false;
        String tmp = null;
        while (!launched) {
            CMD.doSuExec("am start -n " + yybPkg + "/" + yybLauncher + " -a android.intent.action.MAIN -c android.intent.category.LAUNCHER ");
            sleep(10000);
            tmp = CheckHelp.getInstance().getCurrentActivityName(context);
            launched = tmp.contains("com.tencent");
            L.i(TAG, "launched = " + launched);
        }
        boolean isInstallSuccess = false;
        String cur = null;
        long homePageStartTimeStart = 0;
        long searchPageStartTime = 0;
        int x, y;
        boolean runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
        sendBroadYYB(context, app.input, app.name, "SearchActivity");// 输入关键字并开始搜索
        while (!isInstallSuccess && runFlag) {
            sleep(300);
            runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
            if (shStartTime > 0 && System.currentTimeMillis() - shStartTime > app.time_out * 1000) {
                L.i(TAG, "shStartTime = " + shStartTime + ", b = " + (System.currentTimeMillis() - shStartTime));
                break;
            }
            tmp = CheckHelp.getInstance().getCurrentActivityName(context);

            if (null == cur || !cur.equals(tmp)) {
                cur = tmp;
                L.v(TAG, "cur = " + cur);
            }

            if ("com.tencent.assistantv2.activity.MainActivity".equals(cur)) {//首页
                if (homePageStartTimeStart == 0) {
                    homePageStartTimeStart = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - homePageStartTimeStart > app.w_main * 1000) {//
                    input(158, 79);//点击搜索框
                    homePageStartTimeStart = 0;
                }
            } else if ("com.tencent.nucleus.search.SearchActivity".equals(cur)) {//搜索页面
                if (searchPageStartTime == 0) {
                    searchPageStartTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - searchPageStartTime < app.w_search * 1000) {
                    continue;
                }
                swipe(100, 500, 100, 10);
                input(346, 147);
                input(101, 146);
            } else if ("com.tencent.pangu.activity.AppDetailActivityV5".equals(cur)) {//app 详情页面
                input(253, 750);//点击下载按钮
                //判断是wifi还移动网络
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo.getType() == 1) {// wifi
                } else {
                    input(233, 800);//点击 土豪继续下载按钮
                }
                sleep(10000);
//                input(108, 490);//点击 暂不开启 按钮
                doBackBtn();
            } else if ("com.tencent.pangu.activity.InstallerListenerActivity".equals(cur)) {
            } else if ("com.android.packageinstaller.PackageInstallerActivity".equals(cur)) {
                input(358, 745);//点击 安装 按钮
                input(358, 745);//点击 安装 按钮
                input(358, 745);//点击 安装 按钮
            } else if ("com.android.packageinstaller.InstallAppProgress".equals(cur)) {
//                sleep(5000);
//                int k = new Random().nextInt(100);
//                if (k >= app.open) {//60% open app
//                    input(546, 1230);//打开
//                } else {
//                    input(176, 1230);//完成
//                }
//                // next times
//                isInstallSuccess = true;
            } else if ("com.android.launcher3.Launcher".equals(cur)
                    || "com.bb_sz.auto.RunActivity".equals(cur)
                    || "com.tencent.assistant.activity.SplashImplActivity".equals(cur)
                    || "com.tencent.pangu.welcome.WelcomeActivity".equals(cur)
                    || "com.tencent.cloud.activity.GuideActivity".equals(cur)) {
                sleep(1000);
            } else if ("com.tencent.pangu.activity.PopUpNecessaryAcitivity".equals(cur)) {
                input(454, 230);
            } else if ("com.android.settings.dynamicperm.InstallCompleted".equals(cur)) {
                int k = new Random().nextInt(100);
                if (k >= app.open) {
                    input(341, 744);//打开
                } else {
                    input(138, 744);//完成
                }
                isInstallSuccess = true;
            } else if ("com.tencent.pangu.activity.SelfUpdateActivity".equals(cur)) {
                input(100, 648);
            } else {
                doBackBtn();
            }
        }

//        sleep(5000);
//        input(580, 729);
        sleep(app.w_play * 1000);
        if (null != callback) {
            callback.result(isInstallSuccess ? 0 : -1, isInstallSuccess ? "success" : "failed");
        }
        StringBuffer sb = new StringBuffer();
        return sb.toString();
    }

    public String searchShFile_HM_NOTE_YYB(Context context, ShRunCallback callback, App app) {
        if (null == app) return "";
        final String needSearchPkg = app.pkg;

        long shStartTime = System.currentTimeMillis();
        sleep(app.before_start_sleep * 1000);
        String[] cmds = {"am force-stop " + yybPkg,
                "pm clear " + yybPkg,
                "am force-stop " + qihooPkg,
                "pm clear " + qihooPkg,
                "pm uninstall " + needSearchPkg

        };
        CMD.doSuExec(cmds);
        sleep(app.clear_app_sleep * 1000);
        //清空SDcard
        clearSD();
        sleep(app.clear_sd_sleep * 1000);
        boolean launched = false;
        String tmp = null;
        int count = 0;
        while (!launched) {
            CMD.doSuExec("am start -n " + yybPkg + "/" + yybLauncher + " -a android.intent.action.MAIN -c android.intent.category.LAUNCHER ");
            sleep(app.launch_market_sleep * 1000);
            tmp = CheckHelp.getInstance().getCurrentActivityName(context);
            launched = tmp.contains("com.tencent");
            if (!launched && count >= 10) {
                callback.result(0, "Next Phone");
                return "";
            }
            L.i(TAG, "launched = " + launched + ", count = " + count++);
        }
        boolean isInstallSuccess = false;
        String cur = null;
        long homePageStartTimeStart = 0;
        long appPageStartTime = 0;
        long searchPageStartTime = 0;
        boolean isOpen = false;
        boolean runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
        final int phoneType = RunManager.getInstance().getSelPhoneType();
        while (!isInstallSuccess && runFlag) {
            sleep(app.run_blank);
            runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
            if (shStartTime > 0 && System.currentTimeMillis() - shStartTime > app.time_out * 1000) {
                L.i(TAG, "shStartTime = " + shStartTime + ", b = " + (System.currentTimeMillis() - shStartTime));
                break;
            }
            tmp = CheckHelp.getInstance().getCurrentActivityName(context);

            if (null == cur || !cur.equals(tmp)) {
                cur = tmp;
                L.v(TAG, "cur = " + cur);
            }

            if ("com.tencent.assistantv2.activity.MainActivity".equals(cur)) {//首页
                sendBroadYYB(context, app.input, app.name, "SearchActivity");// 输入关键字并开始搜索
                if (homePageStartTimeStart == 0) {
                    homePageStartTimeStart = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - homePageStartTimeStart > app.w_main * 1000) {//
                    input(266, 100);//点击搜索框
                    homePageStartTimeStart = 0;
                }
            } else if ("com.tencent.nucleus.search.SearchActivity".equals(cur)) {//搜索页面
                if (searchPageStartTime == 0) {
                    searchPageStartTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - searchPageStartTime < app.w_search * 1000) {
                    continue;
                }

                if (phoneType == RunManager.P780) {
                    swipe(100, 1000, 100, 10, app.swipe);
                } else if (phoneType == RunManager.NX511J) {
                    swipe(100, 1000, 100, 10, app.swipe);
                } else {
                    swipe(100, 600, 100, 10, app.swipe);
                }

                //滚动超时， 有时候滚动过了，没找到app的时候返回前一页
                if (System.currentTimeMillis() - searchPageStartTime > 1000 * app.swipe_time_out) {
                    searchPageStartTime = 0;
                    doBackBtn();
                    doBackBtn();
                    doBackBtn();
                }
            } else if ("com.tencent.pangu.activity.AppDetailActivityV5".equals(cur)) {//app 详情页面
                if (appPageStartTime == 0) {
                    appPageStartTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - appPageStartTime < app.w_infos * 1000) {//
                    continue;
                }
                input(360, 1230);//点击下载按钮
                //判断是wifi还移动网络
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo.getType() == 1) {// wifi
                } else {
                    input(233, 800);//点击 土豪继续下载按钮
                }
                sleep(10000);
                input(234, 780);//点击 暂不开启 按钮
                if (!TextUtils.isEmpty(app.local_name)) {
                    CMD.doSuExec("pm install /sdcard/TM/" + app.local_name);
                    sleep(5000);
                    // next times
                    isInstallSuccess = true;
                    home(context);
                }

            } else if ("com.tencent.pangu.activity.InstallerListenerActivity".equals(cur)) {
            } else if ("com.android.packageinstaller.PackageInstallerActivity".equals(cur)) {
                input(541, 1235);//点击 安装 按钮
            } else if ("com.android.packageinstaller.InstallAppProgress".equals(cur)) {
                sleep(app.w_install * 1000);
                int k = new Random().nextInt(100);
                if (k >= app.open) {//60% open app
                    if (phoneType == RunManager.NX511J) {
                        input(807, 1823);//打开
                    } else
                        input(546, 1230);//打开
                } else {
                    if (phoneType == RunManager.NX511J) {
                        input(274, 1823);//完成
                    } else {
                        input(176, 1230);//完成
                    }
                }
                // next times
                isInstallSuccess = true;
                home(context);
            } else if ("com.android.launcher3.Launcher".equals(cur)
                    || "com.bb_sz.auto.RunActivity".equals(cur)
                    || "com.tencent.assistant.activity.SplashImplActivity".equals(cur)
                    || "com.tencent.pangu.welcome.WelcomeActivity".equals(cur)
                    || "com.tencent.cloud.activity.GuideActivity".equals(cur)
                    || "com.kingroot.kinguser.activitys.SuNotifyActivity".equals(cur)) {
            } else if ("com.tencent.pangu.activity.PopUpNecessaryAcitivity".equals(cur)) {
                doBackBtn();
            } else {
                doBackBtn();
            }
        }
        if (isOpen) {
            sleep(app.open_ready_time * 1000);
            if (phoneType == RunManager.NX511J) {
                input(870, 1093);
            } else
                input(580, 729);
        }
        sleep(app.w_play * 1000);
        if (null != callback) {
            callback.result(isInstallSuccess ? 0 : -1, isInstallSuccess ? "success" : "failed");
        }

        return "";
    }

    public String searchShFile_ZTE_Q505T(final Context context, ShRunCallback callback, App app) {
        if (1 == 2) {
            final String testPkg = "com.bb_sz.deviceinfo";
            String testLauncher = "com.bb_sz.deviceinfo.MainActivity";
            sleep(2000);
            String[] cmds = {"am force-stop " + testPkg
            };
            CMD.doSuExec(cmds);
            sleep(1000);
            //清空SDcard
            clearSD();
            sleep(2000);
            CMD.doSuExec("am start -n " + testPkg + "/" + testLauncher + " -a android.intent.action.MAIN -c android.intent.category.LAUNCHER ");

            sleep(10000);
            callback.result(0, "success");
            return "";
        }

//        return searchShFile_ZTE_Q505T_QiHoo(context, callback, app);
        return searchShFile_ZTE_Q505T_YYB(context, callback, app);
    }

    public String searchShFile_ZTE_Q505T_QiHoo(final Context context, ShRunCallback callback, App app) {
        String needSearchPkg = app.pkg;
        long shStartTime = System.currentTimeMillis();
        sleep(2000);
        String[] cmds = {"am force-stop " + qihooPkg,
                "pm clear " + qihooPkg,
                "am force-stop  " + yybPkg,
                "pm clear " + yybPkg,
                "pm uninstall " + needSearchPkg
        };
        CMD.doSuExec(cmds);
        sleep(1000);
        //清空SDcard
        clearSD();
        sleep(2000);
        boolean launched = false;
        String tmp = null;
        while (!launched) {
            CMD.doSuExec("am start -n " + qihooPkg + "/" + qihooLauncher + " -a android.intent.action.MAIN -c android.intent.category.LAUNCHER ");
            sleep(10000);
            tmp = CheckHelp.getInstance().getCurrentActivityName(context);
            launched = tmp.contains("com.qihoo");
            L.i(TAG, "launched = " + launched);
        }
        boolean isInstallSuccess = false;
        String cur = null;
        long homePageStartTimeStart = 0;
        long installPageStartTime = 0;
        long appPageStartTime = 0;
        long searchPageStartTime = 0;
        int x, y;
        boolean runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
        sendBroad(context, app.input, app.name, "SearchActivity", "GenericWordCategoryActivity", app.qh360typekey, app.qh360type, app.qh360typeindex);// 输入关键字并开始搜索

        while (!isInstallSuccess && runFlag) {
            sleep(250);
            runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
            if (shStartTime > 0 && System.currentTimeMillis() - shStartTime > app.time_out * 1000) {
                L.i(TAG, "shStartTime = " + shStartTime + ", b = " + (System.currentTimeMillis() - shStartTime));
                break;
            }
            tmp = CheckHelp.getInstance().getCurrentActivityName(context);
            if (null == cur || !cur.equals(tmp)) {
                cur = tmp;
                L.v(TAG, "cur = " + cur);
            }
            if ("com.qihoo.personPortrait.PersonPortraitGuideActivity".equals(cur)) {//选择性别页面
                //wait
                int i = new Random().nextInt(100) % 2;
                L.i(TAG, "i = " + i);
                if (i == 1) {// man
                    input(215, 508);
                } else { //women
                    input(491, 509);
                }
                i = new Random().nextInt(100) % 8;
                if (i <= 3) {
                    y = 841;
                } else {
                    y = 972;
                }
                switch (i) {
                    case 0:
                    case 4:
                        x = 125;
                        break;
                    case 1:
                    case 5:
                        x = 285;
                        break;
                    case 2:
                    case 6:
                        x = 427;
                        break;
                    case 3:
                    case 7:
                    default:
                        x = 571;
                        break;
                }

                input(x, y);
                input(373, 1129);
            } else if ("com.qihoo.appstore.guide.ThemeTransitActivity".equals(cur)) {

            } else if ("com.qihoo.appstore.webview.WebViewActivity".equals(cur)) {//安装他们 优惠不断
                doBackBtn();
            } else if ("com.qihoo.appstore.home.MainActivity".equals(cur)) {//首页l
                if (homePageStartTimeStart == 0) {
                    homePageStartTimeStart = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - homePageStartTimeStart > app.w_main * 1000) {//
                    input(338, 100);//点击搜索框
                    homePageStartTimeStart = 0;
                }
            } else if ("com.qihoo.appstore.search.SearchActivity".equals(cur)) {//搜索页面 1
                if (searchPageStartTime == 0) {
                    searchPageStartTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - searchPageStartTime < app.w_search * 1000) {
                    continue;
                }
                swipe(100, 600, 100, 10);
                searchPageStartTime = 0;
            } else if ("com.qihoo.appstore.appinfopage.AppInfoActivity".equals(cur)) {//app 详情页面
                if (appPageStartTime == 0) {
                    appPageStartTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - appPageStartTime > app.w_infos * 1000) {//
                    input(206, 1230);
                    appPageStartTime = 0;
                }
            } else if ("com.qihoo.appstore.install.NormalInstallTransferActivity".equals(cur)) {
            } else if ("com.android.packageinstaller.PackageInstallerActivity".equals(cur)) {
                if (installPageStartTime == 0) {
                    installPageStartTime = System.currentTimeMillis();
                }
                if (System.currentTimeMillis() - installPageStartTime > 5000) {//
                    input(546, 1230);//点击安装按钮
                    installPageStartTime = 0;
                }
            } else if ("com.android.packageinstaller.InstallAppProgress".equals(cur)) {
                sleep(app.w_install * 1000);
                int k = new Random().nextInt(100);
                if (k >= 0) {//100% open app
                    input(546, 1230);//打开
                } else {
                    input(176, 1230);//完成
                }
                // next times
                isInstallSuccess = true;
            } else if ("com.android.vpndialogs.ConfirmDialog".equals(cur)) {
                input(450, 868);
            } else if ("com.qihoo.appstore.base.BaseDialogActivity".equals(cur)) {
                input(206, 1183);
            } else if ("com.android.launcher3.Launcher".equals(cur)
                    || "com.bb_sz.auto.RunActivity".equals(cur)) {
            } else {
                doBackBtn();
            }
        }

        sleep(5000);
        input(580, 729);
        sleep(app.w_play * 1000);
        if (null != callback) {
            callback.result(isInstallSuccess ? 0 : -1, isInstallSuccess ? "success" : "failed");
        }
        StringBuffer sb = new StringBuffer();
        return sb.toString();
    }

    public String searchShFile_HM_NOTE_QiHoo(final Context context, ShRunCallback callback) {

        return null;
    }

    private void sendBroad(Context context, String input, String txt, String act, String act1, String key, int type, int index) {
        Intent intent = new Intent();
        intent.setAction("com.bb_sz.intent.action.sl360");
        intent.putExtra("sl360_input", input);
        intent.putExtra("sl360_txt", txt);
        intent.putExtra("sl360_act", act);
        intent.putExtra("sl360_act1", act1);
        intent.putExtra("sl360_key", key);
        intent.putExtra("sl360_type", type);
        intent.putExtra("sl360_index", index);
        context.sendBroadcast(intent);
    }

    private void sendBroadYYB(Context context, String input, String txt, String act) {
        Intent intent = new Intent();
        intent.setAction("com.bb_sz.intent.action.slyyb");
        intent.putExtra("slyyb_input", input);
        intent.putExtra("slyyb_txt", txt);
        intent.putExtra("slyyb_act", act);
        context.sendBroadcast(intent);
    }

    private void inputText(String text) {
        CMD.doSuExec("input text " + text);
        sleep(1000);
    }

    private void input(int x, int y) {
        doExec("input tap " + x + " " + y);
        sleep(2500);
    }

    private void swipe(int x1, int y1, int x2, int y2) {
        swipe(x1, y1, x2, y2, 3);
    }

    private void swipe(int x1, int y1, int x2, int y2, int blank) {
        doExec("input swipe " + x1 + " " + y1 + " " + x2 + " " + y2);
        sleep(blank * 1000);
    }

    private void doBackBtn() {
        doExec("input keyevent 4");//返回按钮
        sleep(1000 + new Random().nextInt(5) * 1000);
    }

    private void clearSD() {
        if (RunManager.getInstance().getSelPhoneType() == RunManager.Q505T) {
            String[] cmds = {"cd /sdcard"
                    , "rm -rf .b*"
                    , "rm -rf .c*"
                    , "rm -rf .d*"
                    , "rm -rf .e*"
                    , "rm -rf .f*"
                    , "rm -rf .g*"
                    , "rm -rf .h*"
                    , "rm -rf .i*"
                    , "rm -rf .j*"
                    , "rm -rf .k*"
                    , "rm -rf .l*"
                    , "rm -rf .m*"
                    , "rm -rf .o*"
                    , "rm -rf .p*"
                    , "rm -rf .q*"
                    , "rm -rf .r*"
                    , "rm -rf .s*"
                    , "rm -rf .t*"
                    , "rm -rf .u*"
                    , "rm -rf .v*"
                    , "rm -rf .w*"
                    , "rm -rf .x*"
                    , "rm -rf .y*"
                    , "rm -rf .z*"
                    , "rm -rf !(TM)"
            };
            CMD.doSuExec(cmds);

        } else {
            String[] cmds = {"cd /sdcard"
                    , "rm -rf .*"
                    , "rm -rf !(TM)"
            };
            CMD.doSuExec(cmds);
        }

    }

    private void doExec(String cmd) {
        CMD.doSuExec(cmd);
    }

    private void sleep(long time) {
        try {
            if (time >= 1000) {
                L.v(TAG, "sleep : " + time);
            }
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
        }
    }

    private void home(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
        intent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(intent);
    }
}
