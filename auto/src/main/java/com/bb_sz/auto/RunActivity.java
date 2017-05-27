package com.bb_sz.auto;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bb_sz.auto.cmd.CMD;
import com.bb_sz.auto.helper.check.CheckHelp;
import com.bb_sz.auto.helper.sh.ShHelper;
import com.bb_sz.auto.ip.VPNHelper;
import com.bb_sz.auto.manager.Contants;
import com.bb_sz.auto.manager.RunManager;
import com.bb_sz.auto.manager.SP;
import com.bb_sz.auto.manager.Setting;
import com.bb_sz.lib.log.L;
import com.bb_sz.lib.util.PermissionUtil;

import java.util.List;


public class RunActivity extends Activity implements AccessibilityManager.AccessibilityStateChangeListener, View.OnClickListener, View.OnLongClickListener {

    private static final String TAG = "RunActivity";
    private Button startBtn, stopBtn;
    private EditText maxNewCountET;

    private CheckBox checkBoxIp, checkBoxUninstall, checkBoxOnlyImeiImsi, checkBoxPhoneReboot, checkBoxNotChangeDInfo;
    private CheckBox checkBoxRebootTimeBlank;
    private CheckBox checkBoxRunNetData;
    private EditText editTextIpCounts, editTextUninstallKeys, editTextRebootTimeBlank;
    private Spinner phoneType;
    private PermissionUtil permissionUtil;
    private ArrayAdapter<String> phoneTypeAdapter;
    private AccessibilityManager accessibilityManager;
    private TextView version;
    private BroadcastReceiver recever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Contants.UPDATEUI.equals(action)) {
                updateViews();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        saveOnCreateTime();
        initData();
        initSettingViews();

        VPNHelper.getInstance().init(this);

        CMD.doSuExec("ls /sdcard");


        if (RunManager.getInstance().getSelPhoneType() == RunManager.HMNOTE1LTE) {
            permissionUtil = new PermissionUtil();
            permissionUtil.requestPermission(this, new String[]{
                    "android.permission.WRITE_EXTERNAL_STORAGE",
                    "android.permission.READ_EXTERNAL_STORAGE"
            }, new PermissionUtil.OnCheckPermissionCallback() {
                @Override
                public void requestPermissionSuccess() {

                }

                @Override
                public void requestPermissionFailed() {

                }
            });
        }


        checkAccessible();
        unlockScreen();

        register();

        RunManager.getInstance().initDeviceInfoType(this);

//        run();
    }

    private void register() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Contants.UPDATEUI);
        registerReceiver(recever, filter);
    }

    boolean isRun = false;

    private void unlockScreen() {
        if (RunManager.getInstance().getSelPhoneType() != RunManager.NX511J) {
            return;
        }
        isRun = true;
        final long start = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun) {
                    long time = 0;
                    if (RunManager.getInstance().getSelPhoneType() == RunManager.NX511J) {
                        time = 1000 * 30;
                    } else if (RunManager.getInstance().getSelPhoneType() == RunManager.HMNOTE1LTE) {
                        time = 1000 * 30;
                    }
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String cur = CheckHelp.getInstance().getCurrentActivityName(RunActivity.this);
                    L.i(TAG, "unlockScreen, cur = " + cur);
                    if (null != cur && cur.equals("com.bb_sz.auto.RunActivity") && System.currentTimeMillis() - start > 1000 * 10 && RunManager.getInstance().getSelPhoneType() == RunManager.NX511J) {
//                        CMD.doSuExec("input swipe 500 1000 500 500");
                        String[] cmd = {"input swipe 500 1000 500 500", "input tap 254 888", "input tap 537 888", "input tap 813 888", "input tap 254 1111"};
                        CMD.doSuExec(cmd);
                        isRun = false;
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        onStart();
                    } else if (RunManager.getInstance().getSelPhoneType() == RunManager.HMNOTE1LTE) {
                        String[] cmd = {"input tap 141 645", "input tap 358 662", "input tap 566 667", "input tap 145 883", "input tap 559 1123"};
                        CMD.doSuExec(cmd);
                        isRun = false;
                        try {
                            Thread.sleep(time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        onStart();
                    } else if (null != cur && !cur.equals("com.bb_sz.auto.RunActivity")){
                        CMD.doSuExec("input keyevent 4");
                        String[] cmds = {"am force-stop " + ShHelper.yybPkg,
                                "pm clear " + ShHelper.yybPkg,
                                "am force-stop " + ShHelper.qihooPkg,
                                "pm clear " + ShHelper.qihooPkg
                        };
                        CMD.doSuExec(cmds);
                    }
                }
            }
        }).start();
    }

    private void checkAccessible() {
        //监听AccessibilityService 变化
        accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        accessibilityManager.addAccessibilityStateChangeListener(this);
        updateServiceStatus();
    }

    private void updateServiceStatus() {
        if (!isServiceEnabled()) {
            Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(accessibleIntent);
        }
    }

    private boolean isServiceEnabled() {
        List<AccessibilityServiceInfo> accessibilityServices =
                accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        for (AccessibilityServiceInfo info : accessibilityServices) {
            L.i(TAG, "isServiceEnabled(), id is " + info.getId());
            if (info.getId().equals(getPackageName() + "/.accessibility.AccessibilityService")) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (null != permissionUtil)
            permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onClickSaveMaxNewCount(View view) {
        String input = maxNewCountET.getText().toString();
        if (TextUtils.isEmpty(input)) return;
        int count = Integer.parseInt(input);
        Setting.COUNT = count;
        SP.getInstance().setIntValue(Contants.SETTING_COUNT_START, Setting.COUNT);
        maxNewCountET.setText("");
        maxNewCountET.setHint("cur:" + SP.getInstance().getRunTimes() + ", start:" + Setting.COUNT);
    }

    public void start(View view) {
        if (isRun) return;
        RunManager.getInstance().start();
        startBtn.setEnabled(false);
        stopBtn.setEnabled(true);
        run();
    }

    public void stop(View view) {
        RunManager.getInstance().stop();
        startBtn.setEnabled(true);
        stopBtn.setEnabled(false);
    }

    public void reset(View view) {
        RunManager.getInstance().reset();
    }
    public void resetAndReboot(View view) {
        RunManager.getInstance().reset();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CMD.doSuExec("reboot");
    }


    public void upload(View view) {
        String pkg = "com.bb_sz.deviceinfo";
        String launcher = "com.bb_sz.deviceinfo.MainActivity";
//        CMD.doSuExec(CMD.getLaunchCMD(pkg, launcher));

        CMD.doExec("su root " + CMD.getLaunchCMD(pkg, launcher));

//        CMD.doExec(CMD.getLaunchCMD(pkg, launcher));
//        ComponentName componet = new ComponentName(pkg, launcher);
//        Intent intent = new Intent();
//        intent.setComponent(componet);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        if (null != view)
//            view.setEnabled(false);
//        new HenJuIP().reCall();
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.d(TAG, "onStart");
        run();
    }

    private void run() {
        if (isRun) return;
        boolean runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
        L.d(TAG, "run(), run flag is " + runFlag);
        if (runFlag)
            RunManager.getInstance().start(RunActivity.this);
//        home();
    }

    private void home() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
        intent.addCategory(Intent.CATEGORY_HOME);
        this.startActivity(intent);
    }

    private void saveOnCreateTime() {
        long onCreateTime = SP.getInstance().getLongValue(Contants.SETTING_ON_CREATE_TIME_KEY);
        if (onCreateTime == 0)
            SP.getInstance().setLongValue(Contants.SETTING_ON_CREATE_TIME_KEY, java.lang.System.currentTimeMillis());
    }


    private void initData() {
        initSettingData();
        initSpinnerData();
    }

    private void initSpinnerData() {
        phoneTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, RunManager.getInstance().getPhoneType());
        phoneTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void initSettingData() {
        Setting.isNeedChangeIp = SP.getInstance().getBooleanValue(Contants.SETTING_CHANGE_IP_KEY);
        Setting.isNeedUninstallDownApps = SP.getInstance().getBooleanValue(Contants.SETTING_UNINSTALL_APP_KEY);
        Setting.isOnlyChangeImeiImsi = SP.getInstance().getBooleanValue(Contants.SETTING_ONLY_IMEI_IMSI_KEY);
        Setting.isNeedRebootAfterUpdateBuild = SP.getInstance().getBooleanValue(Contants.SETTING_PHONE_REBOOT_KEY);
        Setting.isNotChangeDeviceInfo = SP.getInstance().getBooleanValue(Contants.SETTING_NOT_CHANGE_D_INFO_KEY);
        Setting.isNeedRebootTimeBlank = SP.getInstance().getBooleanValue(Contants.SETTING_REBOOT_TIME_BLANK_KEY);
        Setting.isRunNetData = SP.getInstance().getBooleanValue(Contants.SETTING_IS_RUN_NET_DATA_KEY);

        Setting.iChangeIpCount = SP.getInstance().getIntValue(Contants.SETTING_CHANGE_IP_COUNT_KEY);
        Setting.uninstallKeyWork = SP.getInstance().getStringValue(Contants.SETTING_UNINSTALL_APP_KEYS_KEY);
        Setting.iRebootTimeBlankNumber = SP.getInstance().getIntValue(Contants.SETTING_REBOOT_TIME_BLANK_NUMBER_KEY);
    }

    private void initSettingViews() {
        startBtn = (Button) findViewById(R.id.btn_start);
        stopBtn = (Button) findViewById(R.id.btn_stop);
        phoneType = (Spinner) findViewById(R.id.phone_type_spinner);
        version = (TextView) findViewById(R.id.version);

        version.setOnClickListener(this);
        version.setOnLongClickListener(this);

        maxNewCountET = (EditText) findViewById(R.id.input_et_max_new_count);

        checkBoxIp = (CheckBox) findViewById(R.id.cbox_ip);
        checkBoxUninstall = (CheckBox) findViewById(R.id.cbox_uninstall_app);
        checkBoxOnlyImeiImsi = (CheckBox) findViewById(R.id.cbox_only_imei_imsi);
        checkBoxPhoneReboot = (CheckBox) findViewById(R.id.cbox_phone_need_reboot_update_build);
        checkBoxNotChangeDInfo = (CheckBox) findViewById(R.id.cbox_is_not_change_device_info);
        checkBoxRebootTimeBlank = (CheckBox) findViewById(R.id.cbox_reboot_time_blank);
        checkBoxRunNetData = (CheckBox) findViewById(R.id.cbox_run_net_data);

        editTextIpCounts = (EditText) findViewById(R.id.edit_ip);
        editTextUninstallKeys = (EditText) findViewById(R.id.edit_uninstall_app);
        editTextRebootTimeBlank = (EditText) findViewById(R.id.edit_reboot_time_blank);

        checkBoxIp.setChecked(Setting.isNeedChangeIp);
        checkBoxUninstall.setChecked(Setting.isNeedUninstallDownApps);
        checkBoxOnlyImeiImsi.setChecked(Setting.isOnlyChangeImeiImsi);
        checkBoxPhoneReboot.setChecked(Setting.isNeedRebootAfterUpdateBuild);
        checkBoxNotChangeDInfo.setChecked(Setting.isNotChangeDeviceInfo);
        checkBoxRebootTimeBlank.setChecked(Setting.isNeedRebootTimeBlank);
        checkBoxRunNetData.setChecked(Setting.isRunNetData);


        checkBoxIp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Setting.isNeedChangeIp = b;
                SP.getInstance().setBooleanValue(Contants.SETTING_CHANGE_IP_KEY, b);
                editTextIpCounts.setVisibility(b ? View.VISIBLE : View.GONE);
            }
        });
        checkBoxUninstall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Setting.isNeedUninstallDownApps = b;
                SP.getInstance().setBooleanValue(Contants.SETTING_UNINSTALL_APP_KEY, b);
                editTextUninstallKeys.setVisibility(b ? View.VISIBLE : View.GONE);
            }
        });
        checkBoxOnlyImeiImsi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Setting.isOnlyChangeImeiImsi = b;
                SP.getInstance().setBooleanValue(Contants.SETTING_ONLY_IMEI_IMSI_KEY, b);
            }
        });
        checkBoxPhoneReboot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Setting.isNeedRebootAfterUpdateBuild = b;
                SP.getInstance().setBooleanValue(Contants.SETTING_PHONE_REBOOT_KEY, b);
            }
        });
        checkBoxNotChangeDInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Setting.isNotChangeDeviceInfo = b;
                SP.getInstance().setBooleanValue(Contants.SETTING_NOT_CHANGE_D_INFO_KEY, b);
            }
        });

        checkBoxRebootTimeBlank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                Setting.isNeedRebootTimeBlank = b;
                SP.getInstance().setBooleanValue(Contants.SETTING_REBOOT_TIME_BLANK_KEY, b);
                editTextRebootTimeBlank.setVisibility(b ? View.VISIBLE : View.GONE);
            }
        });
        checkBoxRunNetData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                Setting.isRunNetData = b;
                SP.getInstance().setBooleanValue(Contants.SETTING_IS_RUN_NET_DATA_KEY, b);
            }
        });

        editTextIpCounts.setText("" + Setting.iChangeIpCount);
        editTextUninstallKeys.setText(Setting.uninstallKeyWork);
        editTextRebootTimeBlank.setText("" + Setting.iRebootTimeBlankNumber);

        editTextIpCounts.setVisibility(Setting.isNeedChangeIp ? View.VISIBLE : View.GONE);
        editTextUninstallKeys.setVisibility(Setting.isNeedUninstallDownApps ? View.VISIBLE : View.GONE);
        editTextRebootTimeBlank.setVisibility(Setting.isNeedRebootTimeBlank ? View.VISIBLE : View.GONE);

        editTextIpCounts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (TextUtils.isEmpty(s)) {
                    s = "1";
                }
                Setting.iChangeIpCount = Integer.valueOf(s);
                SP.getInstance().setIntValue(Contants.SETTING_CHANGE_IP_COUNT_KEY, Setting.iChangeIpCount);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextUninstallKeys.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Setting.uninstallKeyWork = charSequence.toString();
                SP.getInstance().setStringValue(Contants.SETTING_UNINSTALL_APP_KEYS_KEY, Setting.uninstallKeyWork);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextRebootTimeBlank.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String s = charSequence.toString();
                if (TextUtils.isEmpty(s)) {
                    s = "0";
                }
                Setting.iRebootTimeBlankNumber = Integer.valueOf(s);
                SP.getInstance().setIntValue(Contants.SETTING_REBOOT_TIME_BLANK_NUMBER_KEY, Setting.iRebootTimeBlankNumber);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        phoneType.setAdapter(phoneTypeAdapter);
        phoneType.setSelection(SP.getInstance().getIntValue(Contants.KEY_PHONE_TYPE_SELECTED));
        phoneType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SP.getInstance().setIntValue(Contants.KEY_PHONE_TYPE_SELECTED, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateViews() {

        Setting.COUNT = SP.getInstance().getIntValue(Contants.SETTING_COUNT_START, 1);
        maxNewCountET.setHint("cur:" + SP.getInstance().getRunTimes() + ", start:" + Setting.COUNT);


        version.setText("Version: V" + BuildConfig.VERSION_NAME);

        boolean flag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
        startBtn.setEnabled(!flag);
        stopBtn.setEnabled(flag);

        L.i(TAG, "updateViews isRunNetData = " + Setting.isRunNetData);

        checkBoxRunNetData.setChecked(Setting.isRunNetData);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Beta.checkUpgrade();

        updateViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRun = false;
        unregisterReceiver(recever);
    }

    @Override
    public void onAccessibilityStateChanged(boolean enabled) {
        Log.d(TAG, "enabled = " + enabled);
    }

    @Override
    public void onClick(View v) {
        L.i(TAG, "onClick:");
        switch (v.getId()) {
            case R.id.version:
                openUrl("http://www.bb-sz.com");
                break;
            case R.id.addVpn:
                addVpn(RunActivity.this);
                break;
            case R.id.connectVpn:
                connectVpn();
                break;
            case R.id.disconnectVpn:
                disconnectVpn();
                break;
        }
    }

    private void disconnectVpn() {
        VPNHelper.getInstance().logout("混合");
    }

    private void connectVpn() {
        VPNHelper.getInstance().login("混合", "e2701", "00");
    }

    private void addVpn(Context context) {
        L.i(TAG, "addVpn:");
        VPNHelper.getInstance().addVpn(context, "混合", "hh3.ipduoduo.cc");
    }

    private void openUrl(String url) {
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        startActivity(intent);

        SP.getInstance().setStringValue(Contants.VPN_NAME, "");

//        VPNHelper.getInstance().addVpn("湖南-郴州-电信1", "czz.8866.org");
//        VPNHelper.getInstance().addVpn("湖北-十堰-电信1", "hbss.8866.org");
//        VPNHelper.getInstance().login("湖南-郴州-电信1", "pxsk001", "Sky123");

//        VPNHelper.getInstance().addVpn("山东泰安电信", "sdtadx.ipduoduo.cc");
//        VPNHelper.getInstance().login("山东泰安电信", "e1909", "6352");
//        VPNHelper.getInstance().logout("山东泰安电信");
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.version:
                CMD.doSuExec("pm install /sdcard/TM/tgllk.apk");
                break;
        }
        return true;
    }
}
