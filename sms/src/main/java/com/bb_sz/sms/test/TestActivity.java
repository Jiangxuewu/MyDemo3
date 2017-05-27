package com.bb_sz.sms.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.bb_sz.sms.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private ListView listview;
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        listview = (ListView) findViewById(R.id.listview);

        updateList();
        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Toast.makeText(TestActivity.this, "键盘显示 高度" + height, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void keyBoardHide(int height) {
                Toast.makeText(TestActivity.this, "键盘隐藏 高度" + height, Toast.LENGTH_SHORT).show();
//                updateList();
                adapter.hide();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void updateList() {
        List<ItemData> data = getData();
        if (data == null) data = new ArrayList<>();
        if (null == adapter) {
            adapter = new TestAdapter(data, this);
            listview.setAdapter(adapter);
        } else {
            adapter.update(data);
        }
    }

    private List<ItemData> getData() {
        List<ItemData> data = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            data.add(new ItemData("test......." + i));
        }
        return data;
    }
}
