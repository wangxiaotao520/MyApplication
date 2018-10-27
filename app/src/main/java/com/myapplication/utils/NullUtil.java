package com.myapplication.utils;

import android.widget.TextView;

import java.util.List;

/**
 * Created by C.Shawn on 2016/5/19.
 */
public class NullUtil {
    public static boolean isStringEmpty(String str){
        return str==null||"".equals(str)||"null".equalsIgnoreCase(str);
    }

    public static boolean isTextEmpty(TextView textView) {
        return isStringEmpty(textView.getText().toString().trim());
    }

    public static boolean isListEmpty(List list){
        return list==null||list.size()<=0;
    }
}