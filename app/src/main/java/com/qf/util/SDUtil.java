package com.qf.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/11/22.
 */

public class SDUtil {

    /**
     * 判断是否挂载
     * @return
     */
    public static boolean isMounted(){
        boolean res = false;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
                res = true;
        }
        return res;
    }

    /**
     * 获取绝对路径
     */
    public static String getRealpath(String path){
        if(!isMounted()){
            return null;
        }
        File esd = Environment.getExternalStorageDirectory();
        String ap = esd.getAbsolutePath();
        File file = new File(ap,path);
        if(!file.exists()){
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }


}
