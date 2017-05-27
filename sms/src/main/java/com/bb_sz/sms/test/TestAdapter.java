package com.bb_sz.sms.test;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.bb_sz.sms.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */

public class TestAdapter extends BaseAdapter {


    private List<ItemData> list;
    private Context context;

    public void hide() {
//        index = -1;
//        Log.i("testhide", "hide()");
//        int i = 0;
//        for (ItemData item : list) {
//            Log.i("testhide", "i = " + i++ + "item: = " + item.getContent());
//        }

    }

    public TestAdapter(List<ItemData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return null == list ? 0 : list.size();
    }

    @Override
    public ItemData getItem(int position) {
        return null == list ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder2 holder;
        if (null == convertView) {
            convertView = View.inflate(context, R.layout.item_layout, null);
            holder = new ItemHolder2();
            holder.initView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ItemHolder2) convertView.getTag();
        }
        holder.initData(getItem(position), position);
        return convertView;
    }

    public void update(List<ItemData> data) {
        this.list = null;
        this.list = data;
        notifyDataSetChanged();
    }

    class ItemHolder2 {
        public TextView textView;
        public EditText editText;
        private int index = -1;

        public void initView(View view) {
            textView = (TextView) view.findViewById(R.id.textview);
            editText = (EditText) view.findViewById(R.id.edittext);
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        index = (int) v.getTag();
                        Log.i("test", "hasFocus = " + hasFocus + ", index = " + index);
                    } else {
                        index = -1;
                        Log.i("test", "hasFocus = " + hasFocus + ", index = " + index);
                    }
                }
            });
//            editText.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if (event.getAction() == MotionEvent.ACTION_UP) {
//                        if (index != (int) v.getTag()) {
////                            notifyDataSetChanged();
//                            index = (int) v.getTag();
//                            Log.i("test", "1 (int) v.getTag() = " + (int) v.getTag() + ", index = " + index);
//                        } else {
//                            Log.i("test", "2 (int) v.getTag() = " + (int) v.getTag() + ", index = " + index);
//                        }
////                        editText.setEnabled(true);
//                    }
//                    return false;
//                }
//            });

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String txt = s.toString();
                    if (index >= 0) {
                        list.get(index).setContent(txt);
                        Log.i("testX", " txt2 = " + list.get(index).getContent() + ", index = " + index + ", start = " + start + ", before = " + before + ", count = " + count);
//                        Log.i("testX", " txt2 = " + list.get(index - 1).getContent() + ", index = " + (index - 1));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

//            editText.setOnKeyListener(new View.OnKeyListener() {
//                @Override
//                public boolean onKey(View v, int keyCode, KeyEvent event) {
//                    int position = (int) v.getTag();
//                    Log.w("test", "keyCode = " + keyCode + ", position = " + position);
//                    if (KeyEvent.KEYCODE_ENTER == keyCode) {
////                        Log.i("test", ((EditText)v).getText().toString());
//                        String txt = (editText).getText().toString();
//                        if (txt.endsWith("\n\n\n\n\n")) {
//                            editText.clearFocus();
//                        } else {
//                            Log.i("test", "txt:::::" + txt);
//                        }
//                    }
//                    return false;
//                }
//            });

//            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    int position = (int) v.getTag();
//                    Log.w("test", "actionId = " + actionId + ", position = " + position);
//                    return false;
//                }
//            });

//            editText.setEnabled(false);

//            editText.setSingleLine();
//            editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }

        public void initData(ItemData item, int position) {
            editText.setText(item.getContent());
            editText.clearFocus();
            editText.setTag(position);
        }
    }
}
