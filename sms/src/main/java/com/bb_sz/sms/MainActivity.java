package com.bb_sz.sms;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.bb_sz.sms.test.TestActivity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final String SMS_URI_OUTBOX = "content://sms/sent";
    private static final String SMS_URI_INBOX = "content://sms/inbox";
    private static final String SMS_URI_CON = "content://sms/conversations";
    private String defaultSmsApp;
    private PermissionUtil mPermissionUtil;
    private EditText editInput;
    private String apkDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            defaultSmsApp = Telephony.Sms.getDefaultSmsPackage(this);
        }
        Log.i("SKY", "defaultSmsApp = " + defaultSmsApp);

        editInput = (EditText) findViewById(R.id.input);

        mPermissionUtil = new PermissionUtil();

        if (Build.VERSION.SDK_INT >= 23) {
            String[] permission = {"android.permission.SEND_SMS", "android.permission.RECEIVE_SMS", "android.permission.READ_SMS", "android.permission.RECEIVE_WAP_PUSH", "android.permission.RECEIVE_SMS"};
            mPermissionUtil.requestPermission(this, permission, new PermissionUtil.OnCheckPermissionCallback() {
                @Override
                public void requestPermissionSuccess() {

                }

                @Override
                public void requestPermissionFailed() {

                }
            });
        }

        ApplicationInfo info = getApplicationInfo();
        apkDir = info.sourceDir;
        Log.i("SKY", "apkDir = " + apkDir);
    }

    public void root(final RootCallback callback) {
//        Environment environment = Environment.getInstance(this);
//        environment.setmRootResultListener(new RootResultListener() {
//            @Override
//            public void onRootSuccess(Process process, ResultEnum resultEnum) {
//                //TODO root成功的回调。
//                String msg = "";
//                switch (resultEnum) {
//                    case ROOT_DZ_SUD_ROOT://新root
//                        msg = "New root success from Catagory";
//                        break;
//                    case ROOT_DZ_SUD_OLD_ROOT://已经root过，无需再执行
//                        msg = "Old root success";
//                        break;
//                    case ROOT_DZ_SUD_SU_OLD_ROOT://借助第三方 root
//                        msg = "New root success from 3d part SU";
//                        break;
//                }
//                Log.e("RootApi", "msg = " + msg);
//                if (null != callback) {
//                    callback.result(true);
//                }
//            }
//
//            @Override
//            public void onRootFail(ResultEnum resultEnum) {
//                //TODO root失败的回调。
//                Log.e("RootApi", "failed = " + resultEnum.name());
//                if (null != callback) {
//                    callback.result(false);
//                }
//            }
//        });
//        environment.initAuto();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= 23 && null != mPermissionUtil) {
            mPermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void changeSms(Context context) {
        Intent intent = null;
        try {
            intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, context.getPackageName());
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetSms(Context context, String defaultSmsApp) {
        Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, defaultSmsApp);
        startActivity(intent);
    }

    public void onClickSetSms(View view) {
        changeSms(this);
    }

    public void onClickResetSms(View view) {
        resetSms(this, defaultSmsApp);
    }

    public void onClickInbox(View view) {
        query(this, SMS_URI_INBOX);
    }

    public void onClickCon(View view) {
//        query(this, SMS_URI_CON);
        Intent intent = new Intent(this, ConversationListAct.class);
        intent.putExtra("TYPE", SMS_URI_CON);
        startActivity(intent);
    }

    public void onClickDel(View view) {
        String id = editInput.getText().toString();
        if (TextUtils.isEmpty(id)) return;
        ContentResolver CR = getContentResolver();

        int i = CR.delete(Uri.parse("content://sms?force_delete=1"), "_id = " + id, null);
//        int i = CR.delete(Uri.parse("content://sms?force_delete=1"), "thread_id = " + id, null);
//        int i = CR.delete(Uri.parse("content://sms?force_delete=1"), "1=1" , null);

        Log.d("SKY", "_id " + id + ", res = " + i);

//        startActivity(new Intent(this, TestActivity.class));
    }

    public void onClickUninstall(View view) {
//        try {
//            //uninstall
//            Runtime.getRuntime().exec("su root pm uninstall " + MainActivity.this.getPackageName());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        root(new RootCallback() {
            @Override
            public void result(boolean success) {
                if (success) {
                    try {
                        //uninstall
                        Process proc = Runtime.getRuntime().exec("su");
                        DataOutputStream os = new DataOutputStream(proc.getOutputStream());
                        os.writeBytes("pm uninstall " + MainActivity.this.getPackageName());
                        os.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onClickAddSystem(View view) {
        root(new RootCallback() {
            @Override
            public void result(boolean success) {
                if (success) {
                    try {
                        // add system
                        Process proc = Runtime.getRuntime().exec("su");
                        DataOutputStream os = new DataOutputStream(proc.getOutputStream());
                        os.writeBytes("mount -o remount,rw /system\n");
                        os.writeBytes("cp " + apkDir + " /data/local/tmp/skysms.apk\n");
                        os.writeBytes("chmod 777 /data/local/tmp/skysms.apk\n");
                        os.writeBytes("cp /data/local/tmp/skysms.apk /system/app/skysms.apk\n");
                        os.writeBytes("rm -rf /data/local/tmp/skysms.apk\n");
                        os.writeBytes("reboot\n");
                        os.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onClickOutbox(View view) {
        query(this, SMS_URI_OUTBOX);
    }

    private void test(Context context) {
//        Conversation localConversation = Conversation.from(paramContext, paramCursor);
        String conversation = "com.android.mms.data.Conversation";

        try {
            String SMS_URI_OUTBOX = "content://sms";//
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(SMS_URI_OUTBOX);
            Cursor cursor = cr.query(uri, null, null, null, null);

            Class<?> cls = Class.forName(conversation);
            Method method = cls.getMethod("from", Context.class, Cursor.class);
            Object obj = method.invoke(null, context, cursor);
            if (null != obj) {
                Log.d("test", "ttt");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void __test(Context context) {
//        SqliteWrapper sqliteWrapper;
        String clsStr = "android.database.sqlite.SqliteWrapper";
        try {
            ContentResolver cr = context.getContentResolver();
            Class<?> cls = Class.forName(clsStr);
            Method methid = cls.getMethod("query", Context.class, ContentResolver.class, Uri.class, String[].class, String.class, String[].class, String.class);
            Uri uri = Uri.parse("content://sms")/*.buildUpon().appendPath("").build()*/;
            Object obj = methid.invoke(null, context, cr, uri, null, null, null, null);
            if (obj instanceof Cursor) {
                Cursor cursor = (Cursor) obj;
                if (null != cursor && cursor.moveToFirst()) {
                    String[] colNames = cursor.getColumnNames();
                    StringBuffer sb = new StringBuffer();
                    for (String col : colNames) {
                        if (sb.length() > 0) {
                            sb.append(", ");
                        }
                        sb.append(col);
                    }
                    Log.e("SKY", "colNames = " + sb.toString());
                    Log.e("SKY", "count = " + cursor.getCount());
                    do {
                        StringBuffer sbItem = new StringBuffer();
                        for (String col : colNames) {
                            sbItem.append(col).append(" = ");
                            sbItem.append(cursor.getString(cursor.getColumnIndex(col)));
                            sbItem.append("\n");
                        }
                        Log.i("SKY", "item:\n" + sbItem.toString());
                    } while (cursor.moveToNext());
                    cursor.close();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void _test(Context context) {
//        final String SMS_URI_OUTBOX = "content://metok/fakecell";// com.xiaomi.metok.permission.CONTENTPROVIDER.read
//        final String SMS_URI_OUTBOX = "content://sms/seen";//nothing
//        final String SMS_URI_OUTBOX = "content://sms/sent";//发送成功的短信
//        final String SMS_URI_OUTBOX = "content://sms/queued";//nothing
//        final String SMS_URI_OUTBOX = "content://sms/inbox";// 正常接收的短信
//        final String SMS_URI_OUTBOX = "content://yellowpage-address";//nothing
//        final String SMS_URI_OUTBOX = "content://sms/mx_status";//nothing
//        final String SMS_URI_OUTBOX = "content://sms/icc";//NullPointerException
//        final String SMS_URI_OUTBOX = "content://sms/icc2";//NullPointerException
//        final String SMS_URI_OUTBOX = "content://hms/address";///com.miui.mipub.permission.READ_HMS failed
//        final String SMS_URI_OUTBOX = "content://b2c-address";//nothing
//        final String SMS_URI_OUTBOX = "content://sms/conversations";// 能获取所有的会话
//        final String SMS_URI_OUTBOX = "content://sms/conversation_participants";//
//        final String SMS_URI_OUTBOX = "content://sms/participants";//
//        final String SMS_URI_OUTBOX = "content://sms/conversation_list_view";//
//        final String SMS_URI_OUTBOX = "content://sms/parts";//
//        final String SMS_URI_OUTBOX = "content://sms/canonical_addresses";//
//        final String SMS_URI_OUTBOX = "content://canonical_addresses";//
//        final String SMS_URI_OUTBOX = "content://sms/threads";//
//        final String SMS_URI_OUTBOX = "content://threads";//
//        final String SMS_URI_OUTBOX = "content://sms/yellowpage-address";//
//        final String SMS_URI_OUTBOX = "content://sms/yellowpage";//
//        final String SMS_URI_OUTBOX = "content://sms/b2c-address";//
        final String SMS_URI_OUTBOX = "content://sms/";//
        try {
//            int count = 0;
//            Telephony.Sms.Conversations.THREAD_ID;
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(SMS_URI_OUTBOX);
//            Cursor cursor = cr.query(uri, null, "thread_id = 2", null, null);
//            Cursor cursor = cr.query(uri, null, "address = '+8618668421927'", null, null);
            Cursor cursor = cr.query(uri, null, "address = '10086'", null, null);
            if (null != cursor && cursor.moveToFirst()) {
                String[] colNames = cursor.getColumnNames();
                StringBuffer sb = new StringBuffer();
                for (String col : colNames) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(col);
                }
                Log.e("SKY", "colNames = " + sb.toString());
                Log.e("SKY", "count = " + cursor.getCount());
                do {
//                    count += cursor.getInt(cursor.getColumnIndex("msg_count"));
                    StringBuffer sbItem = new StringBuffer();
                    for (String col : colNames) {
                        sbItem.append(col).append(" = ");
                        sbItem.append(cursor.getString(cursor.getColumnIndex(col)));
                        sbItem.append("\n");
                    }
                    Log.i("SKY", "item:\n" + sbItem.toString());
                } while (cursor.moveToNext());
//                Log.e("SKY", "count 1  =  " + count);
            } else {
                Log.e("SKY", "count = 0 ");
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    private void query(Context context, String uriStr) {
        try {
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(uriStr);
            Cursor cursor = cr.query(uri, null, null, null, null);
            if (null != cursor && cursor.moveToFirst()) {
                String[] colNames = cursor.getColumnNames();
                StringBuffer sb = new StringBuffer();
                for (String col : colNames) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(col);
                }
                Log.e("SKY", "colNames = " + sb.toString());
                Log.e("SKY", "count = " + cursor.getCount());
                do {
//                    count += cursor.getInt(cursor.getColumnIndex("msg_count"));
                    StringBuffer sbItem = new StringBuffer();
                    for (String col : colNames) {
                        sbItem.append(col).append(" = ");
                        sbItem.append(cursor.getString(cursor.getColumnIndex(col)));
                        sbItem.append("\n");
                    }
                    Log.i("SKY", "item:\n" + sbItem.toString());
                } while (cursor.moveToNext());
//                Log.e("SKY", "count 1  =  " + count);
            } else {
                Log.e("SKY", "count = 0 ");
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }


    public interface RootCallback {
        void result(boolean success);
    }

}
