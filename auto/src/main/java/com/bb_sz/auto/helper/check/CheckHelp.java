package com.bb_sz.auto.helper.check;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

import com.bb_sz.auto.manager.RunManager;
import com.bb_sz.auto.receiver.ACTReceiver;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class CheckHelp {

    private static final String TAG = CheckHelp.class.getSimpleName();
    private static CheckHelp mInstance;

    public static CheckHelp getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new CheckHelp();
            }
            return mInstance;
        }
    }


    public String getCurrentActivityName(Context context) {
//        String tmp1 = getCurrentActivityName1(context);
//        if (TextUtils.isEmpty(tmpAct)) {
//
//        }

        if (RunManager.getInstance().getSelPhoneType() == RunManager.Q505T/* || RunManager.phoneType == RunManager.P780*/){
            return getCurrentActivityName1(context);
        }
        String tmp2 = getCurrentActivityName2(context);

        return tmp2;
    }

    public String getCurrentActivityName1(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        return componentInfo.getClassName();
    }

    public static String getCurrentActivityName2(Context context) {
        return ACTReceiver.act;
//        return MrToSh.doExec("su root cat /data/local/tmp/act.txt");
    }
}
