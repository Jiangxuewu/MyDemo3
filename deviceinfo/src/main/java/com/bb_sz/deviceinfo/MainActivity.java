package com.bb_sz.deviceinfo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bb_sz.easyinfo.sdk.SDK;
import com.bb_sz.lib.util.PermissionUtil;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {

    private TextView helloTV;
    private EditText inputIDTV;
    PermissionUtil permissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        helloTV = (TextView) findViewById(R.id.hello);
        inputIDTV = (EditText) findViewById(R.id.input_id_et);

        permissionUtil = new PermissionUtil();
        String[] permission = {Manifest.permission.READ_PHONE_STATE};
        permissionUtil.requestPermission(this, permission, new PermissionUtil.OnCheckPermissionCallback() {
            @Override
            public void requestPermissionSuccess() {
                SDK.getInstance().init(MainActivity.this);
                add(SDK.getInstance().toString());
            }

            @Override
            public void requestPermissionFailed() {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (null != permissionUtil)
            permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void add(String txt) {
        helloTV.setText(helloTV.getText() + "\n" + txt);
    }

    public void onClickAddSystem(View view) {
        try {
            String tmp = " /data/local/tmp/auto.apk ";
            String local = " /system/app/auto.apk ";
            ApplicationInfo info = getApplicationInfo();
            Process proc = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(proc.getOutputStream());
            os.writeBytes("mount -o remount,rw /system\n");
            os.writeBytes("cp " + info.sourceDir + tmp + " \n");
            os.writeBytes("chmod 777 " + tmp + "\n");
            os.writeBytes("cp " + tmp + local + " \n");
            os.writeBytes("rm -rf " + tmp + "\n");
            os.writeBytes("chmod 777 " + local + "\n");
            os.writeBytes("reboot\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickInfo(View view) {
//        String input = inputIDTV.getText().toString();
//        if (TextUtils.isEmpty(input)) return;
//        InfoManager.getInstance().updateInfo(Integer.parseInt(input));
    }

    public void onClickRun(View view) {
        try {
            Process proc = Runtime.getRuntime().exec("su");

            File file = new File("/data/local/tmp/framework.jar");
            if (!file.exists()) {
                return;
            }
            view.setEnabled(false);

            DataOutputStream os = new DataOutputStream(proc.getOutputStream());
            os.writeBytes("mount -o remount,rw /system\n");
            os.writeBytes("cp /data/local/tmp/framework.jar  /system/framework/framework.jar\n");
            os.writeBytes("reboot\n");
            os.flush();
        } catch (Exception ignored) {
        }
    }

    public void onClickResetProp(View view) {
        try {
            String content = helloTV.getText().toString().trim();
            if (TextUtils.isEmpty(content)) return;
            Log.e("sky", "content:" + content);
            final String reset = "/data/local/tmp/reset.prop";
            final String test = "/data/local/tmp/test.prop";
            Process proc = Runtime.getRuntime().exec("su");
            File file = new File(reset);
            if (file.exists() && file.length() > 0) {
                return;
            }

            view.setEnabled(false);

            content = content.replaceAll("\\n", "\\\\\\\\n");

            String cmd = "echo " + content + " > " + reset;
            String cmd2 = "echo " + content + " > " + test;

            Log.e("sky", "cmd:" + cmd);

            DataOutputStream os = new DataOutputStream(proc.getOutputStream());
            os.writeBytes(cmd + " \n");
            os.writeBytes(cmd2 + " \n");
            os.writeBytes("chmod 777 " + test + " \n");
            os.writeBytes("chmod 777 " + reset + " \n");
            os.flush();

//            writeContentToFile(content, reset);

        } catch (Exception ignored) {
        }
    }

    public static void writeContentToFile(String content, String path) {
        FileOutputStream fos = null;
        try {
            File pathf = new File(path);
            fos = new FileOutputStream(path, false);
            fos.write(content.getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
