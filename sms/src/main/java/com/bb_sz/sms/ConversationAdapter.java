package com.bb_sz.sms;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/29.
 */

public class ConversationAdapter extends BaseAdapter {

    private Context context;
    private List<Object> list;

    public ConversationAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return null == list ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return null == list ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            convertView = View.inflate(context, R.layout.conversation_list, null);
            holder = new ViewHolder();
            holder.initView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.initData(getItem(position), position);
        return convertView;
    }

    public void update(List<Object> data) {
        this.list = null;
        this.list = data;
        notifyDataSetChanged();
    }

    class ViewHolder {

        public TextView snippetTV;

        public void initView(View view) {
            snippetTV = (TextView) view.findViewById(R.id.snippet_tv);
        }

        public void initData(Object object, int position) {

            Map<String, String> map = new HashMap<>();
            if (null == object) {
                return;
            }
            Class<?> cls = object.getClass();
            Field[] fields = cls.getDeclaredFields();
            if (null == fields) {
                return;
            }
            int i = 0;
            for (Field item : fields) {
                try {
                    item.setAccessible(true);
                    if (null != item.get(object)) {
                        map.put(item.getName(), String.valueOf(item.get(object)));
                        if (i++ == 0) {
                            snippetTV.setText(item.getName() + " = " + String.valueOf(item.get(object)));
                        } else {
                            snippetTV.setText(snippetTV.getText() + "\n" + item.getName() + " = " + String.valueOf(item.get(object)));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected Map<String, String> toMap(Object object) {
        Map<String, String> map = new HashMap<>();
        if (null == object) {
            return map;
        }
        Class<?> cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        if (null == fields) {
            return map;
        }
        for (Field item : fields) {
            try {
                item.setAccessible(true);
                if (null != item.get(object)) {
                    map.put(item.getName(), String.valueOf(item.get(object)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}
