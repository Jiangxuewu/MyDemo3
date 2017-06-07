package android.bb_sz.framework;

import android.content.Intent;
import android.os.Process;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Activity {
    private static final String TAG = "SKY_Activity";



    public static void onPause(android.app.Activity activity) {
        if (null != activity) {
            String act = activity.getClass().getName();
            Log.d(TAG, "onPause:" + act);

            Intent intent = new Intent("SKY_ACTIVITY_ACTION_PAUSE");
            intent.putExtra("act", act);
            activity.sendBroadcast(intent);
        }
    }

    public static void onResume(android.app.Activity activity) {
        if (null != activity) {
            String act = activity.getClass().getName();
            int pid = Process.myPid();
            Log.d(TAG, "onResume:" + act + ", pid = " + pid);

            Intent intent = new Intent("SKY_ACTIVITY_ACTION");
            intent.putExtra("act", act);
            intent.putExtra("pid", pid);
            activity.sendBroadcast(intent);
        }
    }

//    public static void writeContentToFile(String content, String path) {
//    }
}
