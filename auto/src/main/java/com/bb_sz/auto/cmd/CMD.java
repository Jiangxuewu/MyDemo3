package com.bb_sz.auto.cmd;

import com.bb_sz.lib.log.L;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/4/13.
 */

public class CMD {
    @Deprecated
    public static String doExec(String cmd) {

        L.i("sky_MrToSh", "doExec(), cmd is " + cmd);

        StringBuffer sb = new StringBuffer();
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line + "\n");
            }
            L.i("sky_MrToSh", "doExec(), sb is " + sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void doSuExec(String cmd) {
        doSuExec(new String[]{cmd});
    }

    public static void doSuExec(String[] cmds) {
        try {
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            for (String cmd : cmds) {
                L.d("cmd", cmd);
                os.writeBytes(cmd + "\n");
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLaunchCMD(String pkg, String launcher) {
        return "am start -n " + pkg + "/" + launcher + " -a android.intent.action.MAIN -c android.intent.category.LAUNCHER ";
    }
}
