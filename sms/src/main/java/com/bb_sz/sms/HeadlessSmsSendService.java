package com.bb_sz.sms;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class HeadlessSmsSendService extends IntentService {
    public HeadlessSmsSendService() {
        super(HeadlessSmsSendService.class.getName());
        setIntentRedelivery(true);
    }

    protected void onHandleIntent(Intent paramIntent) {
        String action = paramIntent.getAction();
        Log.i("SKY", "action = " + action);
        if (!"android.intent.action.RESPOND_VIA_MESSAGE".equals(action)) ;
        Bundle localBundle = paramIntent.getExtras();
        if (null == localBundle) return;
        String str1 = localBundle.getString("android.intent.extra.TEXT");
        String str2 = paramIntent.getData().getSchemeSpecificPart();
        int i = str2.indexOf('?');
        if (i == -1) return;
        while ((!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(str1))) {
            String[] arrayOfString = TextUtils.split(str2, ";");
            ContentResolver localContentResolver = getContentResolver();
            SmsManager localSmsManager = SmsManager.getDefault();
            int j = arrayOfString.length;
            int k = 0;
            if (k >= j) {
                break;
            }
            String str3 = arrayOfString[k];
            try {
                localSmsManager.sendTextMessage(str3, null, str1, null, null);
                ContentValues localContentValues = new ContentValues();
                localContentValues.put("address", str3);
                localContentValues.put("body", str1);
                localContentValues.put("date", Long.valueOf(System.currentTimeMillis()));
                localContentValues.put("read", Integer.valueOf(0));
                localContentValues.put("type", Integer.valueOf(2));
                localContentResolver.insert(Uri.parse("content://sms"), localContentValues);
                k++;
                if (k >= j) {
                    break;
                }
                str2 = str2.substring(0, i);
            } catch (Exception localException) {
                localException.printStackTrace();
            }
        }
    }
}
