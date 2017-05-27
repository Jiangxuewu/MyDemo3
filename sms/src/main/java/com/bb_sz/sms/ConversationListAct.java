package com.bb_sz.sms;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public class ConversationListAct extends ListActivity {


    private static final String SMS_URI_CON = "content://sms/conversations";

    ConversationAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (null != intent){
            String type = intent.getStringExtra("TYPE");
//            if ()
        }
        update();
    }

    private void update() {
        List<Object> data = getConversationData();
        if (null == adapter) {
            adapter = new ConversationAdapter(this, data);
            setListAdapter(adapter);
        } else {
            adapter.update(data);
        }
    }

    private List<Object> getConversationData() {
        return query(this, SMS_URI_CON);
    }

    private List<Object> query(Context context, String uriStr) {
        List<Object> res = new ArrayList<>();
        try {
            ContentResolver cr = context.getContentResolver();
            Uri uri = Uri.parse(uriStr);
            Cursor cursor = cr.query(uri, null, null, null, null);
            if (null != cursor && cursor.moveToFirst()) {
                int threadId = cursor.getColumnIndex("thread_id");
                int msgCountID = cursor.getColumnIndex("msg_count");
                int snippetID = cursor.getColumnIndex("snippet");
                do {
                    String thread_id = cursor.getString(threadId);
                    int msgCount = cursor.getInt(msgCountID);
                    String snippet = cursor.getString(snippetID);
                    Conversations item = new Conversations(thread_id, msgCount, snippet);
                    res.add(item);
                } while (cursor.moveToNext());
            } else {
                Log.e("SKY", "count = 0 ");
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return res;
    }

}

