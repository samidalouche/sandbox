package com.lifepulz.client.android.utils;

import android.widget.AdapterView;

public class AdapterUtils {
    public static <T> T selectedValue(T[] values, long index) {
        T result = null;
        if(index != AdapterView.INVALID_ROW_ID) {
            result = values[(int)index];     
        }
        return result;
    }
    public static Integer selectedValue(int[] values, long index) {
        Integer result = null;
        if(index != AdapterView.INVALID_ROW_ID) {
            result = values[(int)index];     
        }
        return result;
    }
}
