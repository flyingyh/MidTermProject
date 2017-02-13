package com.qf.util;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/11/22.
 */

public class RemoveBomUtil {
    public static String removeBom(String data){
        if(TextUtils.isEmpty(data)){
            return data;
        }
        if(data.startsWith("\ufeff")){
            return data.substring(1);
        }else{
            return data;
        }
    }
}
