package com.bb_sz.sms.test;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bb_sz.sms.R;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ItemHolder {
    public TextView textView;
    public EditText editText;
    private MyWatcher mWatcher;
    private static int index = -1;
    private static String txt = "";

    public void initView(View view) {
        textView = (TextView) view.findViewById(R.id.textview);
        editText = (EditText) view.findViewById(R.id.edittext);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    index = (int) v.getTag();
                    Log.i("test", " (int) v.getTag() = " + (int) v.getTag() + ", index = " + index);
                }
                return false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txt = s.toString();
                Log.i("test", " txt = " + txt);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void initData(ItemData item, int position) {
//        textView.setText(item.getContent());
        editText.setText(item.getContent());
//        Log.i("test", "position = " + position);
//        Log.i("test", "index = " + index);
        if (index == position) {
//            editText.requestFocus();
            editText.setText(txt);
            editText.setSelection(editText.getText().toString().length());
            Log.i("test", " txt = " + txt + ", index = " + index);
//            index = -1;
//        } else {
//            editText.clearFocus();
        }
        editText.clearFocus();
        editText.setTag(position);


//        if (mWatcher == null) {
//            mWatcher = new MyWatcher(item, position);
//        /*final*/ TextWatcher watcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                item.setContent(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        };
//        editText.removeTextChangedListener(mWatcher);
//        editText.addTextChangedListener(mWatcher);//设置edittext内容监听

//        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                EditText et = (EditText) v;
//                if (hasFocus) {
//                    et.addTextChangedListener(watcher);//设置edittext内容监听
//                } else {
//                    et.removeTextChangedListener(watcher);
//                }
//            }
//        });
//        }
    }

    class MyWatcher implements TextWatcher {
        ItemData item;
        int position;

        public MyWatcher(ItemData item, int position) {
            this.item = item;
            this.position = position;
            Log.i("test", "MyWatcher... position = " + position);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            this.item.setContent(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
