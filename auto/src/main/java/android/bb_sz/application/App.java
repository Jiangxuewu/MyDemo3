package android.bb_sz.application;

import android.content.Context;

/**
 * Created by Administrator on 2017/6/6.
 */

public class App {

    public static String pkg = "";


    public static void attachBaseContext(Context context) {
        if (null != context){
            pkg = context.getPackageName();
        }
    }

}
