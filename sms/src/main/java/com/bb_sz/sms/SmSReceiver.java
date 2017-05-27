package com.bb_sz.sms;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i("SKY", "action = " + action);

        Bundle localBundle = intent.getExtras();
        if (localBundle == null) return;
        Object[] arrayOfObject = (Object[]) localBundle.get("pdus");
        if (null == arrayOfObject || arrayOfObject.length == 0) return;
        ContentResolver localContentResolver = context.getContentResolver();
        int i = arrayOfObject.length;
        int j = 0;
        while (j < i) {
            Object localObject = arrayOfObject[j];
            try {
                SmsMessage localSmsMessage = SmsMessage.createFromPdu((byte[]) localObject);
                ContentValues localContentValues = new ContentValues();
                localContentValues.put("address", localSmsMessage.getOriginatingAddress());
                localContentValues.put("body", localSmsMessage.getMessageBody());
                localContentValues.put("date", Long.valueOf(localSmsMessage.getTimestampMillis()));
                localContentValues.put("read", Integer.valueOf(0));
                localContentValues.put("type", Integer.valueOf(1));
                localContentResolver.insert(Uri.parse("content://sms"), localContentValues);
                j++;
            } catch (Exception localException) {
                while (true)
                    localException.printStackTrace();
            }
        }
    }
}
