package com.bb_sz.lib.http;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */

public abstract class BaseModel {
    public abstract HttpEntry toHttpEntry();

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
