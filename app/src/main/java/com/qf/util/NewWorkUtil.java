package com.qf.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/11/22.
 * 获取网络状态
 */

public class NewWorkUtil {

    public static boolean isConnect(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info != null && info.isAvailable() && info.isConnectedOrConnecting()){
            String type = info.getTypeName();
            return true;
        }else{
            return false;
        }
    }
}
