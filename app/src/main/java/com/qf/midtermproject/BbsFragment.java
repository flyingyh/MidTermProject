package com.qf.midtermproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qf.util.StaticData;

/**
 * Created by Administrator on 2016/11/21.
 */

public class BbsFragment extends Fragment {
    private WebView wb_bbs;

    public BbsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bbs_fragment,container,false);

        wb_bbs = (WebView)view.findViewById(R.id.wb_bbs);
        wb_bbs.getSettings().setJavaScriptEnabled(true);//设置webView属性，能够执行JavaScript脚本
        wb_bbs.loadUrl(StaticData.PATH2);//加载需要显示的页面
        wb_bbs.setWebViewClient(new HelloWebViewClient());//设置web视图

        return view;
    }



    //web视图
    class HelloWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(StaticData.PATH2);
            return true;
        }
    }



}
