package com.qf.util;

import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/22.
 * 下载数据 不用考虑线程问题
 */

public class HandlerNetUtil {
    private static Handler handler = new Handler();

    public static void doNetWork(final String path,final DataCallBack dataCallBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                ByteArrayOutputStream baos = null;
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    if(conn.getResponseCode() == 200){
                        is = conn.getInputStream();;
                        baos = new ByteArrayOutputStream();
                        byte[] b = new byte[1024];
                        int len = 0;
                        while((len = is.read(b))!=-1){
                            baos.write(b,0,len);
                        }
                        final byte[] bytes = baos.toByteArray();

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                dataCallBack.doData(bytes);
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(baos!=null){
                        try {
                            baos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(is!=null){
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

    }

    public interface DataCallBack{
        void doData(byte[] bytes);
    }
}
